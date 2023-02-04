package com.example.ecomm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.ecomm.DataBase.DBHelper;
import com.example.ecomm.Models.ChartModel;
import com.example.ecomm.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Chart extends AppCompatActivity {
    BarChart barChart;
    PieChart pieChart;
    DBHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);



        barChart=findViewById(R.id.bar_chart);
            //pieChart=findViewById(R.id.bar_chart);
            database=new DBHelper(getApplicationContext());

       ArrayList<BarEntry> barEntries=new ArrayList<>();
   //         ArrayList<PieEntry> pieEntries = new ArrayList<>();

            List<ChartModel> products=database.getAllSoldPro();

            System.out.println("Product size "+products.size());

            for(int i=0;i<products.size();i++){
               // System.out.println(products.get(i).getProName());
                float vale=(float)(i*10.0);
            BarEntry barEntry=new BarEntry(i, products.get(i).getProQuantity());
                System.out.println("Products Name "+products.get(i).getProName());
          //      PieEntry pieEntry = new PieEntry(products.get(i).getProQuantity(),products.get(i).getProName());
           barEntries.add(barEntry);
            //    pieEntries.add(pieEntry);
            }
        BarDataSet barDataSet=new BarDataSet(barEntries,"Best Selling Product");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(false);

        barChart.setData(new BarData(barDataSet));

        barChart.animateY(5000);

        barChart.getDescription().setText("Best Selling Product Chart");

        barChart.getDescription().setTextColor(Color.BLUE);

//            PieDataSet pieDataSet = new PieDataSet(pieEntries,"Products");
//
//            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//
//            pieChart.setData(new PieData(pieDataSet));
//
//            pieChart.animateXY(5000,5000);
//
//            pieChart.getDescription().setEnabled(false);
        }

}