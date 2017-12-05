package com.fpp.status.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpp.status.R;

import static android.content.ContentValues.TAG;

/**
 * Created by fp on 2017/12/6.
 */

public class CustomViewFour  extends LinearLayout{

        // 返回按钮控件
    private Button mLeftBtn;
    ImageView ivAdd, ivRemove;
    // 标题Tv
    TextView tvAdd,tvName, tvPrice;
    //是否是服务项目，是的话，数字不能超过1，不是的话，可以累加
    boolean isService;

    String price,name,number;
    int count = 0;

    public CustomViewFour(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.custom_view_one, this);
        // 获取控件
        ivAdd = (ImageView) findViewById(R.id.iv_add);
        ivRemove = (ImageView) findViewById(R.id.iv_add);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        Log.e(TAG, "CustomViewFour: " + "001"  );
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomViewOne, defStyle, 0);
        int n = a.getIndexCount();
        Log.e(TAG, "CustomViewFour: " + "002"  );
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            Log.e(TAG, "CustomViewFour: " + "003"  );
            switch (attr)
            {
                case R.styleable.CustomViewOne_isService:

                    isService = a.getBoolean(attr,true);
                    Log.e(TAG, "CustomViewFour: " + "005"  + isService );
                    break;

            }

        }


        a.recycle();
        Log.e(TAG, "CustomViewFour: " + "006" );
    }

    //设置价格
    public void setTvPriceText(String priceText){
        Log.e(TAG, "setTvPriceText: " + "007" );
        tvPrice.setText(priceText);
    }
    //设置名称
    public void setTvNameText(String nameText){
        Log.e(TAG, "setTvNameText: " + "008" );
        tvName.setText(nameText);
    }

    public void setIvAddListener(){
        Log.e(TAG, "setIvAddListener: " + "010" );
        ivAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " + "ooo");

                    Log.e(TAG, "onClick: " + "aaaaa" );

                count++;
                if (count == 1){
                    Log.e(TAG, "onClick: "+"显示数字1 2 3 等" );
                    ivAdd.setVisibility(GONE);
                    ivRemove.setVisibility(VISIBLE);
                    tvAdd.setVisibility(VISIBLE);
                }
                tvAdd.setText(count);

//                if (isService){
//                    Log.e(TAG, "onClick: " + "aaaaa" );
//                    ivAdd.setVisibility(GONE);
//                    ivRemove.setVisibility(VISIBLE);
//                    tvAdd.setVisibility(VISIBLE);
//                }else {
//                    Log.e(TAG, "onClick: " + "bbbbb" );
//                    ivAdd.setVisibility(VISIBLE);
//                    ivRemove.setVisibility(VISIBLE);
//                    ivAdd.setVisibility(GONE);
//                }
            }
        });
    }

    public void setIvRemoveListener(){

        ivRemove.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if (count  == 0){
                    Log.e(TAG, "onClick: "+"隐藏数字1 2 3 等" );
                    ivAdd.setVisibility(VISIBLE);
                    ivRemove.setVisibility(GONE);
                    ivAdd.setVisibility(GONE);
                }
                tvAdd.setText(count);

            }
        });


    }



//    // 为左侧返回按钮添加自定义点击事件
//    public void setLeftButtonListener(OnClickListener listener) {
//        mLeftBtn.setOnClickListener(listener);
//    }


}
