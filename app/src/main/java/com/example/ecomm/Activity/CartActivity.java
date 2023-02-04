package com.example.ecomm.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecomm.Adapters.CartListAdapter;
import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.Models.Product;
import com.example.ecomm.R;


import java.util.ArrayList;
import java.util.Random;

public class CartActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    DBHelper database;
    ArrayList<Product> cartList = new ArrayList<>();
    CartListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recycler = (RecyclerView)findViewById(R.id.recyclercart);
        database = new DBHelper(getApplicationContext());

        Cursor cursor = database.getCartProducts();

        while (!cursor.isAfterLast()){
            Product cartProducts = new Product(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3), cursor.getInt(4));
            cartList.add(cartProducts);
            cursor.moveToNext();
        }
        adapter = new CartListAdapter(cartList,getApplicationContext());

        layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recycler);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);



        TextView showcost = (TextView)findViewById(R.id.totaltxt);

        showcost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView cost = (TextView)findViewById(R.id.costtxt);
                Integer totalcost=0, quantity, price;
                for (int i = 0; i<cartList.size(); i++)
                {
                    price = Integer.parseInt(cartList.get(i).getPrice());
                    quantity = cartList.get(i).getQuantity();
                    totalcost += price * quantity;
                }
                cost.setText(totalcost.toString());
            }
        });

        Button location = (Button)findViewById(R.id.Location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent i = new Intent(CartActivity.this,Currant_location.class);
               startActivity(i);
            }
        });

        Button submit = (Button)findViewById(R.id.submitbtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Your order Submited Successfully",Toast.LENGTH_LONG).show();
                Integer quantity;
                String m;

                Random r = new Random();

                for (int i = 0; i<cartList.size(); i++)
                {

                    quantity = cartList.get(i).getQuantity();
                    m=cartList.get(i).getName();
                    database.updateProductSoldCount(m,quantity);
                }

//                Integer orderid=r.nextInt(80 - 65) + 65);



                Intent intent = new Intent(CartActivity.this,feedback.class);
                startActivity(intent);
            }
        });


    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Product name = cartList.remove(viewHolder.getAdapterPosition());
            database.deleteCartProducts(name.getName());
            Toast.makeText(getApplicationContext(),name.getName()+" removed",Toast.LENGTH_LONG).show();

            adapter.notifyDataSetChanged();
        }
    };
}