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
import com.example.pizzaapp.MenuList.RecycleViewMenu;

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




    }

    public void openMenu() {
        Intent intent = new Intent(this, RecycleViewMenu.class);
        startActivity(intent);

    }


    public void opensign(View view) {
        String email = userEmail.getText().toString();
        String pass = userPassword.getText().toString();


        String url = "http://192.168.43.216:8080/demo/checkUser?email=" + email + "&password=" + pass;

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET
                , url
                , new MainActivity.HTTPResponceListner(),
                new MainActivity.HTTPErrorListner());

        requestQueue.add(stringRequest);
    }

    public void openRegister(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    public void opennext(View view) {
        Intent intent = new Intent(this,RecycleViewMenu.class);
        startActivity(intent);
    }

    private class HTTPResponceListner implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            int result = Integer.parseInt(response);
            if(result == 1) {

                openMenu();
               sendLoginDetails();
            }
            else {
               // message.setText("User or password incorrect");
                Toast.makeText(getApplicationContext(),"User or password incorrect",Toast.LENGTH_SHORT).show();

            }

        }


    }



    private class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
           // message.setText(error.getMessage());
            Toast.makeText(getApplicationContext(),"connection error ",Toast.LENGTH_SHORT).show();

        }
    }

    private void sendLoginDetails() {
        String email = userEmail.getText().toString();

        String url = "http://192.168.43.216:8080/demo/addlogin?email=" + email;

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET
                , url
                , null,
                null);

        requestQueue.add(stringRequest);

    }



}