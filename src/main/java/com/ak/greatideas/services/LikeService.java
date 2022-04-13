package com.ak.greatideas.services;

import org.springframework.stereotype.Service;

import com.ak.greatideas.models.Like;
import com.ak.greatideas.repositories.LikeRepo;

@Service
public class LikeService {

	private final LikeRepo likeRepo;
	
	public LikeService(LikeRepo likeRepo) {
		this.likeRepo= likeRepo;
	}
	
	public Like findCount(Long id) {
		return likeRepo.findCount(id);
	}
}
