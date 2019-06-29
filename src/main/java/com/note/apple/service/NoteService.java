package com.note.apple.service;


import com.note.apple.data.NoteResult;

public interface NoteService {
	//加载笔记列表
	public NoteResult loadBookNotes(String bookId);
	//加载笔记
	public NoteResult loadNote(String noteId);
	//保存笔记
	public NoteResult updateNote(String noteId, String title, String body);
	//新建笔记
	public NoteResult addNote(String userId, String bookId, String noteName);
	//删除笔记（将笔记放入回收站）
	public NoteResult deleteNote(String noteId);
	//移动笔记
	public NoteResult moveNote(String noteId, String bookId);
	//分享笔记
	public NoteResult shareNote(String noteId);
	//搜索已分享的笔记
	public NoteResult searchShareNote(String keyword, int page);
	//显示分享列表的笔记的详细信息
	public NoteResult loadShareNote(String shareId);
	
}
