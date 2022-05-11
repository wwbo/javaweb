package com.realm;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.EmployeeService;
import com.service.PermissionService;
import com.service.RoleService;

public class DatabaseRealm extends AuthorizingRealm{
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//验证通过
		int id =  (int) principalCollection.getPrimaryPrincipal();
		//通过id获取角色和权限
		Set<String> roles = roleService.listRoles(id);
		//Set<String> permissions = permissionService.listPermissions(id);
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//simpleAuthorizationInfo.setStringPermissions(permissions);
		simpleAuthorizationInfo.setRoles(roles);
		System.out.println(roles);
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取账号id和密码
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		String sid = token.getPrincipal().toString();
		int id = Integer.valueOf(sid);
		String password = new String(t.getPassword());
		//获取数据库中的密码
		String passwordInDB = employeeService.getPassword(id);
		if (!passwordInDB.equals(password) || passwordInDB == null) {
			throw new AuthenticationException();
		}
		return new SimpleAuthenticationInfo(id,password,getName());
	}

}
