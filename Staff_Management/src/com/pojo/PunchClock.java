package com.pojo;

import java.util.Date;

public class PunchClock {
	private int id;
	private String name;
	private int employeeid;
	private String depart;
	private String role;
	private int days;
	private int normal;
	private int late;
	private int LeaveEarly;
	private int absenteeism;
	private int travel;
	private int leave;
	private int overtime;
	private Date punch_inTime;
	private Date punch_outTime;
	private Date attendanceTime;
	private String remark;
	private String ps;
	private String qtps;
	private String inTime;
	private String outTime;
	private int qd;

	public int getQd() {
		return qd;
	}

	public void setQd(int qd) {
		this.qd = qd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQtps() {
		return qtps;
	}

	public void setQtps(String qtps) {
		this.qtps = qtps;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getNormal() {
		return normal;
	}

	public void setNormal(int normal) {
		this.normal = normal;
	}

	public int getLate() {
		return late;
	}

	public void setLate(int late) {
		this.late = late;
	}

	public int getLeaveEarly() {
		return LeaveEarly;
	}

	public void setLeaveEarly(int leaveEarly) {
		LeaveEarly = leaveEarly;
	}

	public int getAbsenteeism() {
		return absenteeism;
	}

	public void setAbsenteeism(int absenteeism) {
		this.absenteeism = absenteeism;
	}

	public int getTravel() {
		return travel;
	}

	public void setTravel(int travel) {
		this.travel = travel;
	}

	public int getOvertime() {
		return overtime;
	}

	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}

	public int getLeave() {
		return leave;
	}

	public void setLeave(int leave) {
		this.leave = leave;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public Date getPunch_inTime() {
		return punch_inTime;
	}
	public void setPunch_inTime(Date punch_inTime) {
		this.punch_inTime = punch_inTime;
	}
	public Date getPunch_outTime() {
		return punch_outTime;
	}
	public void setPunch_outTime(Date punch_outTime) {
		this.punch_outTime = punch_outTime;
	}
	public Date getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(Date attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
}
