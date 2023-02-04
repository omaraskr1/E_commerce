package com.example.ecomm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.R;

public class showfeedback extends AppCompatActivity {

    ListView bookslist;
    ArrayAdapter<String> booksadapter;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showfeedback);
        Intialize();
        bookslist=(ListView) findViewById(R.id.listviewfeedback);
        booksadapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.layout1);
        bookslist.setAdapter(booksadapter);


        Cursor cur=DB.fetchallfeedbacks();
        while(!cur.isAfterLast()){
            booksadapter.add("username: "+cur.getString(0)+" feedback: "+cur.getString(1));



            cur.moveToNext();
        }

    }
    public void Intialize(){
        bookslist = findViewById(R.id.listviewfeedback);
        DB = new DBHelper(this);


    }

}