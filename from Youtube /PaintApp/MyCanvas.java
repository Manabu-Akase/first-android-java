package com.example.paintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyCanvas extends View {

    private Path path ;
    private Paint paint ;

    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //１、コンストラクタの作成

        path = new Path();//線を引いたり、図形を書いたりする

        paint = new Paint();// 筆の種類
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);//幅

    }

        //2,onDraw 描画の準備、メソッドの作成
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            canvas.drawPath(path,paint);
        }

        //3,タッチした時の処理絵をかく

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            //座標を取得する（x座標、y座標）
            float x = event.getX();
            float y = event.getY();

            //タッチの処理
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }

            //return super.onTouchEvent(event);
            return true;
        }


            //4,クリア処理

            public void clearCanvas(){
                path.reset();
                invalidate();
            }
        }
