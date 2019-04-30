package com.web.user.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.web.user.service.IUserService;
import com.web.user.vo.UserVo;



@Controller
@RequestMapping("user")
public class UserServiceController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@GetMapping("/login")
	public String loginPage() {
		return "/user/login";
	}
	@GetMapping("/register")
	public String registerPage() {	
		return "/user/register";
	}
	
	@PostMapping("/register")
	@ResponseBody
	public Map<String, Object> register(@RequestBody UserVo user) throws UnknownHostException {
		Map<String, Object> result = new HashMap<>();
		//4자리 랜덤 코드
		int ucode = new Random().nextInt(8999)+1000;
		int num = new Random().nextInt(4)+1;
		InetAddress local = InetAddress.getLocalHost();
		String uip = local.getHostAddress();
		if(user.getUemail().isEmpty() || user.getUname().isEmpty() || user.getUpassword().isEmpty()) {
			return result;
		}else {
			user.setUip(uip);
			user.setUcode(ucode);
			user.setUpassword(passwordEncoder.encode(user.getUpassword()));
			user.setUprofilename("profile_icon_"+num+".png");
			if(userService.getUser(user) == null) {
				userService.registerUser(user);
				result.put("register",Boolean.TRUE);
			}else {
				result.put("register",Boolean.FALSE);
			}
		}
		return result;
		
		
	}
	
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Object> login(@RequestBody UserVo user,HttpSession session){
		Map<String, Object> result = new HashMap<>();
		try {
			if(user.getUemail().isEmpty() || user.getUpassword().isEmpty()) {
				return result;	
			}else {
				UserVo login = userService.getUser(user);
				if(login.getUemail() != null) {
					result.put("idCheck",Boolean.TRUE);
					if(passwordEncoder.matches(user.getUpassword(),login.getUpassword())) {
						user.setUstatus(1);
						user.setUemail(user.getUemail());
						userService.statusUpdate(user);
						result.put("passCheck",Boolean.TRUE);
						if(session.getAttribute("loginId") != null) {
							session.removeAttribute("loginId");
						}else {
							session.setAttribute("loginId", login);
						}
					}else {
						result.put("passCheck",Boolean.FALSE);
					}
				}
			}
			return result;
		} catch (NullPointerException e) {
			result.put("idCheck",Boolean.FALSE);
			return result;
		}
		
	}
	
	@PutMapping("/condition")
	@ResponseBody
	public Map conditionUpdate(@RequestBody UserVo us,HttpSession session){
		Map<String, Object> result = new HashMap<>();
		UserVo users = new UserVo();
		UserVo user = (UserVo)session.getAttribute("loginId");
		users.setUemail(user.getUemail());
		users.setUmystatus(us.getUmystatus());
		int success = userService.mystatusUpdate(users);
		result.put("result", Boolean.TRUE);
		result.put("data", us.getUmystatus());
		return result;
	}
	
	@PostMapping("/info")
	@ResponseBody
	public Map userInfo(HttpSession session){
		Map<String, Object> result = new HashMap<>();
		UserVo info = new UserVo();
		UserVo user = (UserVo)session.getAttribute("loginId");
		info.setUemail(user.getUemail());
		info = userService.userInfo(info);
		result.put("result", Boolean.TRUE);
		result.put("data", info);
		return result;
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public Map userLogout(HttpSession session){
		Map<String, Object> result = new HashMap<>();
		UserVo user = (UserVo)session.getAttribute("loginId");
		UserVo out = new UserVo();
		out.setUstatus(0);
		out.setUemail(user.getUemail());
		int success = userService.statusUpdate(out);
		if(success == 1) {
			session.invalidate();
			result.put("result", Boolean.TRUE);
		}
		
		return result;
	}
	
	@PostMapping("/modify")
	@ResponseBody
	public Map userModify(@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam(value="npassword",required=false) String npassword,HttpSession session){
		Map<String, Object> result = new HashMap<>();
		UserVo login = new UserVo();
		UserVo user = (UserVo)session.getAttribute("loginId");
		login.setUemail(user.getUemail());
		login = userService.getUser(login);
		if(passwordEncoder.matches(password,login.getUpassword())) {
			if(npassword == null) {
				login.setUname(name);
				login.setUpassword(passwordEncoder.encode(password));
				login.setUemail(user.getUemail());
			}else {
				login.setUname(name);
				login.setUpassword(passwordEncoder.encode(npassword));
				login.setUemail(user.getUemail());
			}
			userService.userModify(login);
			result.put("result", Boolean.TRUE);
		}else {
			result.put("result", Boolean.FALSE);
		}
			
		
		return result;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Map userDelete(@RequestParam("password") String password,HttpSession session){
		Map<String, Object> result = new HashMap<>();
		UserVo login = new UserVo();
		UserVo user = (UserVo)session.getAttribute("loginId");
		login.setUemail(user.getUemail());
		login = userService.getUser(login);
		if(passwordEncoder.matches(password,login.getUpassword())) {
			session.invalidate();
			userService.deleteUser(user.getUemail());
			result.put("result", Boolean.TRUE);
		}else {
			result.put("result", Boolean.FALSE);
		}
			
		
		return result;
	}
	
}
