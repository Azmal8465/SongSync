package com.mehfils.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehfils.music.entities.Songs;

public interface SongsRepository extends JpaRepository<Songs, Integer> {
	public Songs findByName(String name);
}
