package com.fpp.status.activity.four;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fpp.status.R;
import com.fpp.status.activity.fragmenteight.fragment.AllFragment;
import com.fpp.status.activity.fragmenteight.fragment.AlreadyAccomplishFragment;
import com.fpp.status.activity.fragmenteight.fragment.AlreadyCancelFragment;
import com.fpp.status.activity.fragmenteight.fragment.UnderwayFragment;
import com.fpp.status.activity.fragmenteight.fragment.WaitAcceptFragment;
import com.fpp.status.entity.LoadMemberListResponseData;
import com.fpp.status.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fupengpeng on 2017/12/16 0016.
 */

public class FourFourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 生成一个LinearLayout，作为布局容器来动态添加3个Button
        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        // 生成3个Button
        final Button btn1 = new Button(this);
        btn1.setText("1");
        btn1.setText("Button1");
        final Button btn2 = new Button(this);
        btn2.setText("2");
        btn2.setText("Button2");
        final Button btn3 = new Button(this);
        btn3.setText("3");
        btn3.setText("Button3");

        // 动态把三个Button添加到
        layout.addView(btn1);
        layout.addView(btn2);
        layout.addView(btn3);

        // 点击按钮时，先把原来在布局容器layout上的删掉，再添加上局容器layout，这样本次添加的控件就会排序到最后，以理解动态添加控件的思路
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                layout.removeView(btn1);
                layout.addView(btn1);
            }
        });

        // 同btn1一样道理
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                layout.removeView(btn2);
                layout.addView(btn2);
            }
        });

        // 同btn1一样道理
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                layout.removeView(btn3);
                layout.addView(btn3);
            }
        });
        setContentView(layout);
    }
}
