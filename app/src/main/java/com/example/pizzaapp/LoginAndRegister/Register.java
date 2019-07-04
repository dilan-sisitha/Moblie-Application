package com.example.pizzaapp.LoginAndRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pizzaapp.IpAdress;
import com.example.pizzaapp.R;

public class Register extends AppCompatActivity {
    public EditText usernameTxt, passwordTxt, emailTxt, mobileTxt;
    public Button adduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);




        usernameTxt = findViewById(R.id.userName);
        emailTxt = findViewById(R.id.Email);
        passwordTxt = findViewById(R.id.Password);
        mobileTxt = findViewById(R.id.telephone);
        adduser = findViewById(R.id.addUser);

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                String mobieno = mobileTxt.getText().toString();


                String url = IpAdress.ip+"/demo/useradd?user_name=" + user + "&password=" + password + "&email=" + email + "&telephone=" + mobieno;

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