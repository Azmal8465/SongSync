package com.mehfils.music.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehfils.music.entities.Users;
import com.mehfils.music.repositories.UserRepository;

@Service
public class UserSericeImplementation implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public String addUser(Users user) {
		userRepo.save(user);
		return "User is Created and saved";
	}

	@Override
	public boolean emailExists(String email) {
		if (userRepo.findByEmail(email) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user = userRepo.findByEmail(email);
		String db_password = user.getPassword();
		if (db_password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getRoll(String email) {
		Users user=userRepo.findByEmail(email);
		String role=user.getRole();
		return role;
	}

	@Override
	public Users getUser(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		userRepo.save(user);
		
	}

}
