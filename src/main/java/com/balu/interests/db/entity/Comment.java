package com.balu.interests.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="int_comment")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Comment extends BaseMsg{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long comments_id;
	
	public Long getComments_id() {
		return comments_id;
	}

	public void setComments_id(Long comments_id) {
		this.comments_id = comments_id;
	}

	@ManyToOne
	@JoinColumn(name = "opinion_fk", nullable = false,referencedColumnName="opinion_id")
    private Opinion opinion;

	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	
	
}
