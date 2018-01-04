package com.balu.interests.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="int_app_features")
public class Features {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long fet_id;
	private String fet_name;
	private Long app_id;
	private LocalDateTime create_dttm;
	private LocalDateTime update_dttm;

	private String created_user;
	private String updated_user;

	public LocalDateTime getCreate_dttm() {
		return create_dttm;
	}
	public void setCreate_dttm(LocalDateTime create_dttm) {
		this.create_dttm = create_dttm;
	}
	public LocalDateTime getUpdate_dttm() {
		return update_dttm;
	}
	public void setUpdate_dttm(LocalDateTime update_dttm) {
		this.update_dttm = update_dttm;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getUpdated_user() {
		return updated_user;
	}
	public void setUpdated_user(String updated_user) {
		this.updated_user = updated_user;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	private int status;


	
	
	public Long getFet_id() {
		return fet_id;
	}
	public void setFet_id(Long fet_id) {
		this.fet_id = fet_id;
	}
	public String getFet_name() {
		return fet_name;
	}
	public void setFet_name(String fet_name) {
		this.fet_name = fet_name;
	}
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
}
