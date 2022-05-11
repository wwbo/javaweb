package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pojo.Employee;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.PunchClockMapper;
import com.pojo.PunchClock;
import com.service.PunchClockService;
@Service
@Transactional
public class PunchClockServiceImpl implements PunchClockService {
    @Autowired
    private PunchClockMapper punchClockMapper;
	@Override
	public int add_in(PunchClock punchClock) {
		// TODO 自动生成的方法存根
		return punchClockMapper.add_in(punchClock);
	}

	@Override
	public int up_out(PunchClock punchClock) {
		// TODO 自动生成的方法存根
		return punchClockMapper.up_out(punchClock);
	}

	@Override
	public int late_result(PunchClock punchClock) {
		// TODO 自动生成的方法存根
		return punchClockMapper.late_result(punchClock);
	}

	@Override
	public PunchClock if_punchin(PunchClock punchClock) {
		// TODO 自动生成的方法存根
		return punchClockMapper.if_punchin(punchClock);
	}

	@Override
	public PunchClock if_punchout(PunchClock punchClock) {
		// TODO 自动生成的方法存根
		return punchClockMapper.if_punchout(punchClock);
	}

	@Override
	public List<Date> all_punchin(int id) {
		// TODO 自动生成的方法存根
		return punchClockMapper.all_punchin(id);
	}

	@Override
	public List<Date> all_punchout(int id) {
		// TODO 自动生成的方法存根
		return punchClockMapper.all_punchout(id);
	}

	@Override
	public List<PunchClock> allinfo(Page page) {
		return punchClockMapper.allinfo(page);
	}

	@Override
	public List<PunchClock> allinfobydate(Page page) {
		return punchClockMapper.allinfobydate(page);
	}

	@Override
	public int totalbydate(Page page) {
		return punchClockMapper.totalbydate(page);
	}

	@Override
	public int total(int id) {
		return punchClockMapper.total(id);
	}

	@Override
	public List<PunchClock> all(Page page) {
		return punchClockMapper.all(page);
	}

	@Override
	public List<Employee> allRole(Page page) {
		return punchClockMapper.allRole(page);
	}

	@Override
	public List<PunchClock> allLeave(Page page) {
		return punchClockMapper.allLeave(page);
	}

	@Override
	public List<PunchClock> allNormal(Page page) {
		return punchClockMapper.allNormal(page);
	}

	@Override
	public List<PunchClock> allLate(Page page) {
		return punchClockMapper.allLate(page);
	}

	@Override
	public List<PunchClock> allLeaveEarly(Page page) {
		return punchClockMapper.allLeaveEarly(page);
	}

	@Override
	public List<PunchClock> allTravel(Page page) {
		return punchClockMapper.allTravel(page);
	}

	@Override
	public List<PunchClock> allOvertime(Page page) {
		return punchClockMapper.allOvertime(page);
	}

	@Override
	public List<PunchClock> qdnotnull(Page page) {
		return punchClockMapper.qdnotnull(page);
	}

}
