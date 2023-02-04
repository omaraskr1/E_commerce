package com.example.ecomm.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecomm.Models.Product;
import com.example.ecomm.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewHolder> {

    Integer total;
    ArrayList<Product> cart;
    Context context;

    public CartListAdapter(ArrayList<Product> cart, Context context) {
        this.cart = cart;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cartproducts_cardview,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        holder.onBind(cart,position,context);
    }

    @Override
    public int getItemCount() {
        if(cart == null) return 0;
        return cart.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView proname, prodesc, proprice, proquantity,totalcost,qq;
        Button productplus, productminus;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            proname = itemView.findViewById(R.id.productname);
            prodesc = itemView.findViewById(R.id.productdescreption);
            proprice = itemView.findViewById(R.id.productprice);
            proquantity = itemView.findViewById(R.id.productquantity);
            totalcost = itemView.findViewById(R.id.costtxt);
//qq=itemView.findViewById(R.id.ddd);
            productplus= itemView.findViewById(R.id.productplus);
            productminus = itemView.findViewById(R.id.productminuse);
        }

        @SuppressLint("SetTextI18n")
        public void onBind(final ArrayList<Product> cartList, final int position, final Context context){
            proname.setText(cartList.get(position).getName());
            prodesc.setText(cartList.get(position).getDescription());
            proprice.setText(cartList.get(position).getPrice() + " $");
            proquantity.setText(""+cartList.get(position).getQuantity()+"");
           // qq.setText(""+cartList.get(position).getQuantity()+"");
            productplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer quantityplus = cartList.get(position).getQuantity();
                    quantityplus++;
                    cartList.get(position).setQuantity(quantityplus);
                    proquantity.setText(quantityplus + "");
                }
            });

            productminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer quantityminus = cartList.get(position).getQuantity();
                    quantityminus--;
                    if(quantityminus >= 1){
                        cartList.get(position).setQuantity(quantityminus);
                        proquantity.setText(quantityminus + "");
                    }
                    else
                        Toast.makeText(context,"The minimum quantity of product is 1",Toast.LENGTH_LONG).show();

                }
            });
        }

    }
}
