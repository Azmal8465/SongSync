package com.mehfils.music.services;

import java.util.List;

import com.mehfils.music.entities.PlayList;

public interface PlayListService {

	public void addPlaylist(PlayList playlist);

	public List<PlayList> fetchPlaylists();

}
