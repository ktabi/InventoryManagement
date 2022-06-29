package com.gm.inventorymanagement.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.gm.inventorymanagement.models.AuthUser;
import com.gm.inventorymanagement.models.User;
import com.gm.inventorymanagement.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User register(User newUser, BindingResult result) {
		if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
			result.rejectValue("email", "Email already in use!");
		}
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Password must match!");
		}
		if(result.hasErrors()) {
			return null;
		} else {
			String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashed);
			return userRepo.save(newUser);
		}
		
	}
	
	
public User login(AuthUser newLogin, BindingResult result) {
	if(result.hasErrors()) {
		return null;
	}
	
	Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
	
	if(!potentialUser.isPresent()) {
		result.rejectValue("email", "Unique", "Invalid Credentials!");
		return null;
	}
	
	User user = potentialUser.get();
	
	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
		result.rejectValue("password", "Matches", "Invalid Credentials!");
	}
	if(result.hasErrors()) {
		return null;
	} else {
		return user;
	}
}


//=============================
//Find One User based on Id
//=============================

	public User getUserInfo(Long id) {
//		optional is used to find one thing. if it exists will return if not will return null.
		Optional<User> optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
			return null;
		}
 	}

}
