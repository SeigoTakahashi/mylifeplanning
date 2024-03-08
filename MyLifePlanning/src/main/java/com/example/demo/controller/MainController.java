package com.example.demo.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String homeGet() throws IOException {
		return "home";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginGet() throws IOException {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginPost(HttpSession session,RedirectAttributes redirectAttribute,String username, String password) throws IOException {
		int userCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE username = ? AND password = ?", Integer.class, username, password);
		if (userCount == 1) {
			session.setAttribute("username", username);
			return "redirect:/design";
		} else {
			redirectAttribute.addFlashAttribute("error", "invalid");
			return "redirect:/login";
		}
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String registerPost(HttpSession session,RedirectAttributes redirectAttribute,String username, String password, String confirm, String birthday) throws IOException {
		Date setBirthday = Date.valueOf(birthday);
		int userCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE username = ?", Integer.class, username);
		if (password.equals(confirm)) {
			if (userCount == 0) {
				jdbcTemplate.update("INSERT INTO users (username, password, birthday) VALUES (?, ?, ?)", username,
						password, setBirthday);
				session.setAttribute("username", username);
				session.setAttribute("year", Integer.parseInt(birthday.substring(0, 4)));
				return "redirect:/design";
			} else {
				redirectAttribute.addFlashAttribute("error", "already");
				return "redirect:/login";
			}
		} else {
			redirectAttribute.addFlashAttribute("error", "confirm");
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path = "/design", method = RequestMethod.GET)
	public String designGet(HttpSession session,Model model) throws IOException {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return "redirect:/login";
		}
		List<Map<String,Object>> goals = jdbcTemplate.queryForList("SELECT * FROM design WHERE username = ?", username);
		model.addAttribute("goals", goals);
 		return "design";
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logoutGet(HttpSession session) throws IOException {
		session.removeAttribute("username");
		return "redirect:/login";
	}

}
