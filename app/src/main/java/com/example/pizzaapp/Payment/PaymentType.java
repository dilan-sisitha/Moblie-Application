package com.example.pizzaapp.Payment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pizzaapp.R;

public class PaymentType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_type);
    }

    public void paycard(View view) {
        Intent intent = new Intent(this,CardDetails.class);
        startActivity(intent);

    }

    public void paycash(View view) {

        Intent intent = new Intent(this,DelivaryDetails.class);
        startActivity(intent);

        Toast.makeText(this,"cssh",Toast.LENGTH_SHORT).show();
    }
}
