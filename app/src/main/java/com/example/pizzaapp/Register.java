package com.example.pizzaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Register extends AppCompatActivity {
    public EditText username, password, email, mobile;
    public Button adduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setTitle("REGISTER NOW");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        username = findViewById(R.id.userName);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        mobile = findViewById(R.id.telephone);
        adduser = findViewById(R.id.addUser);

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String mobieno = mobile.getText().toString();


                String url = "http://192.168.1.101:8080/demo/useradd?user_name=" + user + "&password=" + email1 + "&email=" + password1 + "&telephone=" + mobieno;

                RequestQueue requestQueue = Volley.newRequestQueue(Register.this);


                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET
                        , url
                        , new HTTPResponceListner(),
                        new HTTPErrorListner());

                requestQueue.add(stringRequest);
            }
        });


    }

    private class HTTPResponceListner implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            //responce.setText("User "+response);
            Toast.makeText(getApplicationContext(), "User "+response, Toast.LENGTH_SHORT).show();
            backToMenu();


        }


    }

    private class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            //responce.setText(error.getMessage());
            Toast.makeText(getApplicationContext(), "connection error", Toast.LENGTH_SHORT).show();
        }
    }

    public void backToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}