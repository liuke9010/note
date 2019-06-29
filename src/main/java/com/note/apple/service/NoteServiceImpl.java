package com.note.apple.service;

import com.note.apple.dao.NoteDAO;
import com.note.apple.dao.ShareDAO;
import com.note.apple.data.NoteResult;
import com.note.apple.entity.Note;
import com.note.apple.entity.Share;
import com.note.apple.util.NoteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Resource
	private NoteDAO noteDAO;
	@Resource
	private ShareDAO shareDAO;

    /**
     * 加载笔记本中的笔记列表
     * @param bookId
     * @return
     */
	public NoteResult loadBookNotes(String bookId) {
		//按照笔记本ID查询笔记信息
		List<Note> list=noteDAO.findByBookId(bookId);
		//创建返回结果
		NoteResult result= new NoteResult();
		result.setStatus(0);
		result.setMsg("查询完毕");
		result.setData(list);
		log.info("加载笔记列表成功，list={}",list);
		return result;
	}

    /**
     * 加载笔记
     * @param noteId
     * @return
     */
	public NoteResult loadNote(String noteId) {
		//根据笔记id查找笔记的内容
		Note note=noteDAO.findById(noteId);
		//创建返回结果
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("查询完毕");
		result.setData(note);
		log.info("加载笔记<{}>内容成功！",note.getCn_note_title());
		return result;
	}

    /**
     * 更新笔记
     * @param noteId
     * @param title
     * @param body
     * @return
     */
	public NoteResult updateNote(String noteId,String title,String body) {
		//根据当前笔记的ID保存笔记
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
        Timestamp time=new Timestamp(System.currentTimeMillis());//创建时间
		note.setCn_note_last_modify_time(time);
		int rows=noteDAO.updateNote(note);
		NoteResult result=new NoteResult();
		if(rows==1) {//更新成功
			result.setStatus(0);
			result.setMsg("保存笔记成功");
			log.info("更新笔记<{}>成功！",title);
		}else {//更新失败
			result.setStatus(1);
			result.setMsg("保存笔记失败");
            log.info("更新笔记<{}>失败！",title);
		}
		return result;
	}

    /**
     * 创建笔记
     * @param userId
     * @param bookId
     * @param noteName
     * @return
     */
	public NoteResult addNote(String userId,String bookId, String noteName) {
		Note note=new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(noteName);
        Timestamp time=new Timestamp(System.currentTimeMillis());//创建时间
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(time);//最后修改时间与创建时间相同
		String noteId= NoteUtil.createId();//创建笔记id，主键
		note.setCn_note_id(noteId);
		int row=noteDAO.save(note);
		NoteResult result=new NoteResult();
		if(row==1) {//创建笔记成功
			result.setStatus(0);
			result.setMsg("创建笔记成功");
			result.setData(note);
			log.info("创建笔记<{}>成功！",noteName);
		}else {//创建笔记失败
			result.setStatus(1);
			result.setMsg("创建笔记失败");
            log.info("创建笔记<{}>失败！",noteName);
		}
		
		return result;
	}

    /**
     * 删除笔记（放入回收站），cn_note_status_id状态改为2
     * @param noteId
     * @return
     */
	public NoteResult deleteNote(String noteId) {
		NoteResult result=new NoteResult();
		int row=noteDAO.updateStatus(noteId);
		if(row==1) {//成功
			result.setStatus(0);
			result.setMsg("删除笔记成功");
			log.info("删除笔记<{}>成功，已放入回收站！",noteId);
		}else {//失败
			result.setStatus(1);
			result.setMsg("删除笔记失败");
            log.info("删除笔记<{}>失败！",noteId);
		}
		return result;
	}

    /**
     * 移动笔记
     * @param bookId
     * @param noteId
     * @return
     */
	public NoteResult moveNote(String bookId, String noteId) {
		Note note=new Note();
		note.setCn_notebook_id(bookId);
		note.setCn_note_id(noteId);
		NoteResult result=new NoteResult();
		int row=noteDAO.updateBookId(note);
		if(row==1) {//成功
			result.setStatus(0);
			result.setMsg("移动笔记成功");
			log.info("移动笔记<{}>成功！",noteId);
		}else {//失败
			result.setStatus(1);
			result.setMsg("移动笔记失败");
            log.info("移动笔记<{}>失败！",noteId);
		}
		return result;
	}

    /**
     * 分享笔记
     * @param noteId
     * @return
     */
	public NoteResult shareNote(String noteId) {
		// 获取分享的笔记信息
		NoteResult result=new NoteResult();
		Note note=noteDAO.findById(noteId);
		//检查cn_note_type_id，如果是分享状态，直接返回
		if("2".equals(note.getCn_note_type_id())) {
			result.setStatus(1);
			result.setMsg("该笔记已分享过");
			log.info("笔记<{}>已经分享过",noteId);
			return result;
		}
		//更新笔记的cn_note_type_id为已分享,值改为2
		noteDAO.updateTypeId(noteId);
		//添加到分享列表
		Share share=new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		share.setCn_share_id(NoteUtil.createId());
		shareDAO.save(share);
		//创建返回结果
		
		result.setStatus(0);
		result.setMsg("分享成功");
		log.info("笔记<{}>分享成功！",noteId);
		return result;
	}

    /**
     * 搜索已分享的笔记
     * @param keyword
     * @param page
     * @return
     */
	public NoteResult searchShareNote(String keyword,int page) {
		//处理查询条件值，若为空，传入百分号；不为空，传入时前后加百分号
		String title="%";
		if(keyword!=null&&!"".equals(keyword)) {
			title="%"+keyword+"%";
		}
		//计算请求抓取的起点
		if(page<1) {
			page=1;
		}
		int begin=(page-1)*5;
		//封装参数
		Map<String, Object> params=new HashMap<String,Object>();
		params.put("begin", begin);
		params.put("keyword", title);
		List<Share> list=shareDAO.findLikeTitle(params);
		log.info("搜索已分享笔记成功，list={}",list);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("搜索完毕");
		result.setData(list);
		return result;
	}

    /**
     * 加载搜索列表的笔记信息
     * @param shareId
     * @return
     */
	public NoteResult loadShareNote(String shareId) {
		Share share=shareDAO.findById(shareId);
		
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setData(share);
		result.setMsg("加载成功！");
		log.info("加载搜索列表成功");
		return result;
	}

}
