package com.example.ecomm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.R;

public class feedback extends AppCompatActivity {

    EditText TextForFeedback;
    Button sumbitFeedback;
    DBHelper DB;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intialize();
        sumbitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if( Takefeedback()){
                   Toast.makeText(getApplicationContext(),"thank you",Toast.LENGTH_LONG).show();
                   Intent i = new Intent(feedback.this, Home.class);
                   startActivity(i);
                }
               else{
                   Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
               }

            }
        });
    }
    public void Intialize(){
        TextForFeedback = findViewById(R.id.textForfeedback);
        sumbitFeedback = findViewById(R.id.btnSubmit_feedback);
        DB = new DBHelper(this);
        sharedPreferences = getSharedPreferences("remember me",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public boolean Takefeedback(){
        String feedback=TextForFeedback.getText().toString();
        String username=sharedPreferences.getString("username","");
        int effected=DB.TakeFeedback(username,feedback);
        if(effected>0){
            return true;
        }
        return false;
    }

}