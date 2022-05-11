package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.Employee;
import com.pojo.Role;
import com.util.Page;

public interface RoleMapper {
    List<Role> listRolesById(int id);

    int upadatRole(Map<String, Object> map);

    List<Employee> searchByRole(Map<String, Object> map);

    int totalRole(int rid);

    List<Role> allRole(Page page);

    int selectRid(String string);

    List<Role> allListRole();

    int insertRole(Role role);

    int deleteRole(int id);

    int updateRole01(Role role);

    int countRole();

    int countERbyRid(int id);
}
