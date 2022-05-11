package com.controller;

import com.pojo.Announcement;
import com.pojo.Employee;
import com.service.AnnouncementService;
import com.util.Page;
import com.util.TimeConversion;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @RequestMapping(value="addAnnouncement",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestBody Announcement announcement){
        Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
        announcement.setAuthor(announcementService.selectRoleById(id));
        announcement.setEid(id);
        int result = announcementService.insertAnnounce(announcement);
        Map<String, Object> map = new HashMap<>();
        if (result > 0) {
            map.put("status", 1);
        }else {
            map.put("status", 0);
        }
        return map;
    }
    @RequestMapping(value="listAnnouncement", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAll(Page page){
        List<Announcement> list = announcementService.selectAnnounce(page);
        TimeConversion.Announcement(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", page.getCount());
        map.put("data", list);
        map.put("total", announcementService.countAnnounce());
        return map;
    }
    @RequestMapping(value="listAnnouncementByE", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAllByE(Page page){
        List<Announcement> list = announcementService.selectAnnounceByE(page);
        TimeConversion.Announcement(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", page.getCount());
        map.put("data", list);
        map.put("total", announcementService.countAnnounceByE());
        return map;
    }
    @RequestMapping(value="updateAnnouncement", method = RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> update(@RequestBody Announcement announcement) {
        int result = announcementService.updateAnnounce(announcement);
        Map<String , Object> map = new HashMap<String, Object>();
        if (result > 0) {
            map.put("status", 1);
        } else {
            map.put("status", 0);
        }
        return map;
    }
    @RequestMapping(value="deleteAnnouncement", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteById(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        int id = Integer.parseInt(request.getParameter("id"));
        int result = announcementService.deleteAnnounce(id);
        if (result > 0) {
            map.put("status", 1);
        } else {
            map.put("status", 0);
        }
        return map;
    }
    @RequestMapping(value="deleteBatchAnnounce", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removeBatch(@RequestParam("ids[]") int ids[]) {
        for (int i=0; i<ids.length; i++){
            System.out.println(ids[i]);
        }
        int result = announcementService.deleteBatchAnnounce(ids);
        Map<String , Object> map = new HashMap<>();
        if (result == ids.length) {
            map.put("status", 1);
        } else {
            map.put("status", 0);
        }
        return map;
    }
}
