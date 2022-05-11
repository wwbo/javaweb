package com.service.impl;

import com.mapper.OverTimeMapper;
import com.pojo.OverTime;
import com.service.OverTimeService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OverTimeServiceImpl implements OverTimeService {
    @Autowired
    private OverTimeMapper overTimeMapper;

    @Override
    public int totalOvertime(int id) {
        return overTimeMapper.totalOvertime(id);
    }

    @Override
    public List<OverTime> selectFillTime(int id) {
        return overTimeMapper.selectFillTime(id);
    }

    @Override
    public List<OverTime> allApprovalOver(Page page) {
        return overTimeMapper.allApprovalOver(page);
    }

    @Override
    public int countApprovalOverDepart(int id) {
        return overTimeMapper.countApprovalOverDepart(id);
    }

    @Override
    public List<OverTime> allApprovalOverDepart(Page page) {
        return overTimeMapper.allApprovalOverDepart(page);
    }

    @Override
    public int countApprovalOver() {
        return overTimeMapper.countApprovalOver();
    }

    @Override
    public int rejectOver(OverTime overTime) {
        return overTimeMapper.rejectOver(overTime);
    }

    @Override
    public int passOver(OverTime overTime) {
        return overTimeMapper.passOver(overTime);
    }

    @Override
    public List<OverTime> listOvertimeById(Page page) {
        return overTimeMapper.listOvertimeById(page);
    }

    @Override
    public int insert(OverTime overTime) {
        return overTimeMapper.insert(overTime);
    }
}
