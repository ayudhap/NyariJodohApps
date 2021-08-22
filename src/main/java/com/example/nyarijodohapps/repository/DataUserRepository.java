package com.example.nyarijodohapps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.nyarijodohapps.entity.DataUser;

public interface DataUserRepository extends JpaRepository<DataUser, Long>{
	@Query(value = "SELECT * FROM data_user WHERE jenis_kelamin NOT LIKE ?1", nativeQuery = true)
	List<DataUser> findByJenisKelamin(String jenisKelamin);
	DataUser findByUsername(String username);
}
