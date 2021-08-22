package com.example.nyarijodohapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nyarijodohapps.entity.User;
import com.example.nyarijodohapps.service.ApiClient;
import com.example.nyarijodohapps.service.UserInterface;
import com.google.gson.Gson;

import java.io.File;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername,etPassword, etNama, etNoHp, etUmur;
    Spinner spnJenisKelamin;
    ImageView imageView;
    Button btnCamera, btnRegister;
    private int requestCode = 1;
    RequestBody requestBody, data;
    String mediaPath;
    MultipartBody.Part fileToUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.et_username3);
        etPassword = findViewById(R.id.et_password3);
        etNama = findViewById(R.id.et_nama3);
        etNoHp = findViewById(R.id.et_nohp3);
        etUmur = findViewById(R.id.et_umur3);
        spnJenisKelamin = findViewById(R.id.spn_jenisKelamin);
        imageView= findViewById(R.id.imageView);
        btnCamera = findViewById(R.id.btn_camera);
        btnRegister = findViewById(R.id.btn_register3);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 1);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUsername(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.setNama(etNama.getText().toString());
                user.setNoHp(etNoHp.getText().toString());
                user.setUmur(etUmur.getText().toString());
                user.setJenisKelamin(spnJenisKelamin.getSelectedItem().toString());
                Gson gson = new Gson();
                String json = gson.toJson(user);

                UserInterface userInterface = ApiClient.getRetrofit().create(UserInterface.class);

                File file = new File(mediaPath);
                requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                data = RequestBody.create(MediaType.parse("text/plain"), json);

                Call<String> call = userInterface.daftarUser(user);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(RegisterActivity.this,response.body(),Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this,"Tidak Berhasil",Toast.LENGTH_LONG).show();
                        System.out.println(t);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode == requestCode && resultCode == RESULT_OK) {
            mediaPath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
        }
    }
}