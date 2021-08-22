package com.example.nyarijodohapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nyarijodohapps.entity.User;

public class DetailCalonActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tvNama, tvUmur, tvUsername, tvNohp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_calon);
        imageView = findViewById(R.id.imageView2);
        tvNama = findViewById(R.id.tv_nama);
        tvUmur = findViewById(R.id.tv_umur);
        tvUsername = findViewById(R.id.tv_username);
        tvNohp = findViewById(R.id.tv_nohp);

        User user = getIntent().getParcelableExtra("user");
        Glide.with(DetailCalonActivity.this).load("http://55df6112262d.ngrok.io/image/"+user.getFoto()).into(imageView);
        tvNama.setText(user.getNama());
        tvNama.setText(user.getNama());
        tvNama.setText(user.getNama());
        tvNama.setText(user.getNama());
    }
}