package com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pojo.Employee;
import com.pojo.PunchClock;
import com.util.Page;

public interface PunchClockService {
	int add_in(PunchClock punchClock);
	int up_out(PunchClock punchClock);
	int late_result(PunchClock punchClock);
	PunchClock if_punchin(PunchClock punchClock);
	PunchClock if_punchout(PunchClock punchClock);
	List<Date> all_punchin(int id);
	List<Date> all_punchout(int id);
	List<PunchClock> allinfo(Page page);
	List<PunchClock> allinfobydate(Page page);
	int totalbydate(Page page);
	int total(int id);
	List<PunchClock> all(Page page);
	List<Employee> allRole(Page page);
	List<PunchClock> allLeave(Page page);
	List<PunchClock> allNormal(Page page);
	List<PunchClock> allLate(Page page);
	List<PunchClock> allLeaveEarly(Page page);
	List<PunchClock> allTravel(Page page);
	List<PunchClock> allOvertime(Page page);
	List<PunchClock> qdnotnull(Page page);
}
