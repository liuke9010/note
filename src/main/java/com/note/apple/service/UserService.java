package com.note.apple.service;


import com.note.apple.data.NoteResult;

public interface UserService {
	//登录
	public NoteResult checkLogin(String name, String password);
	//注册
	public NoteResult addUser(String name, String nick, String password);
}
