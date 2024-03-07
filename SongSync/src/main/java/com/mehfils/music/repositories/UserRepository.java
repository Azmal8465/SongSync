package com.mehfils.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehfils.music.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	public Users findByEmail(String email);
	
}
