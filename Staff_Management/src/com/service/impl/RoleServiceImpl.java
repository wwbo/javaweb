package com.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pojo.EmployeeDepart;
import com.service.DepartService;
import com.service.EmployeeDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.RoleMapper;
import com.pojo.Employee;
import com.pojo.Role;
import com.service.RoleService;
import com.util.Page;
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
	private DepartService departService;
    @Autowired
	private EmployeeDepartService employeeDepartService;
	@Override
	public Set<String> listRoles(int id) {
		List<Role> roles = roleMapper.listRolesById(id);
		Set<String> result = new HashSet<>();
		for(Role role : roles) {
			result.add(role.getName());
		}
		return result;
	}
	@Override
	public int upadatRole(Map<String, Object> map) {
		EmployeeDepart ed = new EmployeeDepart();
		ed.setDid(departService.selectDepartId(String.valueOf(map.get("depart"))));
		ed.setEid(Integer.parseInt(String.valueOf(map.get("id"))));
		employeeDepartService.update(ed);
		return roleMapper.upadatRole(map);
	}
	@Override
	public List<Employee> searchByRole(Map<String, Object> map) {
		return roleMapper.searchByRole(map);
	}
	@Override
	public int totalRole(int rid) {
		// TODO 自动生成的方法存根
		return roleMapper.totalRole(rid);
	}

	@Override
	public List<Role> allRole(Page page) {
		return roleMapper.allRole(page);
	}

	@Override
	public int selectRid(String string) {
		return roleMapper.selectRid(string);
	}

	@Override
	public List<Role> allListRole() {
		return roleMapper.allListRole();
	}

	@Override
	public int insertRole(Role role) {
		return roleMapper.insertRole(role);
	}

	@Override
	public int updateRole01(Role role) {
		return roleMapper.updateRole01(role);
	}

	@Override
	public int countRole() {
		return roleMapper.countRole();
	}

	@Override
	public int countERbyRid(int id) {
		return roleMapper.countERbyRid(id);
	}

	@Override
	public int deleteRole(int id) {
		return roleMapper.deleteRole(id);
	}
}
