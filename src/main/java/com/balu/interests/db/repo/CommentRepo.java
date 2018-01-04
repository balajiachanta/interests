package com.balu.interests.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.balu.interests.db.entity.Comment;

@RepositoryRestResource
public interface CommentRepo extends JpaRepository<Comment, Long>{

	@Query("select c from Comment c where opinion_fk=?1")
	List<Comment> findAll(Long opinion);

	
	
}
