package com.example.a15squares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainView drawing = (MainView) findViewById(R.id.drawing);
        controller control = new controller(drawing);



        drawing.setOnTouchListener(control);

    }

}