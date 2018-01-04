package com.balu.interests.db.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseMsg {

	private String text;
	private int status;
	private String create_dttm;
	private String update_dttm;
	private int validity_mins;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreate_dttm() {
		return create_dttm;
	}
	public void setCreate_dttm(String create_dttm) {
		this.create_dttm = create_dttm;
	}
	public String getUpdate_dttm() {
		return update_dttm;
	}
	public void setUpdate_dttm(String update_dttm) {
		this.update_dttm = update_dttm;
	}
	public int getValidity_mins() {
		return validity_mins;
	}
	public void setValidity_mins(int validity_mins) {
		this.validity_mins = validity_mins;
	}

	public BaseMsg() {
		// TODO Auto-generated constructor stub
	}
	
}
