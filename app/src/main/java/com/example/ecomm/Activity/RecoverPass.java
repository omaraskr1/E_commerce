package com.example.ecomm.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.R;


public class RecoverPass extends AppCompatActivity {

    EditText UName;
    String UserName;
    TextView Pass;
    Button Recover;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_pass);

        Intialize();

        Recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecoverPassword();
            }
        });

    }

    protected void Intialize(){
        UName = findViewById(R.id.mail_recovery_pass);
        Pass =findViewById(R.id.pass_recovered);
        Recover = (Button)findViewById(R.id.load_pass);
        DB = new DBHelper(this);
    }
    protected void RecoverPassword(){
        UserName = UName.getText().toString();
        String password = DB.RecoverPassword(UserName);
        if(password == null)
        {
            Toast.makeText(getApplicationContext(),"Please check your username",Toast.LENGTH_LONG).show();
        }
        else
            Pass.setText("Your Password is: "+password);

    }
}