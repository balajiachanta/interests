package com.balu.interests.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.balu.interests.db.entity.Opinion;

@RepositoryRestResource
public interface OpinionRepo extends JpaRepository<Opinion, Long>{

	@Query("select o from Opinion o where topic_fk=?1")
	List<Opinion> findAll(Long topic);

}
