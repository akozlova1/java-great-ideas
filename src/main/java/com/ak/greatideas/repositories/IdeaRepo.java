package com.ak.greatideas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ak.greatideas.models.Idea;

@Repository
public interface IdeaRepo extends CrudRepository<Idea, Long> {
List<Idea> findAll();
	
	void deleteById(Long id);
	
	Idea save(Idea x);
	
	Optional<Idea> findById(Long id);
	
}
