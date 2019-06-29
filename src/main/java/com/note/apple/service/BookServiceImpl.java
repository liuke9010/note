package com.note.apple.service;

import com.note.apple.dao.BookDAO;
import com.note.apple.data.NoteResult;
import com.note.apple.entity.Book;
import com.note.apple.util.NoteUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import java.sql.Timestamp;


@Service("bookService")//扫描
public class BookServiceImpl implements BookService {
	@Resource//注入
	private BookDAO bookDAO;
	public NoteResult loadUserBooks(String userId) {
		//按用户id查询笔记本信息
		List<Book> list=bookDAO.findByUserId(userId);
		//创建返回结果
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("查询完毕");
		result.setData(list);
		return result;
	}
	public NoteResult addBook(String userId, String bookName) {
		// 新建笔记本
		Book book=new Book();
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		//创建时间的类型
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		//设置笔记本id
		String bookId= NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		NoteResult result=new NoteResult();
		int row=bookDAO.save(book);
		if(row==1) {//创建成功
			result.setStatus(0);
			result.setMsg("创建笔记本成功");
			result.setData(book);
		}else {//创建失败
			result.setStatus(1);
			result.setMsg("创建笔记本失败");
		}
		
		return result;
	}

}
