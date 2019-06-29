package com.note.apple.controller.note;

import javax.annotation.Resource;
import com.note.apple.data.NoteResult;
import com.note.apple.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class MoveNoteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/note/move.do")
	@ResponseBody
	public NoteResult execute(String bookId, String noteId) {
		NoteResult result=noteService.moveNote(bookId, noteId);
		
		return result;
	}
	
}
