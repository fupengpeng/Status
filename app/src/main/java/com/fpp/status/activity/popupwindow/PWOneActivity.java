package com.fpp.status.activity.popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fpp.status.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PWOneActivity extends AppCompatActivity {


    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwone);
        ButterKnife.bind(this);

        initView();


    }

    private void initView() {

        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_style01, null));
//        popupWindow.showAtLocation(R.id.ll_content, ,Gravity.BOTTOM);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
//        popupWindow.setOutsideTouchable(true);   // 设置可以触摸弹出框以外的区域
//        popupWindow.setBackgroundDrawable();
        popupWindow.setFocusable(true);  //设置可以获取焦点


    }

    @OnClick({R.id.left, R.id.tv_title, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                break;
            case R.id.tv_title:
                break;
            case R.id.iv_right:
                break;
        }
    }
}
