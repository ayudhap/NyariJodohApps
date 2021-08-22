package com.example.nyarijodohapps.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.nyarijodohapps.entity.DataUser;
import com.example.nyarijodohapps.repository.DataUserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	DataUserRepository dataUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> jenisKelamin = null;
		DataUser dataUser = dataUserRepository.findByUsername(username);
		if (dataUser == null) {
			throw new UsernameNotFoundException("User tidak ditemukan dengan username : " + username);
		}
		jenisKelamin = Arrays.asList(new SimpleGrantedAuthority(dataUser.getJenisKelamin()));
		return new User(dataUser.getUsername(), dataUser.getPassword(), jenisKelamin);
	}

}
