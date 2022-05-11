package com.service.impl;

import com.mapper.EmployeeDepartMapper;
import com.pojo.EmployeeDepart;
import com.service.EmployeeDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeDepartServiceImpl implements EmployeeDepartService {
    @Autowired
    private EmployeeDepartMapper employeeDepartMapper;
    @Override
    public int add(EmployeeDepart employeeDepart) {
        return employeeDepartMapper.add(employeeDepart);
    }

    @Override
    public int update(EmployeeDepart employeeDepart) {
        return employeeDepartMapper.update(employeeDepart);
    }
}
