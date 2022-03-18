package com.example.w8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    Context context = null;
    BottleDispenser bd_machine = BottleDispenser.getInstance();
    TextView textView1;
    TextView textView2;
    SeekBar seekBar;
    Spinner spinner;
    ArrayList<String> bottles = bd_machine.getBottlesString();
    int Choice;
    double add;
    DecimalFormat decfor = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        spinner = findViewById(R.id.spinner);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int adding, boolean b) {
                textView2.setText("You are adding "+ decfor.format(adding) +"€ to balance.");
                add = adding;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Choice = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,bottles);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }
    public void addingMoney(View v){
        bd_machine.addMoney(add);
        textView1.setText("Added "+ String.valueOf(add)+"€");
        seekBar.setProgress(0);
    }
    public void buyDrink(View v){
        switch (bd_machine.buyBottle(Choice)){
            case 1:
                textView1.setText("KACHUNK! " + bd_machine.getBottle_array().get(Choice).getName()+ " came out of the dispenser! You have "+decfor.format(bd_machine.getMoney())+"€ left.");
                break;
            case 2:
                textView1.setText("Add money first!");
                break;
            case 3:
                textView1.setText("Sorry! Out of bottles!");
                break;
        }
    }
    public void returnMoney(View v){
        textView1.setText("Klink klink. Money came out! You got "+ decfor.format(bd_machine.getMoney()) +" back");
        bd_machine.returnMoney();
    }
    public void receipt(View v){
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("receipt.txt",context.MODE_PRIVATE));
            String receipt = "Receipt\n"+"Last purchase: "+bd_machine.getBottle_array().get(Choice).getName()+"\nPrice: "+bd_machine.getBottle_array().get(Choice).getPrice();
            ows.write(receipt);
            ows.close();
        }  catch (IOException e) {
            Log.e("IOException","Input error");
        } finally {
            textView1.setText("Receipt printed");
        }
    }
    }
