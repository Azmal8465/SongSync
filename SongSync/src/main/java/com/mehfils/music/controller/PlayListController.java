package com.mehfils.music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.mehfils.music.entities.PlayList;
import com.mehfils.music.entities.Songs;
import com.mehfils.music.services.PlayListService;
import com.mehfils.music.services.SongsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayListController {
	@Autowired
	PlayListService pserv;

	@Autowired
	SongsService sserv;

	@GetMapping("/createplaylist")
	public String createplaylist(Model model) {
		List<Songs> songslist = sserv.fetchAllSongs();
		model.addAttribute("songslist", songslist);
		return "createplaylist";
	}

	@PostMapping("/addplaylist")
	public String addPlayList(@ModelAttribute PlayList playlist) {
		pserv.addPlaylist(playlist);
		List<Songs> songList = playlist.getSongs();
		for (Songs song : songList) {
			song.getPlaylist().add(playlist);
			sserv.updateSong(song);
		}

		return "playlistsuccess";
	}
	
	@GetMapping("/viewplaylist")
	public String viewPlayList(Model model) {
		List<PlayList> playlist=pserv.fetchPlaylists();
		model.addAttribute("playlist", playlist);
		return "viewPlayLists";
		
	}

}
