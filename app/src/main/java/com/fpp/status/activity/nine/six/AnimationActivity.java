package com.fpp.status.activity.nine.six;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.fpp.status.R;

<<<<<<< HEAD
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
=======
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Author: fpp
 * Date: 2018/6/28  15:20
 */

public class AnimationActivity extends AppCompatActivity {

    @BindView(R.id.btn_atvt_animation_show)
    Button btnAtvtAnimationShow;
    @BindView(R.id.btn_atvt_animation_hide)
    Button btnAtvtAnimationHide;
    private AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        showExitDialog();
    }
    @OnClick({R.id.btn_atvt_animation_show, R.id.btn_atvt_animation_hide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_atvt_animation_show:

                dialog.show();
                break;
            case R.id.btn_atvt_animation_hide:

                break;
        }
    }
    /**
     * 弹出退出对话框
     */
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AnimationActivity.this);
        builder.setTitle("退出");
        builder.setMessage("确定要退出吗？");
        builder.setNegativeButton("取消", null);
        builder.setNeutralButton("支持", null);
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 停止服务
//                stopService(new Intent(AnimationActivity.this, MainService.class));
                // 结束当前窗体
                AnimationActivity.this.finish();
            }
        });
        dialog = builder.create();

        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.dialog_anim);
    }


}
