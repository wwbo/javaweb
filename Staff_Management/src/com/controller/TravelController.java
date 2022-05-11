package com.controller;

import com.pojo.Leave;
import com.pojo.Travel;
import com.service.LeaveService;
import com.service.TravelService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

@Controller
public class TravelController {
    @Autowired
    private TravelService travelService;
    @Autowired
    private LeaveService leaveService;

    @RequestMapping(value = "insertTravel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addTravelInfo(@RequestBody Travel travel) throws Exception {
        Map<String, Object> map = new HashMap<>();
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        travel.setEid(id);
        travel.setStatus("待审批");
        String start = travel.getStartendtime().substring(0, 10);
        String end = travel.getStartendtime().substring(13, 23);
        String starts = start.substring(0,7);
        String ends = end.substring(0,7);
        int result = 0;
        //travel.setAddress(travel.getProvince()+travel.getCity()+travel.getCounty());
        if (starts.equals(ends)){
            travel.setStarttime(format.parse(start));
            travel.setEndtime(format.parse(end));
            travel.setAddress(travel.getProvince()+travel.getCity()+travel.getCounty());
            result = travelService.insert(travel);
        }else {
            for(int i = 0; i< WorkDays.getMonthBetween(starts,ends).size(); i++){
                if (i == 0){
                    travel.setStarttime(format.parse(start));
                    travel.setEndtime(format.parse(WorkDays.getLastDayOfMonth(starts)));
                    int lastdays = Integer.valueOf(WorkDays.getLastDayOfMonth(starts).substring(8,10))-Integer.valueOf(start.substring(8,10))+1;
                    travel.setDays(lastdays);
                    travel.setAddress(travel.getProvince()+travel.getCity()+travel.getCounty());
                    result = travelService.insert(travel);
                }else if (i == WorkDays.getMonthBetween(start,ends).size() - 1){
                    ends += "-01";
                    travel.setStarttime(format.parse(ends));
                    travel.setEndtime(format.parse(end));
                    int lastdays = Integer.valueOf(end.substring(8,10))-Integer.valueOf(ends.substring(8,10))+1;
                    travel.setDays(lastdays);
                    travel.setAddress(travel.getProvince()+travel.getCity()+travel.getCounty());
                    result = travelService.insert(travel);
                }else {
                    travel.setStarttime(format.parse(WorkDays.getMonthBetween(starts,ends).get(i)+"-01"));
                    travel.setEndtime(format.parse(WorkDays.getLastDayOfMonth(WorkDays.getMonthBetween(starts,ends).get(i))));
                    int lastdays = Integer.valueOf(WorkDays.getLastDayOfMonth(WorkDays.getMonthBetween(starts,ends).get(i)).substring(8,10))-Integer.valueOf((WorkDays.getMonthBetween(starts,ends).get(i)+"-01").substring(8,10))+1;
                    travel.setDays(lastdays);
                    travel.setAddress(travel.getProvince()+travel.getCity()+travel.getCounty());
                    result = travelService.insert(travel);
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
    @RequestMapping(value = "searchlist-Travel-ById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAll(Page page) {
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        page.setEid(id);
        List<Travel> list = travelService.listtraveltimeById(page);
        TimeConversion.traveltimetoString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", travelService.totalTravleRecord(id));
        map.put("data", list);
        return map;
    }
    @RequestMapping(value = "travelFilltime", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> filltime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        int status = 0;
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        List<Travel> list = travelService.travelFillTime(id);
        for (int i = 0; i < list.size(); i++) {
            Date date2 = list.get(i).getFilldate();
            String time2 = simpleDateFormat.format(date2);
            if (time.equals(time2)) {
                status = 1;
                break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        return map;
    }
    @RequestMapping(value = "searchlist-TravelApproval", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listApproval(Page page) {
        List<Travel> list = travelService.allTravelApproval(page);
        TimeConversion.traveltimetoString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", travelService.countTravel());
        map.put("data", list);
        return map;
    }
    @RequestMapping(value = "searchlist-TravelApproval-depart", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listApprovalDepart(Page page) {
        Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
        page.setDept_id(leaveService.selectDept_id(id));
        List<Travel> list = travelService.allTreavelApprovalDepart(page);
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getEid() == id){
                list.remove(i);
            }
        }
        TimeConversion.traveltimetoString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", page.getCount());
        map.put("data", list);
        map.put("total", travelService.countTravelDepart(page.getDept_id()));
        return map;
    }
    @RequestMapping(value = "rejectTravel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> rejectApproval(@RequestBody Travel travel) {
        int result = travelService.rejectTravel(travel);
        Map<String, Object> map = new HashMap<>();
        if (result > 0){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
        return map;
    }
    @RequestMapping(value = "passTravel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> passApproval(@RequestBody Travel travel) {
        int result = travelService.passTravel(travel);
        Map<String, Object> map = new HashMap<>();
        if (result > 0){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
        return map;
    }
}