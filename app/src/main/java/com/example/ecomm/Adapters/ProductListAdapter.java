package com.example.ecomm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.Models.Product;
import com.example.ecomm.R;


import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    DBHelper database;
    ArrayList<Product> products;
    Context context;
    public ProductListAdapter(ArrayList<Product> list1, Context context){
        this.products = list1;
        this.context = context;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_cardview,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.onBind(products,position,context);

    }

    @Override
    public int getItemCount() {
        if(products == null) return 0;
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView proname,proprice,prodescription,q;
        Button addtocart;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            proname = itemView.findViewById(R.id.productname);
            proprice = itemView.findViewById(R.id.productprice);
            prodescription = itemView.findViewById(R.id.productdescreption);
            addtocart = itemView.findViewById(R.id.cartbtn);
            q=itemView.findViewById(R.id.ddd2);
        }

        public void onBind(final ArrayList<Product> prodName, final int position, final Context context){
            proname.setText(prodName.get(position).getName());
            proprice.setText(prodName.get(position).getPrice()+" $");
            prodescription.setText(prodName.get(position).getDescription());
            q.setText(""+prodName.get(position).getQuantity()+"");
            database = new DBHelper(context);
            addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Product cartproducts = new Product(prodName.get(position).getId(),prodName.get(position).getName(),
                                prodName.get(position).getPrice(),prodName.get(position).getDescription(),prodName.get(position).getQuantity());
                        database.cartProducts(cartproducts);
                        prodName.get(position).setAddedtocart(true);
                        Toast.makeText(context,prodName.get(position).getName()+" Added to cart",Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}
