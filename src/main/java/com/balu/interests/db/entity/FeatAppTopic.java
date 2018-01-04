package com.balu.interests.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="int_feature_app_topic")
public class FeatAppTopic {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long feat_app_tc_id;
	
	public Long getFeat_app_tc_id() {
		return feat_app_tc_id;
	}
	public void setFeat_app_tc_id(Long feat_app_tc_id) {
		this.feat_app_tc_id = feat_app_tc_id;
	}
	private long fet_id;
	
	private long app_id;
	private long topic_id;
	public long getFet_id() {
		return fet_id;
	}
	public void setFet_id(long fet_id) {
		this.fet_id = fet_id;
	}
	public long getApp_id() {
		return app_id;
	}
	public void setApp_id(long app_id) {
		this.app_id = app_id;
	}
	public long getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(long topic_id) {
		this.topic_id = topic_id;
	}
	
	
}
