package com.balu.interests.response;

import java.util.List;


public class GenericResponse {

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToddayDate() {
		return toddayDate;
	}
	public void setToddayDate(String toddayDate) {
		this.toddayDate = toddayDate;
	}
	public List<String> getTopics() {
		return topics;
	}
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	private String userName;
	private String toddayDate;
	private List<String> topics;
	public GenericResponse(String userName, String toddayDate, List<String> topics) {
		super();
		this.userName = userName;
		this.toddayDate = toddayDate;
		this.topics = topics;
	}
	public GenericResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
