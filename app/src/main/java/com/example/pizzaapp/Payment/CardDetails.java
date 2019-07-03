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
import com.example.pizzaapp.CartItems.Total;
import com.example.pizzaapp.R;
import com.example.pizzaapp.Register;

public class CardDetails extends AppCompatActivity {

    public EditText cardName,cardNum,month,date,code;
    public Button confirmPay;
    Double totalAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        cardName = findViewById(R.id.cardHolderName);
        cardNum = findViewById(R.id.crdNum);
        month = findViewById(R.id.expMon);
        date = findViewById(R.id.expDate);
        confirmPay = findViewById(R.id.confirm);
        code =findViewById(R.id.ver);

        totalAmount = Total.getTotalPrice();



    }

    public void Confirm(View view) {

        sendCardDetails();

        checkInput();
    }

    private void sendCardDetails() {

        String name = cardName.getText().toString();
        String num = cardNum.getText().toString();
        String day = (month.getText().toString()+ date.getText().toString()) ;
        String veri = code.getText().toString();






        String url = "http://192.168.43.216:8080/demo/payadd?cardName=" + name + "&cardNum=" + num + "&expDate=" + day + "&veriNum=" + veri;

        RequestQueue requestQueue = Volley.newRequestQueue(CardDetails.this);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET
                , url
                , new CardDetails.HTTPResponceListner(),
                new CardDetails.HTTPErrorListner());

        requestQueue.add(stringRequest);

    }

    private class HTTPResponceListner implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {

            Toast.makeText(getApplicationContext(), "payment "+response, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CardDetails.this,PaymentSucess.class);
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

        if(cardNum.length()==0){
            cardNum.setError("can not be empty");
           confirmPay.setEnabled(false);
        }
        if(cardName.length()==0){
            cardName.setError("can not be empty");
            confirmPay.setEnabled(false);
        }
        if(month.length()==0){
            month.setError("can not be empty");
            confirmPay.setEnabled(false);
        }
        if(date.length()==0){
            date.setError("can not be empty");
            confirmPay.setEnabled(false);
        }
        if(code.length()==0){
            code.setError("can not be empty");
            confirmPay.setEnabled(false);
        }
    }



}
