package com.service;

import com.pojo.AttendanceSetting;

import java.util.List;

public interface AttendanceSettingService {
    int updateAttendanceSetting(AttendanceSetting attendanceSetting);
    List<AttendanceSetting> selectAttendanceSetting();
}
