package com.mehfils.music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mehfils.music.entities.Songs;
import com.mehfils.music.entities.Users;
import com.mehfils.music.services.SongsService;
import com.mehfils.music.services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

	@Autowired
	UserService userServ;
	@Autowired
	SongsService songServ;

	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
		boolean userStatus = userServ.emailExists(user.getEmail());
		if (userStatus == false) {
			userServ.addUser(user);
			return "registersuccess";

		} else {
			return "registerfail";
		}

	}

	@PostMapping("/login")
	public String validateUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		boolean loginStatus = userServ.validateUser(email, password);
		if (loginStatus == true) {

			session.setAttribute("email", email);
			if (userServ.getRoll(email).equals("admin")) {
				return "adminhome";
			} else {
				return "customerhome";
			}
		} else {
			return "loginfail";
		}

	}

	@GetMapping("exploreSongs")
	public String exploreSongs(HttpSession session,Model model) {

		String email = (String) session.getAttribute("email");
		Users user = userServ.getUser(email);

		boolean userStatus = user.isPremium();
		if (userStatus == true) {
			List<Songs> songslist = songServ.fetchAllSongs();
			model.addAttribute("songslist", songslist);
			return "displaySongs";
		} else {
			return "payment";
		}

	}

}
