package com.mapper;

import com.pojo.AttendanceSetting;

import java.util.List;

public interface AttendanceSettingMapper {
    int updateAttendanceSetting(AttendanceSetting attendanceSetting);
    List<AttendanceSetting> selectAttendanceSetting();
}
