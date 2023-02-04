package com.example.ecomm.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.R;


public class Login extends AppCompatActivity {
    EditText UserName,Passwprd;
    Button LoginBtn;
    TextView ForgetPass,SignUp;
    boolean Login ;

    CheckBox RememberMe;
    DBHelper DB;
    SharedPreferences SharedPref;
    SharedPreferences.Editor Editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intialize();
        CheckLogin();

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerLogin();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });

        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RecoverPass.class);
                startActivity(intent);
            }
        });
    }

    protected void Intialize(){
        UserName = findViewById(R.id.username_login);
        Passwprd = findViewById(R.id.password_login);
        LoginBtn = findViewById(R.id.btn_login);
        ForgetPass = findViewById(R.id.forget_password);
        SignUp = findViewById(R.id.go_sign_up_btn);
        RememberMe = findViewById(R.id.remember);
        DB = new DBHelper(this);

        SharedPref = getSharedPreferences("remember me",MODE_PRIVATE);
    }

    protected void CustomerLogin(){
        String Uname = UserName.getText().toString();
        String Password = Passwprd.getText().toString();
        if(Uname.equals("admin")&&Password.equals("admin")){
            Intent i = new Intent(Login.this, Admin_main.class);
            startActivity(i);
        }
        else {
        Cursor cursor = DB.CustomerLogin(Uname,Password);
        if (cursor.getCount()<=0)
        {
            Toast.makeText(getApplicationContext(),"Please check username and password",Toast.LENGTH_LONG).show();
        }
        else
        {
            if(RememberMe.isChecked())
            {
                KeepLogin(Uname,Password);
            }
            Toast.makeText(getApplicationContext(),"Successfully login",Toast.LENGTH_LONG).show();
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
        }}

    }

    protected void KeepLogin(String userName, String Upass){
        Editor = SharedPref.edit();
        Editor.putString("username",userName);
        Editor.putString("password",Upass);
        Editor.putBoolean("login",true);
        Editor.apply();
    }
    protected void CheckLogin(){
        Login = SharedPref.getBoolean("login",false);
        if(Login == true)
        {
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
        }
    }

}