package com.example.a15squares;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.Random;
import java.util.ArrayList;
/**
 * main view
 * does all of the drawing and does extra features like shuffling and checking if complete
 * implemented by Ian Thompson
 */
public class MainView extends SurfaceView{

    private boolean setup;
    private Paint color;
    private ArrayList<Tile> allTiles;
    private Model model;
    private int emptyX;
    private int emptyY;
    private int startBox;
    private int endBox;
    private final Random rand = new Random();
    private int random;
    private int blank;
    private boolean isComplete;
    private Paint background;
    private Paint winBackground;

    public MainView(Context context, AttributeSet atr) {
        super(context, atr);
        setWillNotDraw(false);

        //make list of tiles
        allTiles = new ArrayList<Tile>();
        setup = false;

        //colors
        color = new Paint();
        color.setARGB(255,255,100,150);
        background = new Paint();
        background.setColor(Color.BLUE);
        winBackground = new Paint();
        winBackground.setColor(Color.GREEN);

        //make model
        model = new Model();
    }

    protected void onDraw(Canvas canvas){

        //set up
        if(!setup){
            for(int i = 0; i<4; i++){
                for(int j = 0; j<4; j++) {

                    Tile myTile = new Tile((i*4)+(j+1),j, i);
                    System.out.println((i*4)+(j+1));
                    if(i*4+(j+1) == 16){
                        myTile.setNumber(0);
                    }
                    allTiles.add(myTile);
                }
            }
            shuffle(15, canvas);
            setup = true;
        }

        //find the empty space
        for(int i=0; i<16; i++){
            if(allTiles.get(i).getNumber() == 0){
                blank = i;
            }
        }

        //shuffles if needed
        if(model.needToShuffle){
            shuffle(blank, canvas);
            model.needToShuffle = false;
        }

        //find the box where you click down on
        if(model.xDown > 300 && model.xDown < 500){
            if(model.yDown > 300 && model.yDown < 500){
                startBox = 0;
            }else if(model.yDown > 600 && model.yDown < 800){
                startBox = 4;
            }else if(model.yDown > 900 && model.yDown < 1100){
                startBox = 8;
            }else if(model.yDown > 1200 && model.yDown < 1400){
                startBox = 12;
            }
        }else if(model.xDown > 600 && model.xDown < 800){
            if(model.yDown > 300 && model.yDown < 500){
                startBox = 1;
            }else if(model.yDown > 600 && model.yDown < 800){
                startBox = 5;
            }else if(model.yDown > 900 && model.yDown < 1100){
                startBox = 9;
            }else if(model.yDown > 1200 && model.yDown < 1400){
                startBox = 13;
            }
        }else if(model.xDown > 900 && model.xDown < 1100){
            if(model.yDown > 300 && model.yDown < 500){
                startBox = 2;
            }else if(model.yDown > 600 && model.yDown < 800){
                startBox = 6;
            }else if(model.yDown > 900 && model.yDown < 1100){
                startBox = 10;
            }else if(model.yDown > 1200 && model.yDown < 1400){
                startBox = 14;
            }
        }else if(model.xDown > 1200 && model.xDown < 1400){
            if(model.yDown > 300 && model.yDown < 500){
                startBox = 3;
            }else if(model.yDown > 600 && model.yDown < 800){
                startBox = 7;
            }else if(model.yDown > 900 && model.yDown < 1100){
                startBox = 11;
            }else if(model.yDown > 1200 && model.yDown < 1400){
                startBox = 15;
            }
        }
        //find the box where you lift up your mouse
        if(model.xUp > 300 && model.xUp < 500){
            if(model.yUp > 300 && model.yUp < 500){
                endBox = 0;
            }else if(model.yUp > 600 && model.yUp < 800){
                endBox = 4;
            }else if(model.yUp > 900 && model.yUp < 1100){
                endBox = 8;
            }else if(model.yUp > 1200 && model.yUp < 1400){
                endBox = 12;
            }
        }else if(model.xUp > 600 && model.xUp < 800){
            if(model.yUp > 300 && model.yUp < 500){
                endBox = 1;
            }else if(model.yUp > 600 && model.yUp < 800){
                endBox = 5;
            }else if(model.yUp > 900 && model.yUp < 1100){
                endBox = 9;
            }else if(model.yUp > 1200 && model.yUp < 1400){
                endBox = 13;
            }
        }else if(model.xUp > 900 && model.xUp < 1100){
            if(model.yUp > 300 && model.yUp < 500){
                endBox = 2;
            }else if(model.yUp > 600 && model.yUp < 800){
                endBox = 6;
            }else if(model.yUp > 900 && model.yUp < 1100){
                endBox = 10;
            }else if(model.yUp > 1200 && model.yUp < 1400){
                endBox = 14;
            }
        }else if(model.xUp > 1200 && model.xUp < 1400){
            if(model.yUp > 300 && model.yUp < 500){
                endBox = 3;
            }else if(model.yUp > 600 && model.yUp < 800){
                endBox = 7;
            }else if(model.yUp > 900 && model.yUp < 1100){
                endBox = 11;
            }else if(model.yUp > 1200 && model.yUp < 1400){
                endBox = 15;
            }
        }

        //check if the box you lift up on is empty
        Tile empty = allTiles.get(endBox);
        int none = empty.getNumber();
        if(none == 0) {
            //check if start is to the right        left                  below                    or above the empty space
            if(startBox == endBox+1 || startBox == endBox-1 || startBox == endBox+4 || startBox == endBox-4){
                swap(startBox, endBox, canvas);
            }
        }

        //checks if complete
        isComplete = true;
        for (int i = 1; i < 16; i++) {
            if (allTiles.get(i - 1).getNumber() != i) {
                isComplete = false;
                break;
            }
        }

        //draws background
        if(isComplete){
            canvas.drawRect(0,0,getWidth(),getHeight(),winBackground);
        }else{
            canvas.drawRect(0, 0, getWidth(), getHeight(), background);
        }
        isComplete = false;

        //draws tiles
        for(Tile current:allTiles){
            current.drawTile(canvas);
        }
    }

    // swaps a square with the empty space
    public void swap(int X, int no, Canvas c){
        Tile temporary = allTiles.get(X);
        allTiles.get(no).setNumber(temporary.getNumber());
        allTiles.get(X).setNumber(0);
        allTiles.get(no).drawTile(c);
        allTiles.get(X).drawTile(c);
    }

    //shuffles into a new random but possible state
    public void shuffle(int empty, Canvas c){
        int counter = 0;
        int next = 0;
        for(int i=0; i<10000; i++){
            random = rand.nextInt(4);
            if(random == 0 && empty < 15){
                next = empty+1;
            }else if(random == 1 && empty > 0){
                next = empty-1;
            }else if(random == 2 && empty < 12){
                next = empty+4;
            }else if(random == 3 && empty > 3){
                next = empty-4;
            }
            if(next == empty+1 || next == empty-1 || next == empty+4 || next == empty-4) {
                swap(next, empty, c);
                empty = next;
                counter++;
            }

        }
        System.out.println("swapped! "+counter);
    }

    //gets the model
    public Model getModel(){
        return model;
    }
}
