package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.pojo.AttendanceSetting;
import com.pojo.Employee;
import com.service.AttendanceSettingService;
import com.service.EmployeeService;
import com.util.Page;
import com.util.TimeConversion;
import com.util.WorkDays;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pojo.PunchClock;
import com.service.PunchClockService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("sign")
public class PunchClockController {
	@Autowired
	private PunchClockService punchClockService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AttendanceSettingService attendanceSettingService;

	// 获取对应id全部签到的时间（去掉毫秒）
	@RequestMapping(value = "all_punchin", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> all_punchin() {
		Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
		List<Date> date = punchClockService.all_punchin(id);
		List<String> mydate = new ArrayList<String>();
		for (int i = 0; i < date.size(); i++) {
			mydate.add(String.valueOf(date.get(i).getTime() / 1000));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("mydate", mydate);
		return map;
	}
	//查询今日是否已经签到
    @RequestMapping(value= "if_punchIn", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> if_punchIn() throws Exception{
    	Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
    	PunchClock punchClock = new PunchClock();
    	String nowtime = "";
    	String ps = "";
    	Date date = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat Format = new SimpleDateFormat("HH:mm:ss");
    	Map<String, Object> map = new HashMap<>();
    	punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
		punchClock.setEmployeeid(id);
		if (punchClockService.if_punchin(punchClock) == null) {
			ps = "待签到";
			nowtime = "未签到";
		}else {
			ps = punchClockService.if_punchin(punchClock).getPs();
			nowtime = String.valueOf(Format.format(punchClockService.if_punchin(punchClock).getPunch_inTime()));
		}
		map.put("now_time", nowtime);
		map.put("ps", ps);
		return map;
    }
    //如果未签到，进行签到
	@RequestMapping(value = "in_time.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> in_time() throws Exception {
		PunchClock punchClock = new PunchClock();
		Integer result = null;
		String nowtime = "";
		Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat Format = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat FormatAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		List<AttendanceSetting> list = attendanceSettingService.selectAttendanceSetting();
		String start = Format.format(list.get(0).getStarttime());
		String latest = Format.format(list.get(0).getLateststarttime());
		System.out.println(start+latest);
		Date inTime = Format.parse(start);
		Date outTime = Format.parse(latest);
		Date now_time = Format.parse(Format.format(date));
		Date now_atime = FormatAll.parse(FormatAll.format(date));
		punchClock.setPunch_inTime(now_atime);
		punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
		punchClock.setEmployeeid(id);
        System.out.println(id);
        System.out.println(punchClockService.if_punchin(punchClock));
		if (punchClockService.if_punchin(punchClock) == null) {
			if (now_time.before(outTime) && now_time.after(inTime)) {
				punchClock.setPs("迟到");
				nowtime = String.valueOf(Format.format(date));
				punchClockService.add_in(punchClock);
			} else if (now_time.after(outTime)) {
				punchClock.setPs("缺勤");
				nowtime = "已缺勤";
			} else {
				punchClock.setPs("正常");// 未迟到未签到
				nowtime = String.valueOf(Format.format(date));
				punchClockService.add_in(punchClock);
			}
		} else {
			// 已签到
			nowtime = String.valueOf(Format.format(punchClockService.if_punchin(punchClock).getPunch_inTime()));
		}
		String ps = punchClock.getPs();
        System.out.println(id+"、"+now_atime+"、"+dateFormat.parse(dateFormat.format(date))+"、"+ps);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ps", ps);
		map.put("now_time", nowtime);
		map.put("result", result);
		return map;
	}
	// 获取对应id全部签退的时间（去掉毫秒）
	@RequestMapping(value = "all_punchout", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> all_punchout() {
		Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
		List<Date> date = punchClockService.all_punchout(id);
		List<String> mydate = new ArrayList<String>();
		for (int i = 0; i < date.size(); i++) {
			if (date.get(i) != null) {
				mydate.add(String.valueOf(date.get(i).getTime() / 1000));
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("mydate", mydate);
		return map;
	}
	//查询今日是否已经签退
	@RequestMapping(value= "if_punchOut", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> if_punchOut() throws Exception{
    	Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
    	PunchClock punchClock = new PunchClock();
    	String nowtime = "";
    	String ps = "";
    	Date date = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat Format = new SimpleDateFormat("HH:mm:ss");
    	Map<String, Object> map = new HashMap<>();
    	punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
		punchClock.setEmployeeid(id);
		if (punchClockService.if_punchout(punchClock) == null) {
			ps = "待签退";
			nowtime = "未签退";
		}else {
			ps = "正常";
			nowtime = String.valueOf(Format.format(punchClockService.if_punchout(punchClock).getPunch_outTime()));
		}
		map.put("out_time", nowtime);
		map.put("ps", ps);
		return map;
    }
    //如果未签退，进行签退
	@RequestMapping(value = "out_time.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> out_time() throws Exception {
		PunchClock punchClock = new PunchClock();
		Integer result = null;
		String nowtime = "";
		Integer id = (Integer) SecurityUtils.getSubject().getPrincipal();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat Format = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat FormatAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		List<AttendanceSetting> list = attendanceSettingService.selectAttendanceSetting();
		String end = Format.format(list.get(0).getEndtime());
		Date inTime = Format.parse(end);
		Date now_time = Format.parse(Format.format(date));
		Date now_atime = FormatAll.parse(FormatAll.format(date));
		punchClock.setPunch_inTime(now_atime);
		punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
		punchClock.setEmployeeid(id);
		if (punchClockService.if_punchin(punchClock) == null) {
			result = -1; //未签到不能签退
		} else {
			if (punchClockService.if_punchout(punchClock) == null) {// 已签到未签退
				if (now_time.before(inTime)) {
					result = -2; //未到签退时间
				}
				else {//可以签退
					result = 1;
					punchClock.setPunch_outTime(now_atime);
					nowtime = String.valueOf(Format.format(date));
				    punchClockService.up_out(punchClock);
				}
			}else {
				nowtime = String.valueOf(Format.format(punchClockService.if_punchout(punchClock).getPunch_outTime()));
			}
		}
		System.out.println(nowtime);
		Map<String, Object> map = new HashMap<>();
		map.put("out_time", nowtime);
		map.put("result", result);
		return map;
	}

	@RequestMapping(value = "searchlist-PunchClock-ById", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listAll(Page page) {
		int id = (Integer) SecurityUtils.getSubject().getPrincipal();
		page.setEid(id);
		List<PunchClock> list = punchClockService.allinfo(page);
		for (int i=0; i<list.size(); i++){
			if (list.get(i).getPunch_outTime() == null){
				list.get(i).setQtps("未签退");
			}else {
				list.get(i).setQtps("正常");
			}
		}
		TimeConversion.punchClocktoString(list);
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", punchClockService.total(id));
		map.put("data", list);
		return map;
	}
	@RequestMapping(value = "searh-from-date", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchByDate(HttpServletRequest request) throws Exception{
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String count = request.getParameter("count");
		int id = (Integer) SecurityUtils.getSubject().getPrincipal();
		Page page = new Page();
		if (start != null){
			page.setStart(Integer.valueOf(start));
			page.setCount(Integer.valueOf(count));
			page.setDate(date);
		} else {
			page.setCount(Integer.valueOf(limit));
			page.setDate(date);
			page.setStart(0);
		}
		page.setEid(id);
		List<PunchClock> list = punchClockService.allinfobydate(page);
		for (int i=0; i<list.size(); i++){
			if (list.get(i).getPunch_outTime() == null){
				list.get(i).setQtps("未签退");
			}else {
				list.get(i).setQtps("正常");
			}
		}
		TimeConversion.punchClocktoString(list);
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", punchClockService.total(id));
		map.put("data", list);
		return map;
	}
	@RequestMapping(value = "all-Statistics", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) throws Exception{
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String count = request.getParameter("count");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		String start01 = year + "-" + month + "-01";
		String end01 = year + "-" + month + "-" + day;
		String yandm;
		if (month < 10){
			yandm = year+"-0"+month;
		}else {
			yandm = year+"-"+month;
		}
		System.out.println(date);
		Page page = new Page();
		if (start != null){
			page.setStart(Integer.valueOf(start));
			page.setCount(Integer.valueOf(count));
			if (date == null){
				page.setDate(yandm);
			}else {
				page.setDate(date);
			}
		} else {
			page.setCount(Integer.valueOf(limit));
			if (date == null){
				page.setDate(yandm);
			}else {
				page.setDate(date);
			}
			page.setStart(0);
		}
		List<PunchClock> list = punchClockService.allNormal(page);
		List<PunchClock> listleave = punchClockService.allLeave(page);
		List<PunchClock> listlate = punchClockService.allLate(page);
		List<PunchClock> listleareEarly = punchClockService.allLeaveEarly(page);
		List<PunchClock> listTravel = punchClockService.allTravel(page);
		List<PunchClock> listOvertime = punchClockService.allOvertime(page);
		List<Employee> listRole = punchClockService.allRole(page);
		List<PunchClock> listqd = punchClockService.qdnotnull(page);
		for (int i=0; i<list.size(); i++){
			list.get(i).setLeave(listleave.get(i).getLeave());
			list.get(i).setLate(listlate.get(i).getLate());
			list.get(i).setLeaveEarly(listleareEarly.get(i).getLeaveEarly());
			list.get(i).setTravel(listTravel.get(i).getTravel());
			list.get(i).setOvertime(listOvertime.get(i).getOvertime());
			list.get(i).setRole(listRole.get(i).getRole());
			list.get(i).setDays(WorkDays.getWorkDays(Integer.valueOf(page.getDate().substring(0,4)),Integer.valueOf(page.getDate().substring(5,7))));
			int absenteeism = list.get(i).getDays()-listqd.get(i).getQd()-list.get(i).getLeave()-list.get(i).getTravel();
			if (absenteeism < 0){
				list.get(i).setAbsenteeism(0);
			}else {
				list.get(i).setAbsenteeism(absenteeism);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", employeeService.total());
		map.put("data", list);
		return map;
	}
}
