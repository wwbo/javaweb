package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pojo.Depart;
import com.pojo.Role;
import com.service.DepartService;
import com.util.Page;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pojo.Employee;
import com.service.RoleService;
@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartService departService;
	/*private int rid;
	public void toId(String role) {
		switch (role) {
		case "董事长（超级管理员）":
			rid = 1;
			break;
        case "研发经理":
        	rid = 2;
        	break;
        case "推广经理":
        	rid = 3;
        	break;
        case "客服经理":
        	rid = 4;
        	break;
        case "财务经理":
        	rid = 5;
        	break;
        case "行政经理":
        	rid = 6;
        	break;
        default:
        	rid = 7;
        	break;
		}
	}*/
	@RequestMapping(value="list_Role", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listAll(Page page){
		List<Role> list = roleService.allRole(page);
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", page.getCount());
		map.put("data", list);
		return map;
	}
	@RequestMapping(value="list_RoleAndDepart", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> RoleAndDepart(){
		List<Role> list = roleService.allListRole();
		List<Depart> list1 = departService.allListDepart();
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", 8);
		map.put("data", list);
		map.put("data1",list1);
		return map;
	}
    @RequestMapping(value="updateRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> update(@RequestBody Employee employee) {
		int rid = roleService.selectRid(employee.getRole());
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("id", employee.getId());
		map.put("name", employee.getName());
		map.put("depart", employee.getDepart());
		map.put("password", employee.getPassword());
		map.put("phone" ,employee.getPhone());
		map.put("email", employee.getEmail());
		map.put("rid", rid);
		int num = roleService.upadatRole(map);
	    Map<String, Object> map2 = new HashMap<>();
    	if (num == 2) {
            map2.put("status", 1);
         } else {
            map2.put("status", 0);
         }
        return map2;
    }
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addRole(@RequestBody Role role){
		int result = roleService.insertRole(role);
		Map<String, Object> map = new HashMap<>();
		if (result > 0){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		return map;
	}
	@RequestMapping(value = "deleteRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRole(@RequestParam("id") int id){
		Map<String, Object> map = new HashMap<>();
		if (roleService.countERbyRid(id) > 0){
			map.put("status", 2);
		}else {
			int result = roleService.deleteRole(id);
			if (result > 0){
				map.put("status", 1);
			}else {
				map.put("status", 0);
			}
		}
		return map;
	}
	@RequestMapping(value = "updateRole01", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateRole(@RequestBody Role role){
		System.out.println(role.getId());
		int result = roleService.updateRole01(role);
		Map<String, Object> map = new HashMap<>();
		if (result > 0){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		return map;
	}
	@RequiresRoles("admin")
    @RequestMapping(value="search-from-role", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> searchByRole(HttpServletRequest request) {
    	String role= request.getParameter("role");
    	Integer start;
    	Integer count;
    	if (request.getParameter("start") != null) {
    		start = Integer.valueOf(request.getParameter("start"));
	    	count = Integer.valueOf(request.getParameter("count"));
		}else {
			int page = Integer.valueOf(request.getParameter("page"));
        	count = Integer.valueOf(request.getParameter("limit"));
    		start = (page-1)*count;
		}
    	Map<String, Object> map1 = new HashMap<>();
    	map1.put("role", role);
    	map1.put("start", start);
    	map1.put("count", count);
    	List<Employee> data = roleService.searchByRole(map1);
    	Map<String, Object> map = new HashMap<>();
    	map.put("code", 0);
    	map.put("msg", "");
    	map.put("count", data.size());
    	map.put("data", data);
    	map.put("total", roleService.totalRole(roleService.selectRid(role)));
    	return map;
    }

}
