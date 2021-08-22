package com.example.nyarijodohapps.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.nyarijodohapps.config.JwtTokenUtil;
import com.example.nyarijodohapps.entity.DataUser;
import com.example.nyarijodohapps.service.DataUserService;
import com.example.nyarijodohapps.service.MyUserDetails;
import com.example.nyarijodohapps.utility.FileUtility;
import com.google.gson.Gson;

@RestController
@RequestMapping("/authenticate")
public class JwtAuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetails myUserDetails;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	DataUserService dataUserService;
	
	@GetMapping("/getalluser")
	public ResponseEntity<?> getAllUser() {
		return ResponseEntity.ok(dataUserService.getAllUser());
	}

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody DataUser dataUser) throws Exception {
		authenticate(dataUser.getUsername(), dataUser.getPassword());
		final UserDetails userDetails = myUserDetails.loadUserByUsername(dataUser.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails,
				dataUserService.getUserByUsername(dataUser.getUsername()).getJenisKelamin());
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody DataUser dataUser, @RequestParam(value = "file") MultipartFile images, @ModelAttribute(value="data") String dataJSON) throws IOException{
		BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
		dataUser.setPassword(passwordEncode.encode(dataUser.getPassword()));
		
		String filename = StringUtils.cleanPath(images.getOriginalFilename());
		String uploadDir="src/main/java/user-photos";
		FileUtility.saveFile(uploadDir, filename, images);
		dataUser = new Gson().fromJson(dataJSON, DataUser.class);
		
		return ResponseEntity.ok(dataUserService.saveUser(dataUser));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("USER DISABLED", e);
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
