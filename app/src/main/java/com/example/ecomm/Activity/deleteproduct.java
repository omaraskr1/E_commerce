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

public class deleteproduct extends AppCompatActivity {
    EditText prodname;
    Button delete;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteproduct);

        Intialize();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteproduct();
            }
        });
    }
    public void Intialize(){
        prodname = findViewById(R.id.editTextTextPersonName5);


        delete= findViewById(R.id.button_delete);
        DB = new DBHelper(this);


    }
    public void deleteproduct(){
        String name = prodname.getText().toString();


        if(name.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Some fields not entered",Toast.LENGTH_LONG).show();
        }
        else
        {

            DB.deleteProduct(name);
            Toast.makeText(getApplicationContext(),"Successfully delete",Toast.LENGTH_LONG).show();
            Intent i = new Intent(deleteproduct.this,Admin_main.class);
            startActivity(i);
        }

    }
}