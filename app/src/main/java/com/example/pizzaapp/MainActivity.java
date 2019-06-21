package com.example.pizzaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private Button registerBtn;
    private Button signinBtn;
    private EditText userEmail;
    private EditText userPassword;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userEmail = findViewById(R.id.enterEmail);
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


    }

    public void openRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);

    }
    public void openMenu() {
        Intent intent = new Intent(this, Menu_list.class);
        startActivity(intent);

    }


    public void opensign(View view) {
        String email = userEmail.getText().toString();
        String pass = userPassword.getText().toString();
        String url = "http://192.168.1.101:8080/demo/checkUser?email=" + email + "&password=" + pass;

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET
                , url
                , new MainActivity.HTTPResponceListner(),
                new MainActivity.HTTPErrorListner());

        requestQueue.add(stringRequest);
    }

    private class HTTPResponceListner implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            int result = Integer.parseInt(response);
            if(result == 1) {

                openMenu();

            }
            else {
                message.setText("User or password incorrect");

            }

        }


    }

    private class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            message.setText(error.getMessage());
        }
    }

}