package com.note.apple.controller.note;

import javax.annotation.Resource;
import com.note.apple.data.NoteResult;
import com.note.apple.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller//扫描
public class LoadNoteController {
	
	@Resource//注入
	private NoteService noteService;
	@RequestMapping("/note/loadnotes.do")
	@ResponseBody
	public NoteResult execute(String bookId) {
		NoteResult result=noteService.loadBookNotes(bookId);
		return result;
	}
}
