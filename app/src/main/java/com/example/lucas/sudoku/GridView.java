package com.example.lucas.sudoku;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.List;

public class GridView extends View implements View.OnTouchListener{

    StringBuilder sudoku = new StringBuilder("001700509573024106800501002700295018009400305652800007465080071000159004908007053");
    StringBuilder baseValues = new StringBuilder(sudoku);
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int cell = screenWidth / 9;
    int number = 0;


    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x  = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();



        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if (y > 1100 && y < 1250) {
                    number = x / cell + 1;
                    //Log.e("TAG", "x/y: " + x + " " + y + " " + number);
                }else if(y<1100){
                    number=0;
            }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int cell_x = x/cell;
                int cell_y = y/cell;
                if (y<1070) {
                    if (number != 0) {
                        char value = Integer.toString(number).charAt(0);
                        //Log.e("TAG", ""+cell_x+" "+cell_y);
                        if (baseValues.charAt(cell_x + 9 * cell_y) == '0') {
                            sudoku.setCharAt(cell_x + 9 * cell_y, value);
                            //Log.e("TAG", ""+(cell_x+9*cell_y)+" "+value);
                        }
                    } else if (number == 0 && baseValues.charAt(cell_x + 9 * cell_y) == '0') {
                        sudoku.setCharAt(cell_x + 9 * cell_y, '0');
                    }
                }


        }
        this.invalidate();
        return true;
    }


    public void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        double marge = 0.4;


        for (int i = 0; i < 9; i++) {

            canvas.drawLine(0, i * cell, screenWidth, i * cell, paint);
            canvas.drawLine(i * cell, 0, i * cell, screenWidth, paint);

        }

        //Cases de 9

        paint.setStrokeWidth(20);
        for (int i = 0; i < 3; i++) {

            canvas.drawLine(0, i * 3 * cell, screenWidth, i * 3 * cell, paint);
            canvas.drawLine(i * 3 * cell, 0, i * 3 * cell, screenWidth, paint);

        }
        canvas.drawLine(10, 10, 10, 9 * cell, paint);
        canvas.drawLine(10, 10, 9 * cell-10, 10, paint);
        canvas.drawLine(10, 9 * cell, 9 * cell-10, 9 * cell, paint);
        canvas.drawLine(9 * cell-10, 10, 9 * cell-10, 9 * cell, paint);


        for (int i =0; i<9;i++){
            for (int j=0;j<9;j++){
                float margei =i+Float.parseFloat(String.valueOf(marge));
                float margej =j+Float.parseFloat(String.valueOf(marge));
                if (sudoku.charAt(i+9*j) != '0'){
                    paint.setTextSize(50);
                    canvas.drawText(""+sudoku.charAt(i+9*j),(cell)*(margei),(cell)*(margej)+30,paint);
                }
            }
        }
        for (int i =0; i<9;i++){
            paint.setTextSize(100);
            canvas.drawText(""+(i+1),(cell)*i+20,(cell)*10,paint);
        }
    }

}

