package com.balu.interests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.balu.interests.auth.JWTUserService;
import com.balu.interests.db.entity.Comment;
import com.balu.interests.db.entity.FeatAppTopic;
import com.balu.interests.db.entity.Features;
import com.balu.interests.db.entity.ForumListEntity;
import com.balu.interests.db.entity.User;
import com.balu.interests.db.repo.FeatAppTopicRepo;
import com.balu.interests.db.repo.FeatureRepo;
import com.balu.interests.db.repo.ForumRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@ComponentScan(basePackages="com.*")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class InterestsApplication implements CommandLineRunner {


	@Autowired
	private ForumRepo forumRepo;
	
	@Autowired
	private FeatureRepo featureRepo;
	
	@Autowired
	private JWTUserService jwtUserService;
	
	@Autowired
	private FeatAppTopicRepo featAppTopicRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(InterestsApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Features feature = new Features();
		feature.setFet_name("Forum");
		feature.setApp_id(1L);
		feature = featureRepo.save(feature);
		
		ForumListEntity recone = new ForumListEntity();
		recone.setFeature_id(feature.getFet_id());
		recone.setTopic_name("whoami");
		recone.setApp_id((long) 1);
		recone.setStatus(1);
		recone.setAdditional_information("funnyguy");
		//recone.setCreate_dttm(LocalDateTime.now());
		//recone.setUpdate_dttm(LocalDateTime.now());
		
		//"whoami",1,1,"funnyguy"
		
		recone = forumRepo.save(recone);
		
		
		FeatAppTopic fat= new FeatAppTopic();
		fat.setFet_id(feature.getFet_id());
		fat.setApp_id(recone.getApp_id());
		fat.setTopic_id(recone.getTopic());
		
		featAppTopicRepo.save(fat);
		
		ForumListEntity rectwo = new ForumListEntity();
		rectwo.setFeature_id(feature.getFet_id());
		rectwo.setTopic_name("goal");
		rectwo.setApp_id((long) 1);
		rectwo.setStatus(1);
		rectwo.setAdditional_information("setting a goal");
		rectwo=forumRepo.save(rectwo);
		
		FeatAppTopic fat1= new FeatAppTopic();
		fat1.setFet_id(feature.getFet_id());
		fat1.setApp_id(rectwo.getApp_id());
		fat1.setTopic_id(rectwo.getTopic());
		
		featAppTopicRepo.save(fat1);
		
		/*ForumListEntity rectwo = new ForumListEntity("goal",1,1,"setting a goal");
		rectwo.setFeature_id(feature.getFet_id());
		forumRepo.save(rectwo);
		
		ForumListEntity recthree = new ForumListEntity("inspiration",1,1,"it is imp");
		recthree.setFeature_id(feature.getFet_id());
		forumRepo.save(recthree);
		
		ForumListEntity four = new ForumListEntity("careless",1,1,"can not be");
		four.setFeature_id(feature.getFet_id());
		forumRepo.save(four);*/
		
		ObjectMapper om= new ObjectMapper();
		System.out.println(om.writeValueAsString(new Comment()));
		
		User user1=new User();
		user1.setUsername("balu").setEnabled(true).setPassword("userpass");
		
		User user2=new User();
		user2.setUsername("balaji").setEnabled(true).setPassword("userpassone");
		
		jwtUserService.register(user1);
		jwtUserService.register(user2);
			
		
		
	}
	
	
}
