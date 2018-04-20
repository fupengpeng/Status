package com.fpp.status.activity.eight.three;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.activity.eight.two.ResourceActivity;
import com.fpp.status.utils.LogUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fupengpeng
 * @description 权限管理
 * @data 2018/4/18 0018 17:27
 */

public class PermissionManageActivity extends AppCompatActivity {


    /**
     * 扫描二维码返回结果码
     */
    private static final int REQUEST_CODE = 10001;

    /**
     * 拍照权限申请码
     */
    private final int CAMERA_PHOTO_REQUEST_CODE = 9001;
    /**
     * 相册权限申请码(存储)
     */
    private final int PHOTO_ALBUM_REQUEST_CODE = 9002;
    /**
     * 相机权限申请码
     */
    private final int CAMERA_REQUEST_CODE = 9003;

    /**
     * 图片处理响应码
     */
    private final int PICTURE_PROCESSING_REQUEST_CODE = 9004;

    /**
     * 创建的图片保存文件
     */
    File filep;

    /**
     * 图片保存路径
     */
    Uri imageUri;

    /**
     * 处理后图片（用于显示和上传）
     */
    Bitmap bitmap;

    /**
     * 拍照临时图片
     */
    protected static final String tempPhotoPath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES) + File.separator + "camera_tem_photo.jpeg";
    /**
     * 裁剪临时图片
     */
    protected static final String tempCropPhotoPath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES) + File.separator + "camera_tem_crop_photo.jpeg";

    private PermissionManageActivity PMA = PermissionManageActivity.this;


    @BindView(R.id.iv_atvt_permission_manage)
    ImageView ivAtvtPermissionManage;
    @BindView(R.id.btn_atvt_permission_manage)
    Button btnAtvtPermissionManage;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_manage);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @OnClick({R.id.iv_atvt_permission_manage, R.id.btn_atvt_permission_manage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_atvt_permission_manage:
                //弹出对话框
                final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
                dialog.show();
                //自定义dialog的ui
                Window window = dialog.getWindow();
                View dialogView = View.inflate(this, R.layout.dialog_billing_select_image_select, null);
                LinearLayout llSelectCamera = (LinearLayout) dialogView.findViewById(R.id.ll_dialog_billing_select_camera);
                llSelectCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2018/1/12 0012  相机拍照
                        //判断是否有相机和存储权限
                        checkSelfPermission(CAMERA_PHOTO_REQUEST_CODE);
                        dialog.dismiss();
                    }
                });

                LinearLayout llSelectPhotoAlbum = (LinearLayout) dialogView.findViewById(R.id.ll_dialog_billing_select_photo_album);
                llSelectPhotoAlbum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 从相册中选择
                        // TODO: 2018/1/11 0011  相册
                        checkSelfPermission(PHOTO_ALBUM_REQUEST_CODE);
                        dialog.dismiss();
                    }
                });
                window.setContentView(dialogView);
                break;
            case R.id.btn_atvt_permission_manage:
                break;
        }
    }


    /**
     * 检测用户是否拥有对应的权限
     *
     * @param tag 调用方法标识
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void checkSelfPermission(int tag) {
        switch (tag) {
            case CAMERA_PHOTO_REQUEST_CODE:
                //判断是否有相机和存储权限
                if ((ContextCompat.checkSelfPermission(PMA, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) ||
                        (ContextCompat.checkSelfPermission(PMA, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                        (ContextCompat.checkSelfPermission(PMA, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    //如果没有授权，则请求授权
                    ActivityCompat.requestPermissions(PMA, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, CAMERA_PHOTO_REQUEST_CODE);
                } else {
                    // TODO: 2018/1/12 0012 相机拍照 ----- 判断系统版本决定是否拍照（涉及Android7.0存储空间问题）
                    android7(CAMERA_PHOTO_REQUEST_CODE);
                }
                break;
            case PHOTO_ALBUM_REQUEST_CODE:
                // 判断有无存储空间权限
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        &&
//                        !(PackageManager.PERMISSION_GRANTED ==
//                        PMA.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", PMA.getPackageName()))
                        (ContextCompat.checkSelfPermission(PMA, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))

                {
                    // 申请权限
                    PMA.requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PHOTO_ALBUM_REQUEST_CODE);
                    // 解释权限(系统自带)
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    //有存储空间权限
                    // TODO: 2018/1/12 0012 相册 ----- 判断系统版本决定是否拍照（涉及Android7.0存储空间问题）
                    android7(PHOTO_ALBUM_REQUEST_CODE);
                }
                break;
            case CAMERA_REQUEST_CODE:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请相机权限
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                } else {
                    // TODO: 2018/1/12 0012 已有相机权限，进行二维码扫描方法
                    intent = new Intent(PMA, ResourceActivity.class);
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
                break;

        }
    }


    /**
     * android 7.0 文件路径处理
     */
    public void android7(int tag) {
        //获取系統版本
        int currentapiVersion = Build.VERSION.SDK_INT;
        switch (tag) {
            case CAMERA_PHOTO_REQUEST_CODE:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (hasSdcard()) {
                    filep = new File(tempPhotoPath);
                    LogUtils.e("file = " + filep.toURI().toString());      //  file:/storage/emulated/0/Pictures/tks_temp_photo.jpeg
                    // 判断系统版本，根据是否大于24决定uri创建方式
                    if (currentapiVersion < 24) {
                        // 从文件中创建uri
                        imageUri = Uri.fromFile(filep);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    } else {
                        //兼容android7.0 使用共享文件的形式
                        ContentValues contentValues = new ContentValues(1);
                        contentValues.put(MediaStore.Images.Media.DATA, filep.getAbsolutePath());
                        //检查是否有存储权限，以免崩溃
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //申请WRITE_EXTERNAL_STORAGE权限
                            Toast.makeText(this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        imageUri = FileProvider.getUriForFile(this, "com.fpp.status.fileProvider", filep);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    }
                    LogUtils.e("imageUri = " + imageUri.toString());
                }
                // TODO: 2018/4/18 0018 检查是否有相机权限和存储权限 
                startActivityForResult(intent, CAMERA_PHOTO_REQUEST_CODE);
                break;

            case PHOTO_ALBUM_REQUEST_CODE:
                //从图库中获取资源
                // Intent.ACTION_PICK 进入图库获取照片意图
                Intent intent = new Intent(Intent.ACTION_PICK);
                //设置类型
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PHOTO_ALBUM_REQUEST_CODE);
                break;
        }
    }

    /**
     * 判断sdcard是否被挂载
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
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
            case CAMERA_PHOTO_REQUEST_CODE:
                // TODO: 2018/1/12 0012 相机拍照 ----- 判断系统版本决定是否拍照（涉及Android7.0存储空间问题）
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    android7(CAMERA_PHOTO_REQUEST_CODE);
                } else {
                    //用户勾选了不再询问
                    //提示用户手动打开权限
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) ||
                            !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "相机权限已被禁止,请手动设置允许相关权限！", Toast.LENGTH_SHORT).show();
                        intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                    }

                }

                break;
            case PHOTO_ALBUM_REQUEST_CODE:
                // TODO: 2018/1/12 0012 相册 ----- 判断系统版本决定是否拍照（涉及Android7.0存储空间问题）
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    android7(PHOTO_ALBUM_REQUEST_CODE);
                } else {
                    //用户勾选了不再询问
                    //提示用户手动打开权限
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) ||
                            !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "相机权限已被禁止,请手动设置允许相关权限！", Toast.LENGTH_SHORT).show();
                        intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                    }
                }
                break;
            case CAMERA_REQUEST_CODE:
                // TODO: 2018/1/12 0012 相机扫描二维码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intent = new Intent(PMA, ResourceActivity.class);
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                } else {
                    //用户拒绝了所有权限
                    //提示用户手动打开权限
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) ||
                            !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "相机权限已被禁止,请手动设置允许相关权限！", Toast.LENGTH_SHORT).show();
                        intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                    }
                }
                break;
        }

    }

    /**
     * 调用系统应用返回结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("request = " + requestCode + "   result = " + resultCode);
        Bundle bundle;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_PHOTO_REQUEST_CODE:
                    // TODO: 2018/1/12 0012 相机拍照返回结果
                    Uri mImageCaptureUri;
                    File filePhoto = new File(tempPhotoPath);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //兼容android7.0 使用共享文件的形式
                        ContentValues contentValues = new ContentValues(1);
                        contentValues.put(MediaStore.Images.Media.DATA, filePhoto.getAbsolutePath());
                        //检查是否有存储权限，以免崩溃
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //申请WRITE_EXTERNAL_STORAGE权限
                            Toast.makeText(this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mImageCaptureUri = FileProvider.getUriForFile(this, "com.fpp.status.fileProvider", filePhoto);
                    } else {
                        mImageCaptureUri = Uri.fromFile(filePhoto);
                    }
                    pictureProcessing(mImageCaptureUri);
                    break;
                case PHOTO_ALBUM_REQUEST_CODE:
                    // TODO: 2018/1/12 0012 相册获取图片返回结果

                    pictureProcessing(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    // TODO: 2018/1/12 0012 相机扫描二维码返回结果
                    //处理扫描结果（在界面上显示）
                    if (null != data) {
                        bundle = data.getExtras();
                        if (bundle == null) {
                            return;
                        }
//                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                            String result = bundle.getString(CodeUtils.RESULT_STRING);
//                            LogUtils.e("二维码解析结果 == " + result);
//                            if (result != null) {
//                                mid = result.substring(4);
//                                LogUtils.e("mid = " + mid);
//                            }
//
//                            //根据会员id进行网络请求，获取会员信息
//                            if (TextUtils.isEmpty(mid)) {
//                                ToastUtils.showLong(this, "会员码无效，请切换会员码重新扫描！");
//                            } else {
//                                presenter.loadMemberList(mid, key);
//                            }
//                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                            Toast.makeText(BillingSelectActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
//                        }
                    }
                    break;

                case PICTURE_PROCESSING_REQUEST_CODE:
                    //图片处理返回结果
                    if (resultCode == RESULT_OK) {
                        try {
                            filep = new File(tempCropPhotoPath);
                            bitmap = BitmapFactory.decodeFile(filep.getAbsolutePath());
                            // 设置裁剪后的图片
                            ivAtvtPermissionManage.setImageBitmap(bitmap);
                        } catch (Exception e) {

                        }
                    }
                    break;
            }
        }
    }

    /**
     * 图片处理
     *
     * @param uri
     */
    public void pictureProcessing(Uri uri) {
        LogUtils.e("uri = " + uri.toString());
        //使用意图剪切照片
        Intent intent = new Intent();
        //设置要剪切的资源文件和类型
        intent.setDataAndType(uri, "image/*");
        //设置剪切
        intent.setAction("com.android.camera.action.CROP");
        //开启剪切
        intent.putExtra("crop", "true");
        //设置 裁剪框比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设置裁剪后输出的照片大小
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        File file = new File(tempCropPhotoPath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        //启动
        startActivityForResult(intent, PICTURE_PROCESSING_REQUEST_CODE);

    }


}
