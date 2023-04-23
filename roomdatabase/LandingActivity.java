package com.example.myapp.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapp.R;

public class LandingActivity extends AppCompatActivity {

    TextView tName;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        tName = findViewById(R.id.name);
        String name = getIntent().getStringExtra("name");
        tName.setText(name);

    }
}