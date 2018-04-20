package com.fpp.status.activity.eight.three;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;
import com.fpp.status.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fupengpeng
 * @description 权限管理
 * @data 2018/4/18 0018 17:27
 */

public class PermissionManageOneActivity extends AppCompatActivity {


    @BindView(R.id.iv_atvt_permission_manage)
    ImageView ivAtvtPermissionManage;
    @BindView(R.id.btn_atvt_permission_manage_calendar)
    Button btnAtvtPermissionManageCalendar;
    @BindView(R.id.btn_atvt_permission_manage_camera)
    Button btnAtvtPermissionManageCamera;
    @BindView(R.id.btn_atvt_permission_manage_contacts)
    Button btnAtvtPermissionManageContacts;
    @BindView(R.id.btn_atvt_permission_manage_location)
    Button btnAtvtPermissionManageLocation;
    @BindView(R.id.btn_atvt_permission_manage_microphone)
    Button btnAtvtPermissionManageMicrophone;
    @BindView(R.id.btn_atvt_permission_manage_phone)
    Button btnAtvtPermissionManagePhone;
    @BindView(R.id.btn_atvt_permission_manage_sensors)
    Button btnAtvtPermissionManageSensors;
    @BindView(R.id.btn_atvt_permission_manage_sms)
    Button btnAtvtPermissionManageSms;
    @BindView(R.id.btn_atvt_permission_manage_storage)
    Button btnAtvtPermissionManageStorage;

    private PermissionManageOneActivity PMA = PermissionManageOneActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_manage_one);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


    /**
     * 权限申请返回结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1001:

                LogUtils.e("requestCode =  " + requestCode + "   permissions = " + permissions[0] + "   grantResults = " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: 2018/4/18 0018 有日历权限，相关操作
                    ToastUtils.showLong(this, "已有日历权限，进行相关操作");
                    LogUtils.e("已有日历权限，进行相关操作");
                } else {
                    // TODO: 2018/4/18 0018 用户禁止了相关权限，对应提示
                    ToastUtils.showLong(this, "用户禁止了日历相关权限");
                    LogUtils.e("用户禁止了日历相关权限");
                }
                break;
            case 1002:
                LogUtils.e("requestCode =  " + requestCode + "   permissions = " + permissions[0] + "   grantResults = " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: 2018/4/18 0018 有日历权限，相关操作
                    ToastUtils.showLong(this, "已有相机权限，进行相关操作");
                    LogUtils.e("已有相机权限，进行相关操作");
                } else {
                    // TODO: 2018/4/18 0018 用户禁止了相关权限，对应提示
                    ToastUtils.showLong(this, "用户禁止了相机相关权限");
                    LogUtils.e("用户禁止了相机相关权限");
                }

                break;
            case 1003:

                LogUtils.e("requestCode =  " + requestCode + "   permissions = " + permissions[0] + "   grantResults = " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: 2018/4/18 0018 有日历权限，相关操作
                    ToastUtils.showLong(this, "已有通讯录权限，进行相关操作");
                    LogUtils.e("已有通讯录权限，进行相关操作");
                } else {
                    // TODO: 2018/4/18 0018 用户禁止了相关权限，对应提示
                    ToastUtils.showLong(this, "用户禁止了通讯录相关权限");
                    LogUtils.e("用户禁止了通讯录相关权限");
                }
                break;
            case 1004:
                LogUtils.e("requestCode =  " + requestCode + "   permissions = " + permissions[0] + "   grantResults = " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: 2018/4/18 0018 有位置权限，相关操作
                    ToastUtils.showLong(this, "已有位置权限，进行相关操作");
                    LogUtils.e("已有位置权限，进行相关操作");
                } else {
                    // TODO: 2018/4/18 0018 用户禁止了相关权限，对应提示
                    ToastUtils.showLong(this, "用户禁止了位置相关权限");
                    LogUtils.e("用户禁止了位置相关权限");
                }
                break;
            case 1005:
                LogUtils.e("requestCode =  " + requestCode + "   permissions = " + permissions[0] + "   grantResults = " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: 2018/4/18 0018 有麦克风权限，相关操作
                    ToastUtils.showLong(this, "已有麦克风权限，进行相关操作");
                    LogUtils.e("已有麦克风权限，进行相关操作");
                } else {
                    // TODO: 2018/4/18 0018 用户禁止了相关权限，对应提示
                    ToastUtils.showLong(this, "用户禁止了麦克风相关权限");
                    LogUtils.e("用户禁止了麦克风相关权限");
                }
                break;
            case 1006:
                LogUtils.e("requestCode =  " + requestCode + "   permissions = " + permissions[0] + "   grantResults = " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: 2018/4/18 0018 有电话相关权限，相关操作
                    ToastUtils.showLong(this, "已有电话相关权限，进行相关操作");
                    LogUtils.e("已有电话相关权限，进行相关操作");
                } else {
                    // TODO: 2018/4/18 0018 用户禁止了相关权限，对应提示
                    ToastUtils.showLong(this, "用户禁止了电话相关权限");
                    LogUtils.e("用户禁止了电话相关权限");
                }
                break;
            case 1007:
                LogUtils.e("requestCode =  " + requestCode + "   permissions = " + permissions[0] + "   grantResults = " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: 2018/4/18 0018 有传感器相关权限，相关操作
                    ToastUtils.showLong(this, "已有传感器相关权限，进行相关操作");
                    LogUtils.e("已有传感器相关权限，进行相关操作");
                } else {
                    // TODO: 2018/4/18 0018 用户禁止了相关权限，对应提示
                    ToastUtils.showLong(this, "用户禁止了传感器相关权限");
                    LogUtils.e("用户禁止了传感器相关权限");
                }
                break;
            case 1008:
                LogUtils.e("requestCode =  " + requestCode + "   permissions = " + permissions[0] + "   grantResults = " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: 2018/4/18 0018 有短信相关权限，相关操作
                    ToastUtils.showLong(this, "已有短信相关权限，进行相关操作");
                    LogUtils.e("已有短信相关权限，进行相关操作");
                } else {
                    // TODO: 2018/4/18 0018 用户禁止了相关权限，对应提示
                    ToastUtils.showLong(this, "用户禁止了短信相关权限");
                    LogUtils.e("用户禁止了短信相关权限");
                }
                break;
            case 1009:
                LogUtils.e("requestCode =  " + requestCode + "   permissions = " + permissions[0] + "   grantResults = " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: 2018/4/18 0018 有文件存储相关权限，相关操作
                    ToastUtils.showLong(this, "已有文件存储相关权限，进行相关操作");
                    LogUtils.e("已有文件存储相关权限，进行相关操作");
                } else {
                    // TODO: 2018/4/18 0018 用户禁止了相关权限，对应提示
                    ToastUtils.showLong(this, "用户禁止了文件存储相关权限");
                    LogUtils.e("用户禁止了文件存储相关权限");
                }
                break;
        }

    }


    @OnClick({R.id.iv_atvt_permission_manage, R.id.btn_atvt_permission_manage_calendar,
            R.id.btn_atvt_permission_manage_camera, R.id.btn_atvt_permission_manage_contacts,
            R.id.btn_atvt_permission_manage_location, R.id.btn_atvt_permission_manage_microphone,
            R.id.btn_atvt_permission_manage_phone, R.id.btn_atvt_permission_manage_sensors,
            R.id.btn_atvt_permission_manage_sms, R.id.btn_atvt_permission_manage_storage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_atvt_permission_manage:
                //弹出对话框
                final AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.show();
                //自定义dialog的ui
                Window window = dialog.getWindow();
                View dialogView = View.inflate(this, R.layout.dialog_billing_select_image_select, null);
                LinearLayout llSelectCamera = (LinearLayout) dialogView.findViewById(R.id.ll_dialog_billing_select_camera);
                llSelectCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2018/1/12 0012  相机拍照

                        dialog.dismiss();
                    }
                });

                LinearLayout llSelectPhotoAlbum = (LinearLayout) dialogView.findViewById(R.id.ll_dialog_billing_select_photo_album);
                llSelectPhotoAlbum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 从相册中选择
                        dialog.dismiss();
                    }
                });
                window.setContentView(dialogView);
                break;
            case R.id.btn_atvt_permission_manage_calendar:

                //判断是否有日历权限
                if (ContextCompat.checkSelfPermission(PMA, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission.WRITE_CALENDAR}, 1001);
                } else {
                    // TODO: 2018/4/18 0018 有日历权限，相关操作
                    ToastUtils.showLong(this, "已有日历权限，进行相关操作");
                    LogUtils.e("已有日历权限，进行相关操作");
                }

                break;
            case R.id.btn_atvt_permission_manage_camera:
                //判断是否有相机权限
                if (ContextCompat.checkSelfPermission(PMA, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission.CAMERA}, 1002);
                } else {
                    // TODO: 2018/4/18 0018 有相机权限，相关操作
                    ToastUtils.showLong(this, "已有相机权限，进行相关操作");
                    LogUtils.e("已有相机权限，进行相关操作");
                }
                break;
            case R.id.btn_atvt_permission_manage_contacts:
                //判断是否有通讯录权限
                if (ContextCompat.checkSelfPermission(PMA, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission.WRITE_CONTACTS}, 1003);
                } else {
                    // TODO: 2018/4/18 0018 有通讯录权限，相关操作
                    ToastUtils.showLong(this, "已有通讯录权限，进行相关操作");
                    LogUtils.e("已有通讯录权限，进行相关操作");
                }
                break;
            case R.id.btn_atvt_permission_manage_location:
                //判断是否有位置权限
                if (ContextCompat.checkSelfPermission(PMA, Manifest.permission.LOCATION_HARDWARE) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission.LOCATION_HARDWARE}, 1004);
                } else {
                    // TODO: 2018/4/18 0018 有位置权限，相关操作
                    ToastUtils.showLong(this, "已有位置权限，进行相关操作");
                    LogUtils.e("已有位置权限，进行相关操作");
                }
                break;
            case R.id.btn_atvt_permission_manage_microphone:
                //判断是否有麦克风权限
                if (ContextCompat.checkSelfPermission(PMA, Manifest.permission_group.MICROPHONE) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission_group.MICROPHONE}, 1005);
                } else {
                    // TODO: 2018/4/18 0018 有麦克风权限，相关操作
                    ToastUtils.showLong(this, "已有麦克风权限，进行相关操作");
                    LogUtils.e("已有麦克风权限，进行相关操作");
                }
                break;
            case R.id.btn_atvt_permission_manage_phone:
                //判断是否有电话相关权限
                if (ContextCompat.checkSelfPermission(PMA, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission.CALL_PHONE}, 1006);
                } else {
                    // TODO: 2018/4/18 0018 有电话相关权限，相关操作
                    ToastUtils.showLong(this, "已有电话相关权限，进行相关操作");
                    LogUtils.e("已有电话相关权限，进行相关操作");
                }
                break;
            case R.id.btn_atvt_permission_manage_sensors:
                //判断是否有传感器相关权限
                if (ContextCompat.checkSelfPermission(PMA, Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission.BODY_SENSORS}, 1007);
                } else {
                    // TODO: 2018/4/18 0018 有传感器相关权限，相关操作
                    ToastUtils.showLong(this, "已有传感器相关权限，进行相关操作");
                    LogUtils.e("已有传感器相关权限，进行相关操作");
                }
                break;
            case R.id.btn_atvt_permission_manage_sms:
                //判断是否有短信相关权限
                if (ContextCompat.checkSelfPermission(PMA, Manifest.permission_group.SMS) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission_group.SMS}, 1008);
                } else {
                    // TODO: 2018/4/18 0018 有短信相关权限，相关操作
                    ToastUtils.showLong(this, "已有短信相关权限，进行相关操作");
                    LogUtils.e("已有短信相关权限，进行相关操作");
                }
                break;
            case R.id.btn_atvt_permission_manage_storage:
                //判断是否有文件存储相关权限
                if (ContextCompat.checkSelfPermission(PMA, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1009);
                } else {
                    // TODO: 2018/4/18 0018 有文件存储相关权限，相关操作
                    ToastUtils.showLong(this, "已有文件存储相关权限，进行相关操作");
                    LogUtils.e("已有文件存储相关权限，进行相关操作");
                }
                break;
        }
    }
}
