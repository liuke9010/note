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
public class ShareNoteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/note/share.do")
	@ResponseBody
	public NoteResult execute(String noteId) {
		NoteResult result=noteService.shareNote(noteId);
		return result;
	}
	//TODO

}
