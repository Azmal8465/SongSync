package com.mehfils.music.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehfils.music.entities.PlayList;
import com.mehfils.music.repositories.PlayListRepository;

@Service
public class PlayListServiceImplementation implements PlayListService{

	@Autowired
	PlayListRepository prepo;
	
	@Override
	public void addPlaylist(PlayList playlist) {
		prepo.save(playlist);
		
	}

	@Override
	public List<PlayList> fetchPlaylists() {
		return prepo.findAll();
	}

}
