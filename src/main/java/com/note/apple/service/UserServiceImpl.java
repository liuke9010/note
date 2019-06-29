package com.note.apple.service;

import com.note.apple.dao.UserDAO;
import com.note.apple.data.NoteResult;
import com.note.apple.entity.User;
import com.note.apple.error.BusinessException;
import com.note.apple.error.EnumBusinessError;
import com.note.apple.util.NoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//扫描
@Service("userService")
public class UserServiceImpl implements UserService {
	//登录
//	@Resource(name="userDAO")
	@Autowired
	private UserDAO userDAO;//注入
	public NoteResult checkLogin(String name, String password) {
		User user=userDAO.findByName(name);
		NoteResult result=new NoteResult();
		//判断用户名
		if(user==null) {
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//判断密码
//		添加测试的错误，检测错误日志是否能写出
//		String s=null;
//		s.length();
		//将用户输入的明文加密
		String md5_pwd;
		try {
			md5_pwd = NoteUtil.md5(password);
			if(!user.getCn_user_password().equals(md5_pwd)) {
				result.setStatus(2);
				result.setMsg("密码错误");
				return result;
			}
		} catch (Exception e) {
			throw new BusinessException(EnumBusinessError.ENCRYPT_ERROR);
		}
		//
		
		//登陆成功
		result.setStatus(0);
		result.setMsg("登陆成功");
		user.setCn_user_password("");//把密码屏蔽不返回
		result.setData(user);//返回user信息
		return result;
	}
	
	//注册
	public NoteResult addUser(String name, String nick, String password) {
		//检查用户名是否已注册
		NoteResult result=new NoteResult();
		User has_user=userDAO.findByName(name);
		if(has_user!=null) {
			result.setStatus(1);
			result.setMsg("用户名已被占用");
			return result;
		}
		
		
		User user=new User();
		user.setCn_user_name(name);//设置用户名
		user.setCn_user_nick(nick);//设置用户昵称
		//密码加密
		String md5_pwd;
		try {
			md5_pwd = NoteUtil.md5(password);
		} catch (Exception e) {
			throw new BusinessException(EnumBusinessError.ENCRYPT_ERROR);
		}
		user.setCn_user_password(md5_pwd);
		String userId=NoteUtil.createId();//随机生成id
		user.setCn_user_id(userId);//设置用户id
		userDAO.save(user);
		//创建返回结果
		
		result.setStatus(0);
		result.setMsg("注册成功");
		
		return result;
	}

}
