package com.controller;

import java.util.HashMap;
import java.util.Map;

import com.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.util.Page;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageInfoController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private OverTimeService overTimeService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private TravelService travelService;
	@Autowired
	private PunchClockService punchClockService;
	@Autowired
	private DepartService departService;
	@Autowired
	private RoleService roleService;
	@RequiresRoles("admin")
	@RequestMapping(value="getPageInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPageInfo(Page page){
    	Map<String , Object> map = new HashMap<String, Object>();
    	map.put("count", page.getCount());
    	map.put("total", employeeService.total());
    	return map;
    }
    @RequestMapping(value = "getOvertime-PageInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOvertime(Page page){
		int id = (Integer) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", overTimeService.totalOvertime(id));
		return map;
	}
	@RequestMapping(value = "getLeavetime-PageInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getLeavetime(Page page){
		int id = (Integer) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", leaveService.totalLeavetime(id));
		return map;
	}
	@RequestMapping(value = "getTravel-PageInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getTraveltotal(Page page){
		int id = (Integer) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", travelService.totalTravleRecord(id));
		return map;
	}
	@RequestMapping(value = "getPunck-PageInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPuncktotal(Page page){
		int id = (Integer) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", punchClockService.total(id));
		return map;
	}
	@RequestMapping(value = "getPunck-bydate", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getbydatetotal(HttpServletRequest request){
		String date = request.getParameter("date");
		int id = (Integer) SecurityUtils.getSubject().getPrincipal();
		Page page = new Page();
		page.setEmployeeid(id);
		page.setDate(date);
		System.out.println(page.getEmployeeid());
		System.out.println(page.getDate());
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", punchClockService.totalbydate(page));
		System.out.println(punchClockService.totalbydate(page));
		return map;
	}
	@RequestMapping(value = "getStatistics-PageInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getStatisticstotal(Page page){
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", employeeService.total());
		return map;
	}
	@RequestMapping(value = "getLeave-Approval", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> gettotal(Page page){
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", leaveService.countApproval());
		return map;
	}
	@RequestMapping(value = "getOver-Approval", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOvertotal(Page page){
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", overTimeService.countApprovalOver());
		return map;
	}
	@RequestMapping(value = "getTravel-Approval", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> Traveltotal(Page page){
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", travelService.countTravel());
		return map;
	}
	@RequestMapping(value = "countDepart", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> departtotal(Page page){
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", departService.countDepart());
		return map;
	}
	@RequestMapping(value = "list_Role_Page", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> roletotal(Page page){
		Map<String, Object> map = new HashMap<>();
		map.put("count", page.getCount());
		map.put("total", roleService.countRole());
		return map;
	}
}
