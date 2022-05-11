package com.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.PermissionMapper;
import com.pojo.Permission;
import com.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    private PermissionMapper permissionMapper;
	@Override
	public Set<String> listPermissions(int id) {
		List<Permission> permissions = permissionMapper.listPermissionById(id);
		Set<String> result = new HashSet<>();
		for(Permission permission : permissions) {
			result.add(permission.getName());
		}
		return result;
	}
	
}
