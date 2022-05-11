package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.service.DepartService;
import com.service.EmployeeDepartService;
import com.service.LeaveService;
import com.util.TimeConversion;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.pojo.Employee;
import com.randomPwd.RandomPwd;
import com.service.EmployeeService;
import com.util.Page;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private DepartService departService;

	@RequiresRoles("admin")
	@RequestMapping(value="list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listAll(Page page){
			List<Employee> list = employeeService.list(page);
		    TimeConversion.timetoString(list);
		    TimeConversion.birthtimetoString(list);
			Map<String, Object> map = new HashMap<>();
			map.put("code", 0);
	    	map.put("msg", "");
	    	map.put("count", employeeService.total());
	    	map.put("data", list);
	    	return map;
	}
	@RequestMapping(value="list-by-departId", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listAllDepart(Page page){
		Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
		page.setDept_id(leaveService.selectDept_id(id));
		List<Employee> list = employeeService.listByDepartId(page);
		/*TimeConversion.timetoString(list);
		TimeConversion.birthtimetoString(list);*/
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", page.getCount());
		map.put("data", list);
		map.put("total", employeeService.totalDepart(page.getDept_id()));
		return map;
	}

	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@RequestBody Employee employee){
		int result = employeeService.add(employee);
		System.out.println(employee);
		Map<String, Object> map = new HashMap<>();
		if (result > 0) {
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		return map;
	}
	@RequestMapping(value="addEmployeeInfo",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addEmployeeInfo(@RequestBody Employee employee){
		int result = employeeService.addInfo(employee);
		Map<String, Object> map = new HashMap<>();
		if (result > 0) {
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		return map;
	}
	@RequestMapping(value="delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteById(HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	int id = Integer.parseInt(request.getParameter("id"));
    	int result = employeeService.delete(id);
        if (result > 0) {
           map.put("status", 1);
        } else {
           map.put("status", 0);
        }
        return map;
    }
    @RequestMapping(value="deleteBatch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removeBatch(@RequestParam("ids[]") int ids[]) {
    	int result = employeeService.deleteBatch(ids);
    	Map<String , Object> map = new HashMap<String, Object>();
    	if (result == ids.length) {
            map.put("status", 1);
         } else {
            map.put("status", 0);
         }
         return map;
    }
	@RequiresRoles("admin")
    @RequestMapping(value="search-from-id", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> searchById(@RequestParam("id") Integer id) {
		if (id == null){
			id = (Integer) SecurityUtils.getSubject().getPrincipal();
		}
    	List<Employee> data = employeeService.searchById(id);
    	TimeConversion.timetoString(data);
		TimeConversion.birthtimetoString(data);
    	Map<String, Object> map = new HashMap<>();
    	map.put("code", 0);
    	map.put("msg", "");
    	map.put("count", 8);
    	map.put("data", data);
    	map.put("total", data.size());
    	return map;
    }
	@RequestMapping(value="search-from-name", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchByname(HttpServletRequest request) {
		String name = request.getParameter("name");
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
		map1.put("name", name);
		map1.put("start", start);
		map1.put("count", count);
		List<Employee> list = employeeService.searchByName(map1);
		TimeConversion.timetoString(list);
		TimeConversion.birthtimetoString(list);
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
		map.put("total", employeeService.totalByName(name));
		System.out.println(employeeService.totalByName(name));
		return map;
	}
	@RequestMapping(value="search-to-id", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchToId() {
		int id = (Integer) SecurityUtils.getSubject().getPrincipal();
		List<Employee> list = employeeService.searchById(id);
		TimeConversion.timetoString(list);
		TimeConversion.birthtimetoString(list);
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}
	@RequestMapping(value="search-to-id-info", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchToIdInfo(@RequestParam("id") Integer id) {
		List<Employee> list = employeeService.searchById(id);
		TimeConversion.timetoString(list);
		TimeConversion.birthtimetoString(list);
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", 8);
		map.put("data", list);
		map.put("total", 1);
		return map;
	}
	@RequestMapping(value="manager-search-to-id-info", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchManagerInfo(@RequestParam("id") Integer id) {
		Map<String, Integer> map1 = new HashMap<>();
		map1.put("dept_id", departService.selectDepartIdByEid(id));
		map1.put("id", id);
		List<Employee> list = employeeService.managerSelectById(map1);
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		map.put("total", 1);
		return map;
	}
	@RequiresRoles("admin")
    @RequestMapping(value="search-from-depart", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> searchByDepart(HttpServletRequest request) {
    	String depart= request.getParameter("depart");
    	int did = departService.selectDepartId(depart);
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
    	map1.put("start", start);
    	map1.put("count", count);
    	map1.put("did", did);
		List<Employee> data = employeeService.searchByDepart(map1);
		TimeConversion.timetoString(data);
		TimeConversion.birthtimetoString(data);
    	Map<String, Object> map = new HashMap<>();
    	map.put("code", 0);
    	map.put("msg", "");
    	map.put("count", count);
    	map.put("data", data);
    	map.put("total", employeeService.totalDepart(did));
    	return map;
    }
	@RequiresRoles("admin")
	@RequestMapping(value="update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String , Object> update(@RequestBody Employee employee) {
		int result = employeeService.update(employee);
		Map<String , Object> map = new HashMap<String, Object>();
		if (result > 0) {
			map.put("status", 1);
		} else {
			map.put("status", 0);
		}
		return map;
	}
	@RequestMapping(value="updateById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateById(@RequestBody Employee employee) {
		int id = (Integer) SecurityUtils.getSubject().getPrincipal();
		employee.setId(id);
		Map<String , Object> map = new HashMap<>();
		int result = employeeService.updateById(employee);
		if (result > 0) {
			map.put("status", 1);
		} else {
			map.put("status", 0);
		}
		map.put("status",1);
		return map;
	}

    @RequestMapping(value="getPwd", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPwd(){
    	String pwd = RandomPwd.getRandomPwd();
    	Map<String , Object> map = new HashMap<String, Object>();
    	map.put("pwd", pwd);
    	return map;
    }

    @RequestMapping(value = "getdeparttotal" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getdeparttotal(){
        List<Map<String,Integer>> list = employeeService.departChart();
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
	}
}
