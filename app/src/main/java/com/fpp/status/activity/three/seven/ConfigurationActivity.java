package com.fpp.status.activity.three.seven;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fupengpeng
 * @description 系统设置事件
 * @data 2018/4/8 0008 14:36
 */

public class ConfigurationActivity extends Activity {
    @BindView(R.id.tv_atvt_configuration_screen_direction)
    TextView tvAtvtConfigurationScreenDirection;
    @BindView(R.id.tv_atvt_configuration_phone_direction)
    TextView tvAtvtConfigurationPhoneDirection;
    @BindView(R.id.tv_atvt_configuration_touch_screen)
    TextView tvAtvtConfigurationTouchScreen;
    @BindView(R.id.tv_atvt_configuration_network)
    TextView tvAtvtConfigurationNetwork;
    @BindView(R.id.btn_atvt_configuration)
    Button btnAtvtConfiguration;
    @BindView(R.id.btn_atvt_configuration_change)
    Button btnAtvtConfigurationChange;
    // 飞机移动速度
    private int speed = 10;
    private Configuration cfg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_configuration);
        ButterKnife.bind(this);

        // 获取系统Configuration对象
        cfg = getResources().getConfiguration();

        // 获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();


    }

    @OnClick({R.id.tv_atvt_configuration_screen_direction, R.id.tv_atvt_configuration_phone_direction,
            R.id.tv_atvt_configuration_touch_screen, R.id.tv_atvt_configuration_network,
            R.id.btn_atvt_configuration,R.id.btn_atvt_configuration_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_atvt_configuration_screen_direction:
                break;
            case R.id.tv_atvt_configuration_phone_direction:
                break;
            case R.id.tv_atvt_configuration_touch_screen:
                break;
            case R.id.tv_atvt_configuration_network:
                break;
            case R.id.btn_atvt_configuration:

                tvAtvtConfigurationScreenDirection.setText(cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕");
                tvAtvtConfigurationPhoneDirection.setText(cfg.orientation == Configuration.NAVIGATION_NONAV ?
                        "没有方向控制" : cfg.orientation == Configuration.NAVIGATION_WHEEL ?
                        "滚轮控制方向" : cfg.orientation == Configuration.NAVIGATION_DPAD ? "方向键控制方向" : "轨迹球控制方向");
                tvAtvtConfigurationTouchScreen.setText(cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸屏" : "支持触摸屏");
                tvAtvtConfigurationNetwork.setText("国家码 = " + cfg.mcc + "    网络码 = " + cfg.mnc + "");

                break;
            case R.id.btn_atvt_configuration_change:
                // 如果是横屏
                if (cfg.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    // 设置竖屏
                    ConfigurationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                // 如果是竖屏
                if (cfg.orientation == Configuration.ORIENTATION_PORTRAIT){
                    // 设置横屏
                    ConfigurationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }

                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.e("onConfigurationChanged","系统屏幕发生变化" + "  \n 修改后屏幕方向 = " +
                (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕"));
    }
}
