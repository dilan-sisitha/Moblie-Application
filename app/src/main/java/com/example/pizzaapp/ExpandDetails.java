package com.example.pizzaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.pizzaapp.PizzaAdapter.EXTRA_DESC;
import static com.example.pizzaapp.PizzaAdapter.EXTRA_NAME;
import static com.example.pizzaapp.PizzaAdapter.EXTRA_PRICE;
import static com.example.pizzaapp.PizzaAdapter.EXTRA_URL;

public class ExpandDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details);

        Intent intent = getIntent();

        String imageurl = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        String price = intent.getStringExtra(EXTRA_PRICE);
        String description = intent.getStringExtra(EXTRA_DESC);
        //System.out.println(name);
        /*
         */
        ImageView imageView = findViewById(R.id.detail_image);
        TextView textViewName = findViewById(R.id.title);
        TextView textViewPrice = findViewById(R.id.price);
        TextView textViewDescription = findViewById(R.id.details);

        textViewName.setText(name);
        textViewPrice.setText(price);
        textViewDescription.setText(description);

        Picasso.get().load(imageurl).fit().centerInside().into(imageView);


    }
}
