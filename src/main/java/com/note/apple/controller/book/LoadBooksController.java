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
public class LoadBooksController {
	@Resource
	private BookService bookService;
	@RequestMapping("/book/loadbooks.do")
	@ResponseBody
	public NoteResult execute(String userId) {
		NoteResult result=bookService.loadUserBooks(userId);
		return result;
	}
}
