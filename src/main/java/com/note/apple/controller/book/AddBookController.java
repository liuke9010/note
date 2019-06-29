package com.note.apple.controller.book;

import javax.annotation.Resource;

import com.note.apple.data.NoteResult;
import com.note.apple.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class AddBookController {
	@Resource
	private BookService bookService;
	@RequestMapping("/book/add.do")
	@ResponseBody
	public NoteResult execute(String userId, String bookName) {
		NoteResult result=bookService.addBook(userId, bookName);
		return result;
	}
	
}
