package com.note.apple.controller.user;

import com.note.apple.data.NoteResult;
import com.note.apple.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@CrossOrigin
@Controller//扫描
public class UserAddController {
	@Resource	//注入
	private UserService userService;
	
	@RequestMapping("/user/add.do")//请求路径
	@ResponseBody//返回JSON数据
	public NoteResult execute(String name, String nick, String password) {
		NoteResult result=userService.addUser(name, nick, password);
		
		return result;
	}
}
