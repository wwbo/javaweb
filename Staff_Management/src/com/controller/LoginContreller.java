package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginContreller {
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(Model model, String id, String password) {
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.getSession());;
		UsernamePasswordToken token = new UsernamePasswordToken(id, password);
		try {
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);
			if (subject.hasRole("admin")) {
				System.out.println(subject.hasRole("admin"));
				return "adminIndex";
			} else if (subject.hasRole("employee")) {
				System.out.println("employeeIndex");
				return "employeeIndex";
			} else {
				System.out.println("managerIndex");
				return "managerIndex";
			}
		} catch (Exception e) {
			model.addAttribute("error", "工号id或密码错误!");
			return "login";
		}
	}
}
