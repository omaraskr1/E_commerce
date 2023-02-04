package com.example.ecomm.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecomm.Models.Product;
import com.example.ecomm.Adapters.ProductListAdapter;
import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    DBHelper database;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProductListAdapter adapter;
    ArrayList<Product> searchList;
    Cursor cursor;
    Product searchProducts;
    Button voice, search, scan;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        edit = findViewById(R.id.edit);
        search = findViewById(R.id.search_button);
        voice = findViewById(R.id.voice_button);
        scan = findViewById(R.id.scan_button);
        recyclerView = findViewById(R.id.recyclerviewsearch);
        database = new DBHelper(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchList = new ArrayList<>();
                cursor = database.searchProducts(edit.getText().toString());
                if (cursor != null) {
                    while (!cursor.isAfterLast()) {
                        searchProducts = new Product(cursor.getInt(0), cursor.getString(1),
                                cursor.getString(2), cursor.getString(4), cursor.getInt(3));
                        searchList.add(searchProducts);
                        cursor.moveToNext();
                    }
                    adapter = new ProductListAdapter(searchList, getApplicationContext());
                    layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                } else
                    Toast.makeText(getApplicationContext(), "No matched Products", Toast.LENGTH_LONG).show();

            }
        });

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voicesearch();
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(Search.this);
                intentIntegrator.setPrompt("for flash use volume up key");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(capture.class);
                intentIntegrator.initiateScan();
            }
        });

    }

    private void voicesearch() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //search by voice
        if(requestCode==1 && resultCode==RESULT_OK){
            ArrayList<String> arrayList= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String voice=arrayList.get(0);
            edit.setText(arrayList.get(0));
            Toast.makeText(this,"voice is recorded",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show();
        }

        searchList = new ArrayList<>();
        cursor = database.searchProducts(edit.getText().toString());
        if(cursor != null)
        {
            while (!cursor.isAfterLast())
            {
                searchProducts = new Product(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(4),cursor.getInt(3));
                searchList.add(searchProducts);
                cursor.moveToNext();
            }
            adapter = new ProductListAdapter(searchList,getApplicationContext());
            layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }

        //search by qrcode

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(intentResult!=null){

            edit.setText(intentResult.getContents());
            searchList = new ArrayList<>();
            cursor = database.scanproduct(edit.getText().toString());
            if(cursor != null)
            {
                while (!cursor.isAfterLast())
                {
                    searchProducts = new Product(cursor.getInt(0),cursor.getString(1),
                            cursor.getString(2),cursor.getString(4),cursor.getInt(3));
                    searchList.add(searchProducts);
                    cursor.moveToNext();
                }
                adapter = new ProductListAdapter(searchList,getApplicationContext());
                layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }
            else {
                Toast.makeText(getApplicationContext(),"No Matched Products",Toast.LENGTH_LONG).show();
            }
        }
    }
}





