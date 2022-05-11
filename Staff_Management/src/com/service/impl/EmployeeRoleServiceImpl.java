package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.EmployeeRoleMapper;
import com.service.EmployeeRoleService;
@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService{
    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;

	@Override
	public int delete(int id) {
		return employeeRoleMapper.delete(id);
	}

	@Override
	public int add(int id) {
		return employeeRoleMapper.add(id);
	}
	
}
