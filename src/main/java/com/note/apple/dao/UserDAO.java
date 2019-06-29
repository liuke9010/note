package com.note.apple.dao;

import com.note.apple.entity.User;

public interface UserDAO {
	//根据用户名查找用户信息（登录）
	public User findByName(String name);
	//保存新增用户信息（注册）
	public void save(User user);

}
