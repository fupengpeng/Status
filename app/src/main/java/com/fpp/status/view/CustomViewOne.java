package com.fpp.status.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fpp.status.R;

/**
 * Created by fp on 2017/12/5.
 */

public class CustomViewOne extends RelativeLayout {

//    // 返回按钮控件
//    private Button mLeftBtn;
//    ImageView ivAdd, ivRemove;
//    // 标题Tv
//    TextView tvAdd,tvName, tvPrice;
//    //是否是服务项目，是的话，数字不能超过1，不是的话，可以累加
//    boolean isService;
//
//    String price,name,number;
//
//    public CustomViewOne(Context context, AttributeSet attrs, int defStyle){
//        super(context, attrs, defStyle);
//        // 加载布局
//        LayoutInflater.from(context).inflate(R.layout.custom_view_one, this);
//        // 获取控件
//        ivAdd = (ImageView) findViewById(R.id.iv_add);
//        ivRemove = (ImageView) findViewById(R.id.iv_add);
//        tvAdd = (TextView) findViewById(R.id.tv_add);
//        tvName = (TextView) findViewById(R.id.tv_name);
//        tvPrice = (TextView) findViewById(R.id.tv_price);
//        /**
//         * 获得我们所定义的自定义样式属性
//         */
//        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomViewOne, defStyle, 0);
//        isService = a.getBoolean(a.getIndex(0),true);
//        a.recycle();
//    }
//
//    //设置价格
//    public void setTvPriceText(String priceText){
//        tvPrice.setText(priceText);
//    }
//    //设置名称
//    public void setTvNameText(String nameText){
//        tvName.setText(nameText);
//    }
//
//    public void setIvAddListener(){
//        ivAdd.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isService){
//                    ivAdd.setVisibility(GONE);
//                    ivRemove.setVisibility(VISIBLE);
//                    tvAdd.setVisibility(VISIBLE);
//                }else {
//                    ivAdd.setVisibility(VISIBLE);
//                    ivAdd.setVisibility(GONE);
//                }
//            }
//        });
//    }
//
//    public void setIvRemoveListener(){
//
//        ivRemove.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isService){
//
//                }else {
//
//                }
//            }
//        });
//
//
//    }
//
//
//
//    // 为左侧返回按钮添加自定义点击事件
//    public void setLeftButtonListener(OnClickListener listener) {
//        mLeftBtn.setOnClickListener(listener);
//    }



// 返回按钮控件
     private Button mLeftBtn;
     // 标题Tv
          private TextView mTitleTv;

          public CustomViewOne(Context context, AttributeSet attrs) {
             super(context, attrs);

              // 加载布局
              LayoutInflater.from(context).inflate(R.layout.title_bar, this);

              // 获取控件
              mLeftBtn = (Button) findViewById(R.id.left_btn);
              mTitleTv = (TextView) findViewById(R.id.title_tv);

          }

          // 为左侧返回按钮添加自定义点击事件
          public void setLeftButtonListener(OnClickListener listener) {
              mLeftBtn.setOnClickListener(listener);
          }

          // 设置标题的方法
          public void setTitleText(String title) {
              mTitleTv.setText(title);
          }
 }
