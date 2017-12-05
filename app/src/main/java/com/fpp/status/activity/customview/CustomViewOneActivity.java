package com.fpp.status.activity.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.view.CustomViewOne;

public class CustomViewOneActivity extends AppCompatActivity {
    private CustomViewOne mTitleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_one);

        mTitleBar=(CustomViewOne) findViewById(R.id.title_bar);

        mTitleBar.setLeftButtonListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(CustomViewOneActivity.this, "点击了返回按钮", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });


    }


}
