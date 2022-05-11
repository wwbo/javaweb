package com.controller;

import com.pojo.Leave;
import com.pojo.OverTime;
import com.service.LeaveService;
import com.util.Page;
import com.util.TimeConversion;
import com.util.WorkDays;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class LeaveController {
    @Autowired
    private LeaveService leaveService;
    @RequestMapping(value = "insertLeave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addLeaveInfo(@RequestBody Leave leave) throws Exception{
        Map<String, Object> map = new HashMap<>();
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        leave.setEid(id);
        leave.setStatus("待审批");
        String start = leave.getStartendtime().substring(0,10);
        String end = leave.getStartendtime().substring(13,23);
        String starts = start.substring(0,7);
        String ends = end.substring(0,7);
        int result = 0;
        if (starts.equals(ends)){
            leave.setStarttime(format.parse(start));
            leave.setEndtime(format.parse(end));
            result = leaveService.insert(leave);
        }else {
            for(int i=0; i< WorkDays.getMonthBetween(starts,ends).size(); i++){
                System.out.println(WorkDays.getMonthBetween(starts,ends).size());
                if (i == 0){
                    leave.setStarttime(format.parse(start));
                    leave.setEndtime(format.parse(WorkDays.getLastDayOfMonth(starts)));
                    leave.setDays(WorkDays.getDays(start,WorkDays.getLastDayOfMonth(starts)));
                    result = leaveService.insert(leave);
                }else if (i == WorkDays.getMonthBetween(start,ends).size() - 1){
                    ends += "-01";
                    leave.setStarttime(format.parse(ends));
                    leave.setEndtime(format.parse(end));
                    leave.setDays(WorkDays.getDays(ends,end));
                    result = leaveService.insert(leave);
                }else {
                    leave.setStarttime(format.parse(WorkDays.getMonthBetween(starts,ends).get(i)+"-01"));
                    leave.setEndtime(format.parse(WorkDays.getLastDayOfMonth(WorkDays.getMonthBetween(starts,ends).get(i))));
                    leave.setDays(WorkDays.getDays((WorkDays.getMonthBetween(starts,ends).get(i)+"-01"),(WorkDays.getLastDayOfMonth(WorkDays.getMonthBetween(starts,ends).get(i)))));
                    result = leaveService.insert(leave);
                }
            }
        }
          if (result > 0) {
            map.put("status", 1);
        } else {
            map.put("status", 0);
        }
        return map;
    }

    @RequestMapping(value = "searchlist-Leavetiem-ById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAll(Page page) {
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        page.setEid(id);
        List<Leave> list = leaveService.listleavetimeById(page);
        TimeConversion.leavetimetoString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", leaveService.totalLeavetime(id));
        map.put("data", list);
        return map;
    }
    @RequestMapping(value = "leaveFilltime", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> filltime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        int status = 0;
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        List<Leave> list = leaveService.leaveFillTime(id);
        for (int i=0; i<list.size(); i++){
            Date date2 = list.get(i).getFilldate();
            String time2 = simpleDateFormat.format(date2);
            if (time.equals(time2)){
                status = 1;
                break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        return map;
    }
    @RequestMapping(value = "searchlist-leaveApproval", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listApproval(Page page) {
        List<Leave> list = leaveService.allApproval(page);
        TimeConversion.leavetimetoString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", leaveService.countApproval());
        map.put("data", list);
        return map;
    }

    @RequestMapping(value = "searchlist-Depart", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listApprovalByDepart(Page page) {
        Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
        page.setDept_id(leaveService.selectDept_id(id));
        List<Leave> list = leaveService.allApprovalDepart(page);
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getEid() == id){
                list.remove(i);
            }
        }
        TimeConversion.leavetimetoString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", page.getCount());
        map.put("data", list);
        map.put("total", leaveService.countDepart(page.getDept_id()));
        return map;
    }

    @RequestMapping(value = "reject", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> rejectApproval(@RequestBody Leave leave) {
        int result = leaveService.reject(leave);
        Map<String, Object> map = new HashMap<>();
        if (result > 0){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
        return map;
    }
    @RequestMapping(value = "pass", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> passApproval(@RequestBody Leave leave) {
        int result = leaveService.pass(leave);
        Map<String, Object> map = new HashMap<>();
        if (result > 0){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
        return map;
    }
}
