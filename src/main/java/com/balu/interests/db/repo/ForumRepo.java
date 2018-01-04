package com.balu.interests.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.balu.interests.db.entity.ForumListEntity;

@RepositoryRestResource
public interface ForumRepo extends JpaRepository<ForumListEntity, Long>{

	@Query("select f from ForumListEntity f where app_id=?1 and topic=?2 and status=1")
	public List<ForumListEntity> findAll(long topicId,long appId);
	
}
