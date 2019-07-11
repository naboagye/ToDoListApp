package com.example.todolistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        Button loginBtn = (Button)findViewById(R.id.login);

        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("Alex") && password.getText().toString().equals("password")){
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Password or Username is incorrect", Toast.LENGTH_SHORT).show();

                }            }
        });
    }





}