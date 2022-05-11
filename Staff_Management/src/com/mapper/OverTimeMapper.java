package com.mapper;

import com.pojo.OverTime;
import com.util.Page;

import java.util.List;

public interface OverTimeMapper {
    int insert(OverTime overTime);
    List<OverTime> listOvertimeById(Page page);
    int totalOvertime(int id);
    List<OverTime> selectFillTime(int id);
    List<OverTime> allApprovalOver(Page page);
    List<OverTime> allApprovalOverDepart(Page page);
    int countApprovalOverDepart(int id);
    int countApprovalOver();
    int rejectOver(OverTime overTime);
    int passOver(OverTime overTime);
}
