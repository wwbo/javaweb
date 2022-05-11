package com.controller;

import com.pojo.Depart;
import com.pojo.Employee;
import com.service.DepartService;
import com.util.Page;
import com.util.TimeConversion;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DepartController {
    @Autowired
    private DepartService departService;
    @RequestMapping(value="listDepart", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAll(Page page){
        List<Depart> list = departService.allDepart(page);
        TimeConversion.DeparttimeotString(list);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", departService.countDepart());
        map.put("data", list);
        return map;
    }
    @RequestMapping(value="alllistDepart", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAllDepart(){
        List<Depart> list = departService.allListDepart();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", departService.countDepart());
        map.put("data", list);
        return map;
    }
    @RequestMapping(value="addDepart",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestBody Depart depart){
        int result = departService.addDepart(depart);
        Map<String, Object> map = new HashMap<>();
        if (result > 0) {
            map.put("status", 1);
        }else {
            map.put("status", 0);
        }
        return map;
    }
    @RequestMapping(value="deleteDepart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam("id") int id){
        int result = departService.deleteDepart(id);
        Map<String, Object> map = new HashMap<>();
        if (result > 0){
            map.put("status", 1);
        }else {
            map.put("status", 0);
        }
        return map;
    }
    @RequestMapping(value = "updateDepart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(@RequestBody Depart depart){
        int result = departService.updateDepart(depart);
        Map<String, Object> map = new HashMap<>();
        if (result > 0){
            map.put("status", 1);
        }else {
            map.put("status", 0);
        }
        return map;
    }
    @RequestMapping(value = "selectDepartByEid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> selectDepart(){
        int id = (Integer) SecurityUtils.getSubject().getPrincipal();
        String dept_name = departService.selectDepartName(id);
        Map<String, Object> map = new HashMap<>();
        map.put("departName", dept_name);
        return map;
    }
}
