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
public class UpdateNoteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/note/update.do")
	@ResponseBody
	public NoteResult execute(String noteId, String title, String body) {
		NoteResult result=noteService.updateNote(noteId,title,body);
		
		return result;
	}
	
}
