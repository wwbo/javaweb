package com.service.impl;

import com.mapper.AttendanceSettingMapper;
import com.pojo.AttendanceSetting;
import com.service.AttendanceSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttendanceSettingServiceImpl implements AttendanceSettingService {
    @Autowired
    private AttendanceSettingMapper attendanceSettingMapper;
    @Override
    public int updateAttendanceSetting(AttendanceSetting attendanceSetting) {
        return attendanceSettingMapper.updateAttendanceSetting(attendanceSetting);
    }

    @Override
    public List<AttendanceSetting> selectAttendanceSetting() {
        return attendanceSettingMapper.selectAttendanceSetting();
    }

}
