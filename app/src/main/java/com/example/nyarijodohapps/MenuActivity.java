package com.example.nyarijodohapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button btnDataCalon, btnPilihCalon, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnDataCalon = findViewById(R.id.btn_dataCalon);
        btnPilihCalon = findViewById(R.id.btn_piihCalon);
        btnKeluar = findViewById(R.id.btn_keluar);

        btnDataCalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, DataCalonActivity.class);
                startActivity(intent);
            }
        });

        btnPilihCalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PilihCalonActivity.class);
                startActivity(intent);
            }
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}