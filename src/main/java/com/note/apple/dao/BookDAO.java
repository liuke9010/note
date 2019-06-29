package com.note.apple.dao;

import com.note.apple.entity.Book;
import java.util.List;

public interface BookDAO {
	//根据用户的id查找笔记本
	public List<Book> findByUserId(String userId);
	//创建新的笔记本
	public int save(Book book);
}
