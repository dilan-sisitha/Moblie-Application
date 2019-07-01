package com.example.pizzaapp.MenuList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pizzaapp.CartItems.CartActivity;
import com.example.pizzaapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewMenu extends AppCompatActivity {
    RecyclerView recyclerView;
    PizzaAdapter adapter;

    List<Pizza> pizzaList;
    String url ="http://192.168.1.100:8080/demo/all";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_menu);

        pizzaList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadPizza();

    }

    private void loadPizza(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    // In response listener we will get the JSON response as a String
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray pizzaDetail = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i<pizzaDetail.length(); i++){

                                JSONObject pizza = pizzaDetail.getJSONObject(i);

                                pizzaList.add(new Pizza(
                                        pizza.getInt("pizzaId"),
                                        pizza.getString("name"),
                                        pizza.getString("description"),
                                        pizza.getDouble("price"),
                                        pizza.getString("imageUrl")
                                ));

                            }
                            adapter = new PizzaAdapter(RecycleViewMenu.this,pizzaList);
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecycleViewMenu.this,error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void OpenCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
