package com.mehfils.music.services;

import com.mehfils.music.entities.Users;

public interface UserService {

	public String addUser(Users user) ;
	
	public boolean emailExists(String email);
	
	public boolean validateUser(String email,String password);
	
	public String getRoll(String email);

	public Users getUser(String email);

	public void updateUser(Users user);

	
	
}
