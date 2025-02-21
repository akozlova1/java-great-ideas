package com.ak.greatideas.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ak.greatideas.models.User;
import com.ak.greatideas.repositories.LikeRepo;
import com.ak.greatideas.repositories.UserRepo;


@Service
public class UserService {
    private final UserRepo userRepo;
    private final LikeRepo likeRepo;
    
    public UserService(
    		UserRepo userRepo,
    		LikeRepo likeRepo
    		) {
        this.userRepo = userRepo;
        this.likeRepo = likeRepo;
    }
    
    //register & hash pw
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }

	// find user by email
	public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
        	// if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

	public List<User> allUsers() {

		return userRepo.findAll();
	}
	
	public List<User> findByLikeId(Long id){
		return userRepo.findByLikeId(id);
	}
}


