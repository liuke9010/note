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
public class SearchShareNoteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/note/search_share.do")
	@ResponseBody
	public NoteResult execute(String keyword, int page) {
		
		NoteResult result=noteService.searchShareNote(keyword,page);
		return result;
	}
}
