package com.fpp.status.activity.eleven.one;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Description:
 * Author: fpp
 * Date: 2018/9/3  15:03
 */

public class CTextView extends TextView {

    public CTextView(Context context) {
        super(context);
        init();
    }

    public CTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private Paint mPaint;
    private  void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        this.setLayerPaint(View.LAYER_TYPE_SOFTWARE,null);



    }

    private void setLayerPaint(int layerTypeSoftware, Object o) {




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShadowLayer(10F,15F,15F,Color.GREEN);
        RectF rectF = new RectF(200,0,200,200);
        canvas.drawRoundRect(rectF,75F,75F,mPaint);


    }
}
