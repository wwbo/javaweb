package com.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pojo.Employee;
import com.pojo.Role;
import com.util.Page;

public interface RoleService {
	public Set<String> listRoles(int id);
	public int upadatRole(Map<String, Object> map);
	public List<Employee> searchByRole(Map<String, Object> map);
	public int totalRole(int rid);
	List<Role> allRole(Page page);
	int selectRid(String string);
	List<Role> allListRole();
	int insertRole(Role role);
	int deleteRole(int id);
	int updateRole01(Role role);
	int countRole();
	int countERbyRid(int id);
}
