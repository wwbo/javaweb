package com.service.impl;

import java.util.List;
import java.util.Map;

import com.pojo.EmployeeDepart;
import com.service.DepartService;
import com.service.EmployeeDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.EmployeeMapper;
import com.pojo.Employee;
import com.service.EmployeeRoleService;
import com.service.EmployeeService;
import com.util.Page;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRoleService employeeRoleService;
    @Autowired
    private DepartService departService;
    @Autowired
    private EmployeeDepartService employeeDepartService;

    @Override
    public int add(Employee employee) {
        int num = employeeMapper.add(employee);
        employeeRoleService.add(last_id());
        EmployeeDepart ed = new EmployeeDepart();
        ed.setEid(last_id());
        ed.setDid(departService.selectDepartId(employee.getDepart()));
        employeeDepartService.add(ed);
        return num;
    }

    @Override
    public int addInfo(Employee employee) {
        int num = employeeMapper.addInfo(employee);
        employeeRoleService.add(last_id());
        EmployeeDepart ed = new EmployeeDepart();
        ed.setEid(last_id());
        ed.setDid(departService.selectDepartId(employee.getDepart()));
        employeeDepartService.add(ed);
        return num;
    }

    @Override
    public List<Employee> list(Page page) {
        // TODO 自动生成的方法存根
        return employeeMapper.list(page);
    }

    @Override
    public int total() {
        // TODO 自动生成的方法存根
        return employeeMapper.total();
    }

    public int delete(int id) {
        //employeeRoleService.delete(id);
        return employeeMapper.delete(id);
    }

    @Override
    public List<Employee> managerSelectById(Map<String, Integer> map) {
        return employeeMapper.managerSelectById(map);
    }

    @Override
    public int deleteBatch(int[] ids) {
        int result = 0;
        for (int i = 0; i < ids.length; i++) {
            delete(ids[i]);
            result++;
        }
        return result;
    }

    @Override
    public int update(Employee employee) {
        EmployeeDepart ed = new EmployeeDepart();
        ed.setDid(departService.selectDepartId(employee.getDepart()));
        ed.setEid(employee.getId());
        employeeDepartService.update(ed);
        return employeeMapper.update(employee);
    }

    @Override
    public int updateById(Employee employee) {
        return employeeMapper.updateById(employee);
    }

    @Override
    public List<Employee> searchById(int id) {
        return employeeMapper.searchById(id);
    }

    @Override
    public List<Employee> searchByDepart(Map<String, Object> map) {
        // TODO 自动生成的方法存根
        System.out.println(map.get(0)+" "+map.get(1)+" "+map.get(2));
        return employeeMapper.searchByDepart(map);
    }

    @Override
    public int totalDepart(int did) {
        // TODO 自动生成的方法存根
        return employeeMapper.totalDepart(did);
    }

    @Override
    public List<Map<String, Integer>> departChart() {
        return employeeMapper.departChart();
    }

    @Override
    public String getPassword(int id) {
        Employee employee = employeeMapper.getById(id);
        return employee.getPassword();
    }

    @Override
    public List<Employee> searchByName(Map<String, Object> map) {
        return employeeMapper.searchByName(map);
    }

    @Override
    public int addDepartId(Map<String, Object> map) {
        return employeeMapper.addDepartId(map);
    }

    @Override
    public List<Employee> listByDepartId(Page page) {
        return employeeMapper.listByDepartId(page);
    }
    @Override
    public int totalByName(String name) {
        return employeeMapper.totalByName(name);
    }

    @Override
    public int last_id() {
        int last_id = employeeMapper.last_id();
        return last_id;
    }


}
