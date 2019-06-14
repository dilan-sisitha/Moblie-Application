package com.example.pizzaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button registerBtn;
    private Button signinBtn;
    private EditText userName;
    private EditText userPassword;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.enteruser);
        userPassword = findViewById(R.id.enterpassword);
        signinBtn = findViewById(R.id.signin);
        registerBtn = findViewById(R.id.register);
        message = findViewById(R.id.info);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignin(userName.getText().toString(),userPassword.getText().toString());
            }
        });


    }
    public void openRegister(){
        Intent intent = new Intent(this, Sign_in.class);
        startActivity(intent);

    }
    public  void openSignin(String user,String password) {

        if ((user == "admin") && (password == "123")) {
            Intent intent1 = new Intent(this, Menu_list.class);
            startActivity(intent1);
        }

    }

}

