package com.example.ecomm.Activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ecomm.R;

public class Admin_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        getWindow().setStatusBarColor(ContextCompat.getColor(Admin_main.this,R.color.black));

        Button ad =(Button) findViewById(R.id.btnadd);
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allbook =new Intent(Admin_main.this,addproduct.class);
                startActivity(allbook);
            }
        });
        Button report =(Button) findViewById(R.id.btnReport2);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allbook =new Intent(Admin_main.this,report.class);
                startActivity(allbook);
            }
        });
        Button showfeedbacks =(Button) findViewById(R.id.btnForFeedbacks);
        showfeedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allbook =new Intent(Admin_main.this,showfeedback.class);
                startActivity(allbook);
            }
        });
        Button chart =(Button) findViewById(R.id.btnchart);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allbook =new Intent(Admin_main.this,Chart.class);
                startActivity(allbook);
            }
        });
        Button delete =(Button) findViewById(R.id.button_showallboo2);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allbook =new Intent(Admin_main.this,deleteproduct.class);
                startActivity(allbook);
            }



        });
        Button edit=(Button) findViewById(R.id.button2searc);

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent allbooks =new Intent(Admin_main.this,edit.class);
                startActivity(allbooks);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();

        if(item_id==R.id.itemUserInterface){
            Intent users =new Intent(Admin_main.this,Home.class);
            startActivity(users);
            return true;

        }
        else if (item_id==R.id.itemLogOut){
            Intent users =new Intent(Admin_main.this,Login.class);
            startActivity(users);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }
