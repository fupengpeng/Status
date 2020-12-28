package com.fpp.status.activity.twelve.two;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.view.View.OnClickListener;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtil;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class SSActivity extends AppCompatActivity implements OnClickListener {
    private Button Camera;
    private Button Album;
    private Button Pick;
    private Uri imageUri;
    private ImageView Image;
    private ImageButton Image_Pick;
    private File mFile;
    private Button btnAtyMainImage;
    private Uri cropImageUri;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_s);
        initView();
        Image = (ImageView) findViewById(R.id.image);
    }


    private void initView() {
        Camera = findViewById(R.id.camera);
        Image = findViewById(R.id.image);
        Image_Pick = findViewById(R.id.image_pick);
        btnAtyMainImage = findViewById(R.id.btn_aty_main_image);
        Album = findViewById(R.id.album);
        Pick = findViewById(R.id.pick);
        Camera.setOnClickListener((View.OnClickListener) this);
        Album.setOnClickListener((View.OnClickListener) this);
        btnAtyMainImage.setOnClickListener((View.OnClickListener) this);
        Pick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.album:
                toPicture();
                Toast.makeText(SSActivity.this, "album", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_aty_main_image:
                toImageCrop();
                break;
            case R.id.camera:
                getPermissions();
                break;
        }
    }

    //跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);  //跳转到 ACTION_IMAGE_CAPTURE
        intent.setType("image/*");
        LogUtil.e("跳转相册成功,选择照片");
        startActivityForResult(intent, 100);
    }

    private void toImageCrop() {
        Intent intent = new Intent(Intent.ACTION_PICK);  //跳转到 ACTION_IMAGE_CAPTURE
        intent.setType("image/*");
        LogUtil.e("Album selection photos");
        startActivityForResult(intent, 102);
    }


    //跳转相机
    private void toCamera() {
        mFile = new File(this.getExternalFilesDir(null), "crop.jpg");
        LogUtil.e("跳转相机成功" + mFile.getAbsolutePath());
        imageUri = FileProvider.getUriForFile(
                this,
                getPackageName() + ".fileProvider",
                mFile);
        LogUtil.e("跳转相机成功" + imageUri.getPath());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 101);
        LogUtil.e("跳转相机成功");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //判断返回码不等于0
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != RESULT_CANCELED) {    //RESULT_CANCELED = 0(也可以直接写“if (requestCode != 0 )”)
            //读取返回码
            switch (requestCode) {
                case 100:   //相册返回的数据（相册的返回码）
                    LogUtil.e("相册选择照片成功：" + data.getData() + "   " + data.getData().getPath());
                    Uri uri01 = data.getData();
                    try {
                        final Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri01));
                        Image.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 101:  //相机返回的数据（相机的返回码）
                    LogUtil.e("uri022  " + imageUri.getPath());
                    Image.setImageURI(imageUri);
                    LogUtil.e("Image");
                    break;
                case 102:
                    Uri selectUri = data.getData();
                    cropImage(selectUri);
                    break;
                case 103:
                    LogUtil.e("crop pic success : cropImageUri = " + data.getData());
                    LogUtil.e("crop pic success : cropImageUri = " + cropImageUri);
                    Image.setImageURI(cropImageUri);
                    break;
            }
        }
    }

    /**
     * Cut out pictures
     *
     * @param selectUri
     */
    private void cropImage(Uri selectUri) {
        File mFileImg = new File(this.getExternalFilesDir(null), "crop5.jpg");
        cropImageUri = Uri.fromFile(mFileImg);   //图片文件
        LogUtil.e("Cut out pictures save path：" + "   cropImageUri:" + cropImageUri.getPath());
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(selectUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 900);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        LogUtil.e("Cut out pictures" + " path:" + selectUri.getPath());
        startActivityForResult(intent, 103);
    }


    private void getPermissions() {
        List<String> perms = new ArrayList<>();
        perms.add(Manifest.permission.CAMERA);
        perms.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        perms.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> permsNot = new ArrayList<>();
        for (int i = 0; i < perms.size(); i++) {
            int per = ContextCompat.checkSelfPermission(this, perms.get(i));
            if (per != PERMISSION_GRANTED) permsNot.add(perms.get(i));
        }
        if (permsNot.size() > 0) {
            String[] permissions = new String[permsNot.size()];
            permsNot.toArray(permissions);
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            // TODO: fpp 2020/12/25 0025 已有所有授权
            //已经有权限
            toCamera();  //打开相机
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (hasAllPermissionsGranted(grantResults)) {
                // TODO: fpp 2020/12/25 0025 获取到所需授权
                //已经有权限
                toCamera();  //打开相机
            }
        }
    }

    /**
     * 是否授权所有权限
     *
     * @param grantResults 授权响应集合
     * @return 响应
     */
    private boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) return false;
        }
        return true;
    }

    public static String getImageAbsolutePath(Activity context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    /**
     * 通过Uri返回File文件
     * 注意：通过相机的是类似content://media/external/images/media/97596
     * 通过相册选择的：file:///storage/sdcard0/DCIM/Camera/IMG_20150423_161955.jpg
     * 通过查询获取实际的地址
     *
     * @param uri
     * @return
     */
    public File getFileByUri(Uri uri) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
            LogUtil.e("Uri Scheme:" + uri.getScheme());
        }
        return null;
    }

}