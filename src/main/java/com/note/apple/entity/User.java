
package com.note.apple.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表实体类
 * @author Liu Ke
 *
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cn_user_id;			//用户ID
	private String cn_user_name;		//用户名
	private String cn_user_password;	//密码
	private String cn_user_token;		//令牌
	private String cn_user_nick;		// 昵称
//追加关联属性，用于加载相关的多个Book
	private List<Book> books;
	
	
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_user_name() {
		return cn_user_name;
	}
	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}
	public String getCn_user_password() {
		return cn_user_password;
	}
	public void setCn_user_password(String cn_user_password) {
		this.cn_user_password = cn_user_password;
	}
	public String getCn_user_token() {
		return cn_user_token;
	}
	public void setCn_user_token(String cn_user_token) {
		this.cn_user_token = cn_user_token;
	}
	public String getCn_user_nick() {
		return cn_user_nick;
	}
	public void setCn_user_nick(String cn_user_nick) {
		this.cn_user_nick = cn_user_nick;
	}
	@Override
	public String toString() {
		return "User [用户ID=" + cn_user_id + ", 用户名=" + cn_user_name + ", 密码="
				+ cn_user_password + ", 令牌=" + cn_user_token + ", 昵称=" + cn_user_nick + "]";
	}
	
	
}
