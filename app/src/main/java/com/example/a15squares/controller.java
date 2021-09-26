package com.example.a15squares;

import android.view.MotionEvent;
import android.view.View;
/**
 * controller class
 * implements all of the user controls and tells the main view what it needs to do next
 * implemented by Ian Thompson
 */
public class controller implements View.OnTouchListener, View.OnClickListener{
    private MainView mainView;
    private Model model;

    public controller(MainView view){
        mainView = view;
        model = mainView.getModel();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

            //gets the x and y of the mouse's click down
            model.xDown = event.getX();
            model.yDown = event.getY();
            return true;
        }else if(event.getAction() == MotionEvent.ACTION_UP){

            //gets the x and y of the mouse's click down
            model.xUp = event.getX();
            model.yUp = event.getY();
            mainView.invalidate();
            return true;

        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.shuffle){

            //checks if the button is pressed then sends a signal to shuffle the board
            model.needToShuffle = true;
            System.out.println("needs to shuffle");
            mainView.invalidate();
        }
    }
}
