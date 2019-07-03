package com.example.pizzaapp.Payment;

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
import com.example.pizzaapp.R;

public class DelivaryDetails extends AppCompatActivity {


    public EditText diliverName,address,telephone,comments;
    public Button confirmDilivery;
    Double totalAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivary_details);

        diliverName = findViewById(R.id.diliName);
        address = findViewById(R.id.diliAdress);
        telephone = findViewById(R.id.tele);
        comments = findViewById(R.id.comm);
        confirmDilivery = findViewById(R.id.place);




    }

    public void palceDilivery(View view) {
        checkInput();
        sendCardDetails();

    }

    private void sendCardDetails() {

        String diliname = diliverName.getText().toString();
        String addres = address.getText().toString();
        String tele = telephone.getText().toString() ;
        String comm = comments.getText().toString();






        String url = "http://192.168.43.216:8080/demo/cashPayadd?username=" + diliname + "&address=" + addres + "&telephone=" + tele + "&comment=" + comm;

        RequestQueue requestQueue = Volley.newRequestQueue(DelivaryDetails.this);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET
                , url
                , new DelivaryDetails.HTTPResponceListner(),
                new DelivaryDetails.HTTPErrorListner());

        requestQueue.add(stringRequest);

    }

    private class HTTPResponceListner implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {

            Toast.makeText(getApplicationContext(), "Cash payment "+response, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DelivaryDetails.this,PaymentSucess.class);
            startActivity(intent);


        }


    }
    public class HTTPErrorListner implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            //responce.setText(error.getMessage());
            Toast.makeText(getApplicationContext(), "connection error", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkInput() {

        if(diliverName.length()==0){
            diliverName.setError("can not be empty");
            confirmDilivery.setEnabled(false);
        }
        if(address.length()==0){
            address.setError("can not be empty");
            confirmDilivery.setEnabled(false);
        }
        if(telephone.length()==0){
            telephone.setError("can not be empty");
            confirmDilivery.setEnabled(false);
        }

    }

}
