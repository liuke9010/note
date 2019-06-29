package com.note.apple.service;


import com.note.apple.data.NoteResult;

public interface BookService {
	//根据用户id加载笔记本列表
	public NoteResult loadUserBooks(String userId);
	//新建笔记本
	public NoteResult addBook(String userId, String bookName);
	
}
