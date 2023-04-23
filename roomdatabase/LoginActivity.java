package com.example.myapp.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText userId, password;
    Button login;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userIdText = userId.getText().toString();
               final String passwordText = password.getText().toString();
                if(userIdText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must fill all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    UserDatabase userDatabase = UserDatabase
                            .getUserDatabase(getApplicationContext());
                   final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userIdText, passwordText);
                            if(userEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext()
                                        , "Invalid Credential", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                String name = userEntity.name;
                                startActivity(new Intent(
                                        LoginActivity.this, LandingActivity.class)
                                        .putExtra("name", name));
                            }

                        }
                    }).start();
                }

            }
        });

    }
}