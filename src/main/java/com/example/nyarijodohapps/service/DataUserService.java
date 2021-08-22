package com.example.nyarijodohapps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nyarijodohapps.entity.DataUser;
import com.example.nyarijodohapps.repository.DataUserRepository;;

@Service
public class DataUserService {
	
	@Autowired
	DataUserRepository dataUserRepository;
	
	public List<DataUser> getUserByJenisKelamin(String jenisKelamin) {
		return dataUserRepository.findByJenisKelamin(jenisKelamin);
	}
	
	public DataUser getUserByUsername(String username) {
		return dataUserRepository.findByUsername(username);
	}
	
	public String saveUser(DataUser user) {
		dataUserRepository.save(user);
		return "Data Berhasil Terbuat";
	}
	
	public List<DataUser> getAllUser() {
		return dataUserRepository.findAll();
	}

}
