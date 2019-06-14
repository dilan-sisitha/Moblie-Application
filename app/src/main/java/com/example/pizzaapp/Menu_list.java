package com.example.pizzaapp;

        import android.content.Context;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.Volley;
        import com.squareup.picasso.Picasso;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

public class Menu_list extends AppCompatActivity {
    private List<Pizza> pizzaDetails = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.101:8080/demo/all";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new HTTPResponseListner(), new HTTPErrorListner());
        queue.add(request);
    }
    class HTTPResponseListner implements Response.Listener<JSONArray>{
        @Override
        public void onResponse(JSONArray jsonArray) {
            for(int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject object= jsonArray.getJSONObject(i);
                    Pizza pizza = new Pizza();
                    pizza.setPizzaId(Integer.parseInt(object.get("pizzaId").toString()));
                    pizza.setName(object.get("name").toString());
                    pizza.setImageURL(object.get("imageUrl").toString());
                    pizza.setDetails(object.get("description").toString());
                    pizzaDetails.add(pizza);
                    Toast.makeText(getApplicationContext(),"display",Toast.LENGTH_SHORT).show();

                    ListView pizzaList = findViewById(R.id.listView);
                    CustomAdapter listAdapter = new CustomAdapter(getApplicationContext(), R.layout.list_item, pizzaDetails);
                    pizzaList.setAdapter(listAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
        }
    }

    private class CustomAdapter extends ArrayAdapter<Pizza> {
        private List<Pizza> itemsList;

        CustomAdapter(Context context, int resource, List<Pizza> items) {
            super(context, resource, items);
            itemsList = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getLayoutInflater().from(getContext()).inflate(R.layout.list_item, parent, false);
            }
            Pizza item = itemsList.get(position);
            TextView tv =  convertView.findViewById(R.id.textView);
            TextView bb = convertView.findViewById(R.id.des);
            ImageView iv = convertView.findViewById(R.id.imageView);
            Picasso.get().load(item.getImageURL()).into(iv);
            tv.setText(item.getName());
            bb.setText(item.getDetails());
            return convertView;
        }
    }
}
