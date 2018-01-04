package com.balu.interests.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.balu.interests.db.entity.Features;

@RepositoryRestResource
public interface FeatureRepo extends JpaRepository<Features, Long>{

	
	
}
