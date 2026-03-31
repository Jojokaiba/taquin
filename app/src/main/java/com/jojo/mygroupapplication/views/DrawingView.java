package com.jojo.mygroupapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.jojo.mygroupapplication.data.Matrice;
import com.jojo.mygroupapplication.resolver.TaquinResolver;

import java.util.jar.Attributes;

public class DrawingView extends View {
    float posX;
    float posY;
    private Paint drawingPaint;
    private Paint textPaint;


    Matrice depart = new Matrice(3);
    Matrice cible = new Matrice(3);
    Matrice current = new Matrice(3);;

    public void initMatrix() {
        depart.setValeur(new int[][]{
                {1, 2, 4},
                {3, 7, 6},
                {0, 8, 5}
        });

        cible.setValeur(new int[][]{
                {1, 2, 3},
                {8, 0, 4},
                {7, 6, 5}
        });

        current.setValeur(depart.copierMatrice());
    }


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
        drawingPaint.setStyle(Paint.Style.STROKE);
        drawingPaint.setStrokeWidth(10);   // Epaisseur de la ligne
        drawingPaint.setAntiAlias(true);


        textPaint = new Paint();                // paramétrage du texte
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(60);
        textPaint.setAntiAlias(true);
        initMatrix();
    }

    @Override
    protected  void onDraw(Canvas drawingCanvas){
        super.onDraw(drawingCanvas);
        drawMatrix(drawingCanvas);

    }


    public void setCurrent( Matrice newConfig ) {
        current.setValeur(newConfig.copierMatrice());
        invalidate();
    }
    public void drawMatrix(Canvas drawingCanvas) {

        float currentPositionX = getWidth() / 6;
        float currentPositionY = getHeight() / 2 ;
        int compteur = 1;
        Rect carre =  new Rect(200,200,200,200);
        for (int i=0 ; i< current.getSize() ; i++){
            for (int j=0 ; j < current.getSize() ; j++){



                drawingCanvas.drawRect(currentPositionX,currentPositionY,currentPositionX+200 , currentPositionY+200, drawingPaint);

                if(current.getValeur(i , j) != 0){
                    drawingCanvas.drawText(String.valueOf(current.getValeur(i , j)),currentPositionX+90 , currentPositionY+120, textPaint);
                }
                compteur += 1;

                currentPositionX += 200;

            }


            currentPositionX = getWidth() / 6;
            currentPositionY += 200 ;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                TaquinResolver.resolve(depart, cible, this);
                break;

            default:
        }
        return true;

    }
}
