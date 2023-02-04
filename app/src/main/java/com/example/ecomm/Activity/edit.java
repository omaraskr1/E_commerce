package com.example.ecomm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.Models.Product;
import com.example.ecomm.R;

public class edit extends AppCompatActivity {
    EditText prodname,prodprice,prodquantity,proddesc,prodcategory;
    Button edit,display;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intialize();
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayprod();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editprod();
            }
        });
    }
    public void Intialize(){
        prodname = findViewById(R.id.editTextTextPersonName5);
        prodprice = findViewById(R.id.editTextTextPersonName4);
        prodquantity = findViewById(R.id.editTextTextPersonName);
        proddesc = findViewById(R.id.editTextTextPersonName3);
        prodcategory = findViewById(R.id.editTextTextPersonName2);

        edit = findViewById(R.id.button_edit);
        display = findViewById(R.id.button);
        DB = new DBHelper(this);


    }
    public void displayprod(){
        String name = prodname.getText().toString();
        Cursor prod=DB.displayproduct(name);

        prodprice.setText(prod.getString(2)+" $");
        proddesc.setText(prod.getString(4));
        prodquantity.setText(prod.getString(3));
        prodcategory.setText(prod.getString(6));

    }
    public void editprod(){
        String name = prodname.getText().toString();
        String price =prodprice.getText().toString();
        Integer quantity = Integer.parseInt(prodquantity.getText().toString());

        String desc = proddesc.getText().toString();
        Integer cate  = Integer.parseInt(prodcategory.getText().toString());

        if(name.equals("")|| price.equals("")|| quantity.equals("")||desc.equals("") || cate.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Some fields not entered",Toast.LENGTH_LONG).show();
        }
        else
        {
            DB.editproduct(name,price,quantity,desc,cate);

            Toast.makeText(getApplicationContext(),"Successfully edit",Toast.LENGTH_LONG).show();
            Intent i = new Intent(edit.this,Admin_main.class);
            startActivity(i);
        }

    }
}