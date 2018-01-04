package com.balu.interests.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="int_forum_list")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class ForumListEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long topic;
	public Long getTopic() {
		return topic;
	}

	public void setTopic(Long topic) {
		this.topic = topic;
	}

	private String topic_name;
	
	private LocalDateTime create_dttm;
	
	private Long app_id;
	
	private LocalDateTime update_dttm;
	
	private String created_user;
	
	private int status;
	private String tag_line;
	private int validity_miutes;
	private int preferred_order;
	
	private String additional_information;
	private int autoapprove;
	private long feature_id;
	
	
	@OneToOne
	 @JoinColumn(name = "topic")
	//@JsonManagedReference
	private Opinion opinion;
	
	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	public ForumListEntity() {}
	
	/*public ForumListEntity(Long topic_id, String topic_name, String create_dttm, int app_id, String update_dttm,
			String created_user, int status, String tag_line, int validity_miutes, int preferred_order,
			String additional_information, int autoapprove) {
		super();
		this.topic = topic_id;
		this.topic_name = topic_name;
		this.create_dttm = create_dttm;
		this.app_id = app_id;
		this.update_dttm = update_dttm;
		this.created_user = created_user;
		this.status = status;
		this.tag_line = tag_line;
		this.validity_miutes = validity_miutes;
		this.preferred_order = preferred_order;
		this.additional_information = additional_information;
		this.autoapprove = autoapprove;
	}
	
	
	public ForumListEntity( String topic_name,int app_id, int status,String additional_information) {
		super();
		this.topic_name = topic_name;
		this.app_id = app_id;
		this.status = status;
		this.additional_information = additional_information;
		
	}
	
	*/
	
	
	
	public String getTopic_name() {
		return topic_name;
	}
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
	
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTag_line() {
		return tag_line;
	}
	public void setTag_line(String tag_line) {
		this.tag_line = tag_line;
	}
	public int getValidity_miutes() {
		return validity_miutes;
	}
	public void setValidity_miutes(int validity_miutes) {
		this.validity_miutes = validity_miutes;
	}
	public int getPreferred_order() {
		return preferred_order;
	}
	public void setPreferred_order(int preferred_order) {
		this.preferred_order = preferred_order;
	}
	public String getAdditional_information() {
		return additional_information;
	}
	public void setAdditional_information(String additional_information) {
		this.additional_information = additional_information;
	}
	public int getAutoapprove() {
		return autoapprove;
	}
	public void setAutoapprove(int autoapprove) {
		this.autoapprove = autoapprove;
	}

	
	public long getFeature_id() {
		return feature_id;
	}

	public void setFeature_id(long feature_id) {
		this.feature_id = feature_id;
	}

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
	
	  
}
