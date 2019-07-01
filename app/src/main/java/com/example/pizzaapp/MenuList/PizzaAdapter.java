package com.example.pizzaapp.MenuList;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pizzaapp.R;

import java.util.List;
/*data to be shown in viewHolder is in adapter*/
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {
    private Context ctx;
    private List<Pizza> pizzaList;

    public static final String EXTRA_URL = "imageurl";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_DESC = "description";
    public static final String EXTRA_PRICE = "price";


    public PizzaAdapter(Context ctx, List<Pizza> pizzaList) {
        this.ctx = ctx;
        this.pizzaList = pizzaList;
    }


    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.list_layout, null);
        PizzaViewHolder holder = new PizzaViewHolder(view);
        return new PizzaViewHolder(view);
    }

    /*bind data to view holder */
    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder pizzaViewHolder, int position) {

        final Pizza pizza = pizzaList.get(position);
        pizzaViewHolder.textViewName.setText(pizza.getName());
        pizzaViewHolder.textViewPrice.setText(String.valueOf(pizza.getPrice()));


        Glide.with(ctx)
                .load(pizza.getImageURL())
                .into(pizzaViewHolder.imageView);

        pizzaViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ctx, ExpandDetails.class);
                intent.putExtra(EXTRA_URL, pizza.getImageURL());
                intent.putExtra(EXTRA_NAME, pizza.getName());
                intent.putExtra(EXTRA_DESC, pizza.getDetails());
                intent.putExtra(EXTRA_PRICE, pizza.getPrice().toString());

                ctx.startActivity(intent);
            }
        });


    }

    /*size of list*/
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    /*instance of view holder*/
    class PizzaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewPrice;
        LinearLayout linearLayout;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            linearLayout = itemView.findViewById(R.id.linear);


        }
    }
}