package com.example.ecomm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.R;

public class report extends AppCompatActivity {


    EditText searchfordate;
    Button view_report;
    ListView report_list;
    ArrayAdapter<String> reportadapter;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intialize();
        reportadapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.layout1);
        report_list.setAdapter(reportadapter);
        String date=searchfordate.getText().toString();
        view_report.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor cur=DB.fetchallreports(date);
            while(!cur.isAfterLast()){
                reportadapter.add("order id: "+cur.getString(0));
                reportadapter.add("product id: "+cur.getString(1));
                reportadapter.add("quantity: "+cur.getString(2));
                reportadapter.add("product name: "+cur.getString(3));
                reportadapter.add("date: "+cur.getString(4));


                cur.moveToNext();
            }
        }
        });



    }


    public void Intialize(){
        report_list = findViewById(R.id.report_listview);
        DB = new DBHelper(this);
        searchfordate=findViewById(R.id.edittxtfordate);
        view_report=findViewById(R.id.btnforreport);



    }
}