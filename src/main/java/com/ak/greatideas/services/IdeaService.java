package com.ak.greatideas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ak.greatideas.models.Idea;
import com.ak.greatideas.models.Like;
import com.ak.greatideas.repositories.IdeaRepo;
import com.ak.greatideas.repositories.LikeRepo;

@Service
public class IdeaService {

	private final IdeaRepo ideaRepository;
	private final LikeRepo likeRepo;

	public IdeaService(IdeaRepo ideaRepository, LikeRepo likeRepo) {
		this.ideaRepository = ideaRepository;
		this.likeRepo= likeRepo;
	}

	public List<Idea> allIdeas() {
		return ideaRepository.findAll();
	}

	public Idea createIdea(Idea x) {
		return ideaRepository.save(x);
	}

	public Idea findIdea(Long id) {
		Optional<Idea> optionalIdea = ideaRepository.findById(id);
		if(optionalIdea.isPresent()) {
			return optionalIdea.get();
		} else {
			return null;
		}
	}
	
	public Idea updateIdea(Idea x) {
		return ideaRepository.save(x);
		
	}
	
	public void deleteIdea(Long id) {
		ideaRepository.deleteById(id);
	}
	
	public Like relationship(Like x) {
		return likeRepo.save(x);
	}
}

