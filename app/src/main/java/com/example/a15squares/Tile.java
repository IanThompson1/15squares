package com.example.a15squares;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
/**
 * tile class
 * holds the independent aspects of each tile on the board
 * implemented by Ian Thompson
 */
public class Tile {
    private int number;
    private boolean[][] position = new boolean[4][4];
    private int x;
    private int y;
    private Paint color;
    private Paint emptyColor;
    private Paint textColor;

    //creates a tile
    public Tile(int num, int xPos, int yPos) {
        textColor = new Paint();
        textColor.setColor(Color.RED);
        textColor.setTextSize(50);
        color = new Paint();
        color.setARGB(255,100,200,150);
        emptyColor = new Paint();
        emptyColor.setARGB(255,255,50,50);
        number = num;
        position[xPos][yPos] = true;
        x = xPos;
        y = yPos;
    }

    //draws the tile with a given canvas
    public void drawTile(Canvas c){
        if(number != 0) {
            c.drawRect((x + 1) * 300, (y + 1) * 300, (x + 1) * 300 + 200, (y + 1) * 300 + 200, color);
        }else{
            c.drawRect((x + 1) * 300, (y + 1) * 300, (x + 1) * 300 + 200, (y + 1) * 300 + 200, emptyColor);
        }
        if(number != 0) {
            c.drawText("" + number, (x + 1) * 300 + 90, (y + 1) * 300 + 100, textColor);
        }
    }

    public void setNumber(int num){
        number = num;
    }

    public int getNumber(){
        return number;
    }
}
