package com.note.apple.controller.note;

import com.note.apple.data.NoteResult;
import com.note.apple.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@CrossOrigin
@Controller
public class AddNoteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/note/addNote.do")
	@ResponseBody
	public NoteResult execute(String userId, String bookId, String noteName) {
		NoteResult result=noteService.addNote(userId,bookId, noteName);
		return result;
	}
	
}
