package com.example.a15squares;

import android.view.MotionEvent;
import android.view.View;

public class controller implements View.OnTouchListener{
    private MainView mainView;
    private Model model;

    public controller(MainView view){
        mainView = view;
        model = mainView.getModel();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            model.xDown = event.getX();
            model.yDown = event.getY();

//            System.out.println(""+model.xDown);
            return true;
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            model.xUp = event.getX();
            model.yUp = event.getY();

//            System.out.println(""+model.xUp);

            mainView.invalidate();
            return true;

        }
        return false;
    }
}
