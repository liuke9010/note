package com.note.apple.data;

import java.io.Serializable;

/**
 * 结果对象
 * @author Liu Ke
 *
 */
public class NoteResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;		//0表示成功，其他的表示失败
	private String msg;		//
	private Object data;	//
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
