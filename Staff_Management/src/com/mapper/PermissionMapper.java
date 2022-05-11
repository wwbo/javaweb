package com.mapper;

import java.util.List;

import com.pojo.Permission;

public interface PermissionMapper {
	public List<Permission> listPermissionById(int id);
}
