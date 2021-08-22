package com.example.nyarijodohapps.service;

import com.example.nyarijodohapps.entity.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserInterface {
    @POST("/login")
    Call<Integer> loginUser(@Body User user);

    @POST("/register")
    Call<String> daftarUser(@Body User user);

    @GET
    Call<ArrayList<User>> getAll(@Header("Authorization") String header);
}
