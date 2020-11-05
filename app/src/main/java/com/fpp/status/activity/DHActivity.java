package com.fpp.status.activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.fpp.status.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DHActivity extends AppCompatActivity {

    @BindView(R.id.btn_move)
    Button btnMove;
    @BindView(R.id.iv_anim_test)
    ImageView ivAnimTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_move)
    public void onViewClicked() {

        Animation scaleAnimation = AnimationUtils.loadAnimation(DHActivity.this, R.anim.ll_atvt_main_iv_anim_s);

        ivAnimTest.startAnimation(scaleAnimation);

    }
}
