package com.controller;

import com.pojo.Employee;
import com.pojo.OverTime;
import com.service.LeaveService;
import com.service.OverTimeService;
import com.util.Page;
import com.util.TimeConversion;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OverTimeController {
    @Autowired
    private OverTimeService overTimeService;
    @Autowired
    private LeaveService leaveService;

    @RequestMapping(value = "insertOvertime", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addEmployeeInfo(@RequestBody OverTime overTime) {
        Map<String, Object> map = new HashMap<>();
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        overTime.setEid(id);
        overTime.setStatus("待审批");
        int result = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (overTime.getEndtime() == null && overTime.getStarttime() == null) {
            map.put("status", 0);
            return map;
        } else if (overTime.getEndtime() == null || overTime.getStarttime() == null) {
            if (overTime.getStarttime() != null) {
                overTime.setEndtime(overTime.getStarttime());
                overTime.setDays(1);
                result = overTimeService.insert(overTime);
            } else if (overTime.getEndtime() != null) {
                overTime.setStarttime(overTime.getEndtime());
                overTime.setDays(1);
                result = overTimeService.insert(overTime);
            }
        } else if (overTime.getEndtime() != null && overTime.getStarttime() != null){
            if (!format.format(overTime.getEndtime()).substring(5,7).equals(format.format(overTime.getStarttime()).substring(5,7))){
                Date sta = overTime.getStarttime();
                Date end = overTime.getEndtime();
                overTime.setEndtime(sta);
                result = overTimeService.insert(overTime);
                overTime.setStarttime(end);
                overTime.setEndtime(end);
                result = overTimeService.insert(overTime);
            } else {
                overTime.setDays(2);
                result = overTimeService.insert(overTime);
            }
        }
        if (result > 0) {
            map.put("status", 1);
        } else {
            map.put("status", 0);
        }
        return map;
    }

    @RequestMapping(value = "searchlist-Overtiem-ById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAll(Page page) {
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        page.setEid(id);
        List<OverTime> list = overTimeService.listOvertimeById(page);
        TimeConversion.overtimetoString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", overTimeService.totalOvertime(id));
        map.put("data", list);
        return map;
    }

    @RequestMapping(value = "search-filltime-byid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> filltime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        int status = 0;
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        List<OverTime> list = overTimeService.selectFillTime(id);
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
    @RequestMapping(value = "searchlist-OverApproval", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listApproval(Page page) {
        List<OverTime> list = overTimeService.allApprovalOver(page);
        TimeConversion.overtimetoString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", overTimeService.countApprovalOver());
        map.put("data", list);
        return map;
    }
    @RequestMapping(value = "searchlist-OverApprovalDepart", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listApprovalDeaprt(Page page) {
        Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
        page.setDept_id(leaveService.selectDept_id(id));
        List<OverTime> list = overTimeService.allApprovalOverDepart(page);
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getEid() == id){
                list.remove(i);
            }
        }
        TimeConversion.overtimetoString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", page.getCount());
        map.put("data", list);
        map.put("total", overTimeService.countApprovalOverDepart(page.getDept_id()));
        return map;
    }
    @RequestMapping(value = "rejectOver", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> rejectApproval(@RequestBody OverTime overTime) {
        int result = overTimeService.rejectOver(overTime);
        Map<String, Object> map = new HashMap<>();
        if (result > 0){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
        return map;
    }
    @RequestMapping(value = "passOver", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> passApproval(@RequestBody OverTime overTime) {
        int result = overTimeService.passOver(overTime);
        Map<String, Object> map = new HashMap<>();
        if (result > 0){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
        return map;
    }
}
