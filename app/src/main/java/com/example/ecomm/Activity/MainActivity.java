package com.example.ecomm.Activity;//package com.example.project.Activity;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//
//import com.example.project.R;
//
//public class MainActivity extends AppCompatActivity {
//    SharedPreferences SharedPreferences;
//    SharedPreferences.Editor Editor;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = new MenuInflater(this);
//        inflater.inflate(R.menu.menu1,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.searchmenu:
////                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
////                startActivity(intent);
//                return true;
//            case R.id.cartmenu:
////                Intent intent2 = new Intent(MainActivity.this,CartActivity.class);
////                startActivity(intent2);
//                return true;
//            case  R.id.logoutmenu:
//                SharedPreferences = getSharedPreferences("RemembeMe",MODE_PRIVATE);
//                Editor = SharedPreferences.edit();
//                Editor.putString("username",null);
//                Editor.putString("password",null);
//                Editor.putBoolean("login",false);
//                Editor.apply();
//                Intent intent3 = new Intent(MainActivity.this,Login.class);
//                startActivity(intent3);
//
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//}