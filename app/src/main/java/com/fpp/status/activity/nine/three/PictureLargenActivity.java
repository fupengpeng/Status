package com.fpp.status.activity.nine.three;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.fpp.status.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Description:  图片变大，点击变回
 * Author: fpp
 * Date: 2018/6/23  9:05
 */

public class PictureLargenActivity  extends AppCompatActivity {




    Bitmap bp=null;
    ImageView imageview;
    float scaleWidth;
    float scaleHeight;

    int h;
    boolean num=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_largen);

        DisplayMetrics dm=new DisplayMetrics();//创建矩阵
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        imageview=(ImageView)findViewById(R.id.imageview);
        bp= BitmapFactory.decodeResource(getResources(),R.drawable.shilipic);
        int width=bp.getWidth();
        int height=bp.getHeight();
        int w=dm.widthPixels; //得到屏幕的宽度
        int h=dm.heightPixels; //得到屏幕的高度
        scaleWidth=((float)w)/width;
        scaleHeight=((float)h)/height;
        imageview.setImageBitmap(bp);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:  //当屏幕检测到第一个触点按下之后就会触发到这个事件。
                if(num==true)        {
                    Matrix matrix= new Matrix();
                    matrix.postScale(scaleWidth,scaleHeight);

                    Bitmap newBitmap=Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(),
                            matrix, true);
                    imageview.setImageBitmap(newBitmap);
                    num=false;
                }
                else{
                    Matrix matrix = new Matrix();
                    matrix.postScale(1.0f,1.0f);
                    Bitmap newBitmap=Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(),
                            matrix, true);
                    imageview.setImageBitmap(newBitmap);
                    num=true;
                }
                break;
        }

        return super.onTouchEvent(event);
    }



}
