package com.example.nyarijodohapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nyarijodohapps.entity.User;
import com.example.nyarijodohapps.service.ApiClient;
import com.example.nyarijodohapps.service.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setPassword(etUsername.getText().toString());
                user.setUsername(etPassword.getText().toString());
                UserInterface userInterface = ApiClient.getRetrofit().create(UserInterface.class);
                Call<Integer> call = userInterface.loginUser(user);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() == 1) {
                            Toast.makeText(LoginActivity.this,"Password atau username salah",Toast.LENGTH_LONG);
                            Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                            i.putExtra("username",etUsername.getText().toString());
                            startActivity(i);

                        } else {
                            Toast.makeText(LoginActivity.this,"Password atau username salah",Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Tidak Berhasil",Toast.LENGTH_LONG).show();
                        System.out.println(t);
                    }
                });
            }
        });
    }
}