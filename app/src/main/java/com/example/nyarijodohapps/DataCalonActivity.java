package com.example.nyarijodohapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.nyarijodohapps.adapter.UserAdapter;
import com.example.nyarijodohapps.entity.User;
import com.example.nyarijodohapps.service.ApiClient;
import com.example.nyarijodohapps.service.UserInterface;
import com.example.nyarijodohapps.utility.JWTUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCalonActivity extends AppCompatActivity {
    RecyclerView rvDataCalon;
    String token;
    UserInterface userInterface;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_calon);
        rvDataCalon = findViewById(R.id.rv_data_calon);
        token = "Bearer "+getIntent().getStringExtra("token");
        userInterface = ApiClient.getRetrofit().create(UserInterface.class);

        listDataCalon();

        try {
            Gson gson = new Gson();
            user = gson.fromJson(JWTUtil.getBodyDecode(getIntent().getStringExtra("token")), User.class);
            System.out.println(user);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void listDataCalon() {
        Call<ArrayList<User>> call = userInterface.getAll(token);
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                UserAdapter userAdapter = new UserAdapter(response.body(),DataCalonActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataCalonActivity.this,LinearLayoutManager.VERTICAL,false);
                rvDataCalon.setLayoutManager(layoutManager);
                rvDataCalon.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(DataCalonActivity.this,"Tidak Dapat Mengakses",Toast.LENGTH_LONG).show();
                System.out.println(t);
                finish();
            }
        });
    }
}