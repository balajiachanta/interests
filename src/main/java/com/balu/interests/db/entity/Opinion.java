package com.balu.interests.db.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="int_opinion")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Opinion extends BaseMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Opinion() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long opinion_id;
	
	
	public Long getOpinion_id() {
		return opinion_id;
	}

	public void setOpinion_id(Long opinion_id) {
		this.opinion_id = opinion_id;
	}

	@OneToOne
    @JoinColumn(name = "topic_fk", nullable = false,referencedColumnName="topic")
	//@JsonBackReference
    private ForumListEntity forumEntity;
	



	public ForumListEntity getForumEntity() {
		return forumEntity;
	}

	public void setForumEntity(ForumListEntity forumEntity) {
		this.forumEntity = forumEntity;
	}

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "opinion")
    private Set<Comment> comments = new HashSet<Comment>();

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	
	
}
