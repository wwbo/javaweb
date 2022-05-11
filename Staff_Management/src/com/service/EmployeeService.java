package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.Employee;
import com.util.Page;

public interface EmployeeService {
	public int add(Employee employee);
	public int addInfo(Employee employee);
	public int last_id();
	public List<Employee> list(Page page);
	public int total();
	public int delete(int id);
	public int deleteBatch(int[] ids);
	public int update(Employee employee);
	public int updateById(Employee employee);
	public List<Employee> searchById(int id);
	List<Employee> searchByDepart(Map<String, Object> map);
	int totalDepart(int did);
	public List<Map<String,Integer>> departChart();
	public String getPassword(int id);
	List<Employee> searchByName(Map<String, Object> map);
	int totalByName(String name);
	int addDepartId(Map<String, Object> map);
	List<Employee> listByDepartId(Page page);
	List<Employee> managerSelectById(Map<String, Integer> map);
}
