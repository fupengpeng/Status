package com.fpp.status.activity.eleven.five;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.fpp.status.R;
import com.fpp.status.utils.KeyboardUtil;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyBoardActivity extends AppCompatActivity {

    @BindView(R.id.et_aty_change_password_old)
    EditText etAtyChangePasswordOld;
    @BindView(R.id.et_aty_change_password_new)
    EditText etAtyChangePasswordNew;
    @BindView(R.id.et_aty_change_password_affirm)
    EditText etAtyChangePasswordAffirm;
    @BindView(R.id.kv_keyboard)
    KeyboardView kvKeyboard;

    public Activity mAty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
        ButterKnife.bind(this);
        mAty = this;
        initView();
    }

    private void initView() {
        new KeyboardUtil(mAty, kvKeyboard, etAtyChangePasswordOld);
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                switch (v.getId()) {
                    case R.id.et_aty_change_password_old:
                        if (hasFocus) {
                            // 获得焦点
                            new KeyboardUtil(mAty, kvKeyboard, etAtyChangePasswordOld);
                        } else {
                            // 失去焦点
                        }
                        break;
                    case R.id.et_aty_change_password_new:
                        if (hasFocus) {
                            // 获得焦点
                            new KeyboardUtil(mAty, kvKeyboard, etAtyChangePasswordNew);
                        } else {
                            // 失去焦点
                        }
                        break;
                    case R.id.et_aty_change_password_affirm:
                        if (hasFocus) {
                            // 获得焦点
                            new KeyboardUtil(mAty, kvKeyboard, etAtyChangePasswordAffirm);
                        } else {
                            // 失去焦点
                        }
                        break;

                }
            }
        };
        etAtyChangePasswordOld.setOnFocusChangeListener(onFocusChangeListener);
        etAtyChangePasswordNew.setOnFocusChangeListener(onFocusChangeListener);
        etAtyChangePasswordAffirm.setOnFocusChangeListener(onFocusChangeListener);
    }
}
