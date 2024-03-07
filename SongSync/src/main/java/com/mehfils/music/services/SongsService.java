package com.mehfils.music.services;

import java.util.List;

import com.mehfils.music.entities.Songs;

public interface SongsService {

	public String addSongs(Songs song);
	
	public boolean songExist(String name);
	
	public List<Songs> fetchAllSongs();

	public void updateSong(Songs song);
}
