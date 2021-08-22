package com.example.nyarijodohapps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nyarijodohapps.config.JwtTokenUtil;
import com.example.nyarijodohapps.entity.DataUser;
import com.example.nyarijodohapps.service.DataUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	DataUserService dataUserService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/{jenisKelamin}")
	public List<DataUser> getAll(@RequestParam("jenisKelamin") String jenisKelamin) {
		return dataUserService.getUserByJenisKelamin(jenisKelamin);
	}

}
