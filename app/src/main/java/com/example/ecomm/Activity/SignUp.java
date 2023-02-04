package com.example.ecomm.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.Models.Customers;
import com.example.ecomm.R;

import java.util.Calendar;

public class SignUp extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText NameSignup,UuserNameSignUp,PasswordSignUp,JopSignUp;
    TextView Date;
    Spinner Gender;
    Button SignUpBtn;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Intialize();
        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpF();
            }
        });
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePicker();
            }
        });
    }

    public void Intialize(){
        NameSignup = findViewById(R.id.name_signup);
        UuserNameSignUp = findViewById(R.id.username_signup);
        PasswordSignUp = findViewById(R.id.password_signup);
        Date = findViewById(R.id.date);
        JopSignUp = findViewById(R.id.jop_signup);
        Gender = findViewById(R.id.gender);
        SignUpBtn = findViewById(R.id.btn_signup);
        DB = new DBHelper(this);


    }

    public void SignUpF(){
        String name = NameSignup.getText().toString();
        String Uname = UuserNameSignUp.getText().toString();
        String password = PasswordSignUp.getText().toString();
        String birthdate = Date.getText().toString();
        String job = JopSignUp.getText().toString();
        String gend = Gender.getSelectedItem().toString();
        //String admin=adminornot.getText().toString();
        if(name.equals("")|| Uname.equals("") || password.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Some fields not entered",Toast.LENGTH_LONG).show();
        }
        else
        {
            Customers cust = new Customers(name,Uname,password,birthdate,job,gend);
            DB.InsertCustomersData(cust);
            Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
            Intent i = new Intent(SignUp.this, Login.class);
            startActivity(i);
        }

    }


    private void ShowDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month+=1;
        String birthdate = ""+year+" / "+month+" / "+dayOfMonth+"";
        Date.setText(birthdate);
    }
}