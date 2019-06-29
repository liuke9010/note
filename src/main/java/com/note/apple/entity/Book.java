package com.note.apple.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 笔记本实体类
 * 
 * @author Liu Ke
 *
 */
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cn_notebook_id; // 笔记本id
	private String cn_user_id; // 用户id
	private String cn_notebook_type_id; // 笔记本类型
	private String cn_notebook_name; // 笔记本名称
	private String cn_notebook_desc; // 笔记本描述
	private Timestamp cn_notebook_createtime; // 笔记本创建时间
	//追加关联属性，关联User
	private User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCn_notebook_id() {
		return cn_notebook_id;
	}

	public void setCn_notebook_id(String cn_notebook_id) {
		this.cn_notebook_id = cn_notebook_id;
	}

	public String getCn_user_id() {
		return cn_user_id;
	}

	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}

	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}

	public void setCn_notebook_type_id(String cn_notebook_type_id) {
		this.cn_notebook_type_id = cn_notebook_type_id;
	}

	public String getCn_notebook_name() {
		return cn_notebook_name;
	}

	public void setCn_notebook_name(String cn_notebook_name) {
		this.cn_notebook_name = cn_notebook_name;
	}

	public String getCn_notebook_desc() {
		return cn_notebook_desc;
	}

	public void setCn_notebook_desc(String cn_notebook_desc) {
		this.cn_notebook_desc = cn_notebook_desc;
	}

	public Timestamp getCn_notebook_createtime() {
		return cn_notebook_createtime;
	}

	public void setCn_notebook_createtime(Timestamp cn_notebook_createtime) {
		this.cn_notebook_createtime = cn_notebook_createtime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Book [cn_notebook_id=" + cn_notebook_id + ", cn_user_id=" + cn_user_id + ", cn_notebook_type_id="
				+ cn_notebook_type_id + ", cn_notebook_name=" + cn_notebook_name + ", cn_notebook_desc="
				+ cn_notebook_desc + ", cn_notebook_createtime=" + cn_notebook_createtime + "]";
	}

}
