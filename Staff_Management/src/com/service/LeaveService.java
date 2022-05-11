package com.service;

import com.pojo.Leave;
import com.util.Page;

import java.util.List;

public interface LeaveService {
    int insert(Leave leave);
    List<Leave> leaveFillTime(int id);
    List<Leave> listleavetimeById(Page page);
    int totalLeavetime(int id);
    List<Leave> allApproval(Page page);
    List<Leave> allApprovalDepart(Page page);
    int selectDept_id(int id);
    int countDepart(int id);
    int countApproval();
    int reject(Leave leave);
    int pass(Leave leave);
}
