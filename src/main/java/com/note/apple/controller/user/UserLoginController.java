package com.note.apple.controller.user;


import com.note.apple.data.NoteResult;
import com.note.apple.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller		//扫描
@RequestMapping("/user")
public class UserLoginController {
	@Resource	//注入
	private UserService userService;
	@RequestMapping("/login.do")
	@ResponseBody   //JSON输出
	public NoteResult execute(String name, String password) {
		//调用UserService处理登录
		NoteResult result=userService.checkLogin(name, password);
		
		
		return result;
	}
	
	
}
