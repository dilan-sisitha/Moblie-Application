package com.example.pizzaapp.MenuList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.pizzaapp.CartItems.CartActivity;
import com.example.pizzaapp.IpAdress;
import com.example.pizzaapp.R;
import com.squareup.picasso.Picasso;

import static com.example.pizzaapp.MenuList.PizzaAdapter.EXTRA_DESC;
import static com.example.pizzaapp.MenuList.PizzaAdapter.EXTRA_NAME;
import static com.example.pizzaapp.MenuList.PizzaAdapter.EXTRA_PRICE;
import static com.example.pizzaapp.MenuList.PizzaAdapter.EXTRA_URL;

public class ExpandDetails extends AppCompatActivity {

    ImageView imageView;
    TextView textViewName;
    TextView textViewPrice;
    TextView textViewDescription;
    ElegantNumberButton numberButton;
    Button purchase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details);

        Intent intent = getIntent();

        final String imageurl = intent.getStringExtra(EXTRA_URL);
        final String name = intent.getStringExtra(EXTRA_NAME);
        final String price = intent.getStringExtra(EXTRA_PRICE);
        final String description = intent.getStringExtra(EXTRA_DESC);
        //System.out.println(name);
        /*
         */
        imageView = findViewById(R.id.detail_image);
        textViewName = findViewById(R.id.itemname);
        textViewPrice = findViewById(R.id.txtprice);
        textViewDescription = findViewById(R.id.details);
        numberButton = findViewById(R.id.elegantBtn);
        purchase = findViewById(R.id.purchaseBtn);

        textViewName.setText(name);
        textViewPrice.setText(price);
        textViewDescription.setText(description);

        Picasso.with(this).load(imageurl).fit().centerInside().into(imageView);

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCartList();

            }
        });

        numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clickQuantity = numberButton.getNumber();
                String clickPrice = changePrice(price,clickQuantity);
                textViewPrice.setText(clickPrice);
                //Toast.makeText(getApplicationContext(),clickQuantity,Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void addToCartList() {

        String pizzaName = textViewName.getText().toString();
        String price = textViewPrice.getText().toString();
        String quantity = numberButton.getNumber();


        String updatedPrice = changePrice(price,quantity);

       // Toast.makeText(getApplicationContext(),(pizzaName+" "+quantity+" "+updatedPrice),Toast.LENGTH_LONG).show();

        String url = IpAdress.ip+"/demo/addcart?pizza_type=" + pizzaName + "&quantity=" + quantity  + "&price=" + updatedPrice;

        RequestQueue requestQueue = Volley.newRequestQueue(ExpandDetails.this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET
                , url
                , new HTTPResponceListner(),
                new HTTPErrorListner());

        requestQueue.add(stringRequest);


    }

    private String changePrice(String price,String quantity) {

        Double doublePrice =Double.valueOf(price);
        int doubleQuantity = Integer.valueOf(quantity);
        Double updatedPrice = doublePrice * doubleQuantity;
        String newprice = String.valueOf(updatedPrice);
        return (newprice) ;
    }

    public void OpenCart(View view) {

        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);


    }

    public void goback(View view) {
        Intent intent = new Intent(this,RecycleViewMenu.class);
        startActivity(intent);
    }



    private class HTTPResponceListner implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            //responce.setText("User "+response);
            Toast.makeText(getApplicationContext(), "Cart "+response, Toast.LENGTH_SHORT).show();



        }

    }
    private class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            //responce.setText(error.getMessage());
            Toast.makeText(getApplicationContext(),"connection error "+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
