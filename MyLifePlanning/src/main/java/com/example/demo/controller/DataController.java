package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class DataController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/save/{text}/{year}", method = RequestMethod.GET)
	public String saveGet(HttpSession session, @PathVariable String text, @PathVariable int year) {
		String username = (String) session.getAttribute("username");
		jdbcTemplate.update("INSERT INTO design VALUES (?, ?, ?, ?)", null, year, text, username);
		return text;
	}

}
