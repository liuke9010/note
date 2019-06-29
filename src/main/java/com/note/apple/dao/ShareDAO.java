package com.note.apple.dao;

import java.util.List;
import java.util.Map;
import com.note.apple.entity.Share;

public interface ShareDAO {
	//根据笔记id查找
	public void save(Share share);
	//搜索笔记
	public List<Share> findLikeTitle(Map<String, Object> params);
	//显示分享列表的笔记的详细信息
	public Share findById(String shareId);


}
