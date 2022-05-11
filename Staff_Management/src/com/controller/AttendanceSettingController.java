package com.controller;

import com.pojo.AttendanceSetting;
import com.service.AttendanceSettingService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.TimeConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AttendanceSettingController {
    @Autowired
    private AttendanceSettingService attendanceSettingService;
    @RequestMapping(value="updateAttendanceSet",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(HttpServletRequest request) throws Exception{
        String start = request.getParameter("starttime");
        String end = request.getParameter("endtime");
        String latest = request.getParameter("lateststarttime");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = format.parse(start);
        AttendanceSetting attendanceSetting = new AttendanceSetting();
        attendanceSetting.setStarttime(format.parse(start));
        attendanceSetting.setEndtime(format.parse(end));
        attendanceSetting.setLateststarttime(format.parse(latest));
        int result = attendanceSettingService.updateAttendanceSetting(attendanceSetting);
        Map<String, Object> map = new HashMap<>();
        if (result > 0) {
            map.put("status", 1);
        }else {
            map.put("status", 0);
        }
        return map;
    }
    @RequestMapping(value="getAttendanceSetting", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAttendanceSetting(){
        List<AttendanceSetting> list = attendanceSettingService.selectAttendanceSetting();
        TimeConversion.AttendSettingtoString(list);
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("attendInfo", list.get(0));
        return map;
    }
}
