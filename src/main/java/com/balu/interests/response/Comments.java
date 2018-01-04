package com.balu.interests.response;

import java.util.List;


public class Comments {

	private String name;
	private String text;
	private long likes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public List<ReplyBean> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyBean> replies) {
		this.replies = replies;
	}

	private List<ReplyBean> replies;
}
