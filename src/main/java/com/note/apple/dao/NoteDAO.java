package com.note.apple.dao;

import com.note.apple.entity.Note;
import java.util.List;
import java.util.Map;

public interface NoteDAO {
	//根据笔记本id查找笔记本列表
	public List<Note> findByBookId(String bookId);
	//根据笔记ID查找
	public Note findById(String noteId);
	//根据当前笔记ID保存笔记
	public int updateNote(Note note);
	//新建笔记
	public int save(Note note);
	//删除笔记（将笔记放入回收站）
	public int updateStatus(String noteId);
	//移动笔记
	public int updateBookId(Note note);
	//更新已分享状态
	public int updateTypeId(String noteId);
	//组合查询
	public List<Note> findNotes(@SuppressWarnings("rawtypes") Map params);
}
