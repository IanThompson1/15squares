package com.example.a15squares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

/**
 * main activity
 * gets values for stuff and puts it into the other classes
 * implemented by Ian Thompson 09/26/2021
 * only enhancement is the always possible to solve, and draggable
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializes the main and activity classes
        MainView drawing = (MainView) findViewById(R.id.drawing);
        controller control = new controller(drawing);

        //finds the button view and implements them with the controller
        Button shuffle = (Button) findViewById(R.id.shuffle);
        shuffle.setOnClickListener(control);
        drawing.setOnTouchListener(control);

    }

}