package com.ak.greatideas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ak.greatideas.models.Like;

@Repository
public interface LikeRepo extends CrudRepository<Like, Long> {
	Like findCount(Long id);
	
}
