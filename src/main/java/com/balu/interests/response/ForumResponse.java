package com.balu.interests.response;

import java.util.List;

import com.balu.interests.db.entity.ForumListEntity;

public class ForumResponse extends GenericResponse {

	
	
	private List<ForumListEntity> forumTopics;

	public List<ForumListEntity> getForumTopics() {
		return forumTopics;
	}

	public void setForumTopics(List<ForumListEntity> forumTopics) {
		this.forumTopics = forumTopics;
	}
	

}
