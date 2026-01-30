package com.jojo.mygroupapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.jar.Attributes;

public class DrawingView extends View {
    float posX;
    float posY;
    private Paint drawingPaint;
    private Paint textPaint;

    public DrawingView(Context context) {
        super(context);
        initComponents();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initComponents();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponents();
    }

    private void initComponents(){
        drawingPaint = new Paint();
        drawingPaint.setColor(Color.BLACK);
        drawingPaint.setStyle(Paint.Style.FILL);
        drawingPaint.setStrokeWidth(10);   // Epaisseur de la ligne
        drawingPaint.setAntiAlias(true);


        textPaint = new Paint();                // paramétrage du texte
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(60);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected  void onDraw(Canvas drawingCanvas){
        super.onDraw(drawingCanvas);
        // int radius = 200;
        // drawingCanvas.drawCircle(posX, posY, radius, drawingPaint);
        float currentPositionX = getWidth() / 6;
        float currentPositionY = getHeight() / 2 ;
        int compteur = 1;
        for (int i=1; i<5 ; i++){
            for (int j=1; j<5 ; j++){
                drawingCanvas.drawLine(currentPositionX, currentPositionY , currentPositionX + 200, currentPositionY, drawingPaint);  //premier côté du carré
                currentPositionX += 200;

                drawingCanvas.drawLine(currentPositionX, currentPositionY , currentPositionX, currentPositionY + 200, drawingPaint); // deuxième côté du carré
                currentPositionY += 200;

                drawingCanvas.drawLine(currentPositionX, currentPositionY , currentPositionX - 200, currentPositionY, drawingPaint);
                currentPositionX -= 200;

                drawingCanvas.drawLine(currentPositionX, currentPositionY , currentPositionX, currentPositionY - 200, drawingPaint);
                currentPositionY -= 200;

                if(compteur <= 8){
                    drawingCanvas.drawText(String.valueOf(compteur),currentPositionX+90 , currentPositionY+120, textPaint);
                }
                compteur += 1;

                currentPositionX += 200;

            }


            currentPositionX = getWidth() / 6;
            currentPositionY -= 200 ;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        posX = event.getX(); // position X dans la View
        posY = event.getY(); //position Y dans la View
        invalidate();
        return true;

    }
}
