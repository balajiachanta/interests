package com.balu.interests.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReplyBean {

	private String name;
	private String text;
	private long likes;
	
	private List<ReplyBean> childReplys;
}
