package com.mehfils.music.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehfils.music.entities.Songs;
import com.mehfils.music.repositories.SongsRepository;

@Service
public class SongsServiceImplementation implements SongsService {

	@Autowired
	SongsRepository songRepo;

	@Override
	public String addSongs(Songs song) {
		songRepo.save(song);
		return "song added";
	}

	@Override
	public boolean songExist(String name) {
		Songs song = songRepo.findByName(name);
		if (song == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Songs> fetchAllSongs() {
		List<Songs> songlist = songRepo.findAll();
		return songlist;
	}

	@Override
	public void updateSong(Songs song) {
		songRepo.save(song);

	}

}
