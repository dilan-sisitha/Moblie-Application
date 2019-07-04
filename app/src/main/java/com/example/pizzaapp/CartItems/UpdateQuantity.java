package com.example.pizzaapp.CartItems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.example.pizzaapp.IpAdress;
import com.example.pizzaapp.LoginAndRegister.Register;
import com.example.pizzaapp.MenuList.ExpandDetails;
import com.example.pizzaapp.R;
import com.squareup.picasso.Picasso;

import static com.example.pizzaapp.CartItems.CartAdapter.EXTRA_CART_ID;
import static com.example.pizzaapp.CartItems.CartAdapter.EXTRA_PIZZA_TYPE;
import static com.example.pizzaapp.CartItems.CartAdapter.EXTRA_UNIT_PRICE;


public class UpdateQuantity extends AppCompatActivity {


    TextView textViewName;
    TextView textViewPrice;
    ElegantNumberButton numberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_quantity);

        Intent intent1 = getIntent();


        final String orderId = intent1.getStringExtra(EXTRA_CART_ID);
        final String pizza = intent1.getStringExtra(EXTRA_PIZZA_TYPE);
        final String unitPrice = intent1.getStringExtra(EXTRA_UNIT_PRICE);

        Total.setOrderId(orderId);




        textViewName = findViewById(R.id.updtitemname);
        textViewPrice = findViewById(R.id.updttxtprice);
        numberButton = findViewById(R.id.updtelegantBtn);




        textViewName.setText(pizza);
        textViewPrice.setText(unitPrice);


        numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clickQuantity = numberButton.getNumber();
                String clickPrice = changePrice(unitPrice,clickQuantity);
                textViewPrice.setText(clickPrice);

            }
        });




    }

    public void openCart(View view) {
        updateCartList();
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    public void goback(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    private void updateCartList() {


        String quantity = numberButton.getNumber();
        String ordert = String.valueOf(Total.getOrderId());
        String price = (String) textViewPrice.getText();




        // Toast.makeText(getApplicationContext(),(pizzaName+" "+quantity+" "+updatedPrice),Toast.LENGTH_LONG).show();

        String url = IpAdress.ip+"/demo/updatecart?id=" + ordert  + "&quantity=" + quantity +"&price=" +price;

        RequestQueue requestQueue = Volley.newRequestQueue(UpdateQuantity.this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET
                , url
                , new UpdateQuantity.HTTPResponceListner(),
                new UpdateQuantity.HTTPErrorListner());

        requestQueue.add(stringRequest);


    }

    public class HTTPResponceListner implements Response.Listener<String> {


        @Override
        public void onResponse(String response) {
            Toast.makeText(getApplicationContext(), "Cart Updated Sucessfully", Toast.LENGTH_SHORT).show();
        }
    }

    public class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"connection error "+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private String changePrice(String price,String quantity) {

        Double doublePrice =Double.valueOf(price);
        int doubleQuantity = Integer.valueOf(quantity);
        Double updatedPrice = doublePrice * doubleQuantity;
        String newprice = String.valueOf(updatedPrice);
        return (newprice) ;
    }
}
