package com.example.ecomm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.Models.Product;
import com.example.ecomm.R;

public class addproduct extends AppCompatActivity {
    EditText prodname,prodprice,prodquantity,proddesc,prodcategory;
    Button add;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        Intialize();
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addproduct();
                }
            });

    }
    public void Intialize(){
        prodname = findViewById(R.id.editTextTextPersonName5);
        prodprice = findViewById(R.id.editTextTextPersonName4);
        prodquantity = findViewById(R.id.editTextTextPersonName);
        proddesc = findViewById(R.id.editTextTextPersonName3);
        prodcategory = findViewById(R.id.editTextTextPersonName2);

        add = findViewById(R.id.button_edit);
        DB = new DBHelper(this);


    }
    public void addproduct(){
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
            Product prd = new Product(name,price,quantity,desc,cate);
            DB.addproduct(prd);
            Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_LONG).show();
            Intent i = new Intent(addproduct.this,Admin_main.class);
            startActivity(i);
        }

    }

}