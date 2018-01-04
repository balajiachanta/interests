package com.balu.interests.forum;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.balu.interests.db.entity.Comment;
import com.balu.interests.db.entity.ForumListEntity;
import com.balu.interests.db.entity.Opinion;
import com.balu.interests.db.repo.CommentRepo;
import com.balu.interests.db.repo.ForumRepo;
import com.balu.interests.db.repo.OpinionRepo;
import com.balu.interests.request.ForumRequest;
import com.balu.interests.response.Comments;
import com.balu.interests.response.ForumResponse;

@RestController
@RequestMapping("/api")
public class ForumService {

	@Autowired
	private ForumRepo forumRepo;
	
	@Autowired
	private OpinionRepo opinionRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	NotificationManager notificationManager;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value="/topics",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ForumResponse listTopics(@RequestHeader(value="x-appname",required=false) String appname,@RequestParam long appId,@RequestParam long topic) {
		List<ForumListEntity> topicList = new ArrayList<ForumListEntity>();

		ForumResponse forumRes = new ForumResponse();
		forumRes.setUserName("master list");


		topicList = forumRepo.findAll(appId,topic);
		forumRes.setForumTopics(topicList);
		return forumRes;

	}

	@PutMapping(value="/topics")
	public void insertTopics(@RequestHeader(value="x-appname",required=false) String appname,@RequestBody ForumRequest request) {
		System.out.println(Thread.currentThread().getName());
		ForumListEntity forum = new ForumListEntity();
		//forum.setTopic_id("8");
		forum.setAdditional_information(request.getText());
		forum.setTopic_name(request.getTopicName());
		forum.setStatus(1);
		forum.setApp_id((long) 1);
		forumRepo.save(forum);

		//notificationManager.sendMail("balajiachanta@gmail.com", "balajiachanta@gmail.com", "New Topic ..", "New topic has been added ");
	}
	
	@PutMapping(value="/opinion")
	public void writeOpinion(@RequestBody Opinion opinion,@RequestParam Long topic) {
		
		ForumListEntity forum = forumRepo.findOne(topic);
		System.out.println("created date "+forum.getCreate_dttm());
		opinion.setForumEntity(forum);
		//opinion.setOpinion_id(forum.getApp_id());
		opinionRepo.save(opinion);
	}

	@GetMapping(value="/opinion")
	public List<Opinion> getOpinion(@RequestParam Long topic) {
		List<Opinion> li = opinionRepo.findAll(topic);
		return li;
	}
	
	@PutMapping(value="/comment")
	public void writeComment(@RequestBody Comments comment,@RequestParam Long opinion) {
		Opinion forum = opinionRepo.findOne(opinion);
		Comment c = new Comment();
		c.setText(comment.getText());
		
		c.setOpinion(forum);
		
		commentRepo.save(c);
	}
	
	@GetMapping(value="/comment")
	public List<Comment> getComments(@RequestParam Long opinion) {
		List<Comment> forum = commentRepo.findAll(opinion);
		
		
		return forum;
	}
}
