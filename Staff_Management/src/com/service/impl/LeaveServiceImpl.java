package com.service.impl;

import com.mapper.LeaveMapper;
import com.pojo.Leave;
import com.service.LeaveService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public List<Leave> leaveFillTime(int id) {
        return leaveMapper.leaveFillTime(id);
    }

    @Override
    public List<Leave> listleavetimeById(Page page) {
        return leaveMapper.listleavetimeById(page);
    }

    @Override
    public int totalLeavetime(int id) {
        return leaveMapper.totalLeavetime(id);
    }

    @Override
    public List<Leave> allApproval(Page page) {
        return leaveMapper.allApproval(page);
    }

    @Override
    public int selectDept_id(int id) {
        return leaveMapper.selectDept_id(id);
    }

    @Override
    public int countDepart(int id) {
        return leaveMapper.countDepart(id);
    }

    @Override
    public List<Leave> allApprovalDepart(Page page) {
        return leaveMapper.allApprovalDepart(page);
    }

    @Override
    public int countApproval() {
        return leaveMapper.countApproval();
    }

    @Override
    public int reject(Leave leave) {
        return leaveMapper.reject(leave);
    }

    @Override
    public int pass(Leave leave) {
        return leaveMapper.pass(leave);
    }

    @Override
    public int insert(Leave leave) {
        return leaveMapper.insert(leave);
    }
}
