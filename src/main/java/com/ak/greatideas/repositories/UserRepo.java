package com.ak.greatideas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ak.greatideas.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	List<User> findAll();
	
	void deleteById(Long id);
	
	User save(User x);
	
	Optional<User> findById(Long id);
	
}
