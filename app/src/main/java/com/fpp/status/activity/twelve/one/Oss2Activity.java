package com.fpp.status.activity.twelve.one;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.CannedAccessControlList;
import com.alibaba.sdk.android.oss.model.CreateBucketRequest;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.fpp.status.R;
import com.fpp.status.base.BaseApplication;
import com.fpp.status.model.RequestListener;
import com.fpp.status.okhttp.CallBackUtil;
import com.fpp.status.okhttp.OkHttpUtil;
import com.fpp.status.utils.Constant;
import com.fpp.status.utils.LogUtil;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import retrofit2.http.Url;

public class Oss2Activity extends AppCompatActivity {

    @BindView(R.id.btn_aty_oss_token)
    Button btnAtyOssToken;
    @BindView(R.id.setting)
    Button setting;
    @BindView(R.id.select)
    Button select;
    @BindView(R.id.download)
    Button download;
    @BindView(R.id.upload)
    Button upload;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.bar)
    ProgressBar bar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.output_info)
    TextView outputInfo;

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_LOAD_VIDEO = 2;
    @BindView(R.id.select_video)
    Button selectVideo;
    private OSS oss;
    private String mPicturePath;
    private UIDisplayer mUIDisplayer;
    private Token token;
    private String imgName;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oss2);
        ButterKnife.bind(this);
        mUIDisplayer = new UIDisplayer(imageView, bar, outputInfo, this);
    }

    private void download(String bucketName, String fileName, String filePath) {
        final long get_start = System.currentTimeMillis();
        // 构造下载文件请求。
        GetObjectRequest get = new GetObjectRequest(bucketName, fileName);

        OSSAsyncTask task = oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                // 请求成功。
                LogUtil.e("download success:");
                LogUtil.e("Content-Length", "" + result.getContentLength());

                // 请求成功
                InputStream inputStream = result.getObjectContent();
                try {
                    File file = new File(filePath + File.separator + fileName);
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(filePath + File.separator + fileName);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                    byte[] buf = new byte[4096];
                    int length = bufferedInputStream.read(buf);
                    while (-1 != length) {
                        bufferedOutputStream.write(buf, 0, length);
                        length = bufferedInputStream.read(buf);
                    }

                    //需要根据对应的View大小来自适应缩放
                    Bitmap bm = mUIDisplayer.autoResizeFromStream(inputStream);
                    long get_end = System.currentTimeMillis();
                    LogUtil.e("get cost: " + (get_end - get_start) / 1000f);
                    mUIDisplayer.downloadComplete(bm);
                    mUIDisplayer.displayInfo("Bucket: " + Config.BUCKET_NAME + "\nObject: " + request.getObjectKey() + "\nRequestId: " + result.getRequestId());

                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                InputStream inputStream = result.getObjectContent();
//                byte[] buffer = new byte[2048];
//                int len;
//                try {
//                    while ((len = inputStream.read(buffer)) != -1) {
//                        // 您可以在此处编写代码来处理下载的数据。
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            // GetObject请求成功，将返回GetObjectResult，其持有一个输入流的实例。返回的输入流，请自行处理。
            public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                LogUtil.e("download fail:");
                String info = "";
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    info = clientExcepion.toString();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtil.e("ErrorCode", serviceException.getErrorCode());
                    LogUtil.e("RequestId", serviceException.getRequestId());
                    LogUtil.e("HostId", serviceException.getHostId());
                    LogUtil.e("RawMessage", serviceException.getRawMessage());
                    info = serviceException.toString();
                }
                mUIDisplayer.downloadFail(info);
                mUIDisplayer.displayInfo(info);
//                // 请求异常。
//                if (clientExcepion != null) {
//                    // 本地异常，如网络异常等。
//                    clientExcepion.printStackTrace();
//                }
//                if (serviceException != null) {
//                    // 服务异常。
//                                  LogUtil.e("ErrorCode", serviceException.getErrorCode());
//                                  LogUtil.e("RequestId", serviceException.getRequestId());
//                                  LogUtil.e("HostId", serviceException.getHostId());
//                                  LogUtil.e("RawMessage", serviceException.getRawMessage());
//                }
            }
        });

// task.cancel(); // 可以取消任务。
// task.waitUntilFinished(); // 等待任务完成。
    }

    private void download2() {

    }

    private void upload(String bucketName, String fileName, String filePath) {
        // 构造上传请求。
        PutObjectRequest put = new PutObjectRequest(bucketName, fileName, filePath);

        // 异步上传时可以设置进度回调。
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                LogUtil.e("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                LogUtil.e("upload success");
                LogUtil.e("ETag", result.getETag());
                LogUtil.e("RequestId", result.getRequestId());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    LogUtil.e("ErrorCode", serviceException.getErrorCode());
                    LogUtil.e("RequestId", serviceException.getRequestId());
                    LogUtil.e("HostId", serviceException.getHostId());
                    LogUtil.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
// task.cancel(); // 可以取消任务。
// task.waitUntilFinished(); // 等待上传完成。
    }

    private void createBucket() {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest("<bucketName>");
// 设置存储空间的访问权限为公共读，默认为私有读写。
        createBucketRequest.setBucketACL(CannedAccessControlList.PublicRead);
// 指定存储空间所在的地域。
        createBucketRequest.setLocationConstraint("oss-cn-hangzhou");
        OSSAsyncTask createTask = oss.asyncCreateBucket(createBucketRequest, new OSSCompletedCallback<CreateBucketRequest, CreateBucketResult>() {
            @Override
            public void onSuccess(CreateBucketRequest request, CreateBucketResult result) {
                LogUtil.e("locationConstraint", request.getLocationConstraint());
            }

            @Override
            public void onFailure(CreateBucketRequest request, ClientException clientException, ServiceException serviceException) {
                // 请求异常。
                if (clientException != null) {
                    // 本地异常，如网络异常等。
                    clientException.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    LogUtil.e("ErrorCode", serviceException.getErrorCode());
                    LogUtil.e("RequestId", serviceException.getRequestId());
                    LogUtil.e("HostId", serviceException.getHostId());
                    LogUtil.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    private void setEndPointOrProvider() {

        // 推荐使用OSSAuthCredentialsProvider。token过期可以及时更新。credentials
        OSSStsTokenCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(
                token.getCredentials().getAccessKeyId(), token.getCredentials().getAccessKeySecret(), token.getCredentials().getSecurityToken());

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个。
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。

        oss = new OSSClient(getApplicationContext(), Config.OSS_ENDPOINT, credentialProvider, conf);

    }

    @OnClick({R.id.btn_aty_oss_token, R.id.setting, R.id.select, R.id.download, R.id.upload, R.id.download2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_aty_oss_token:
                login(Config.URL_TOKEN, new RequestListener<String>() {
                    @Override
                    public void success(String data) {
                        LogUtil.e("token success:" + data);
                        setting.setEnabled(true);
                        token = new Gson().fromJson(data, Token.class);
                    }

                    @Override
                    public void fail(Exception e) {
                        LogUtil.e("token fail:" + e.getMessage());
                    }
                });
                break;
            case R.id.setting:
                LogUtil.e("setting start:");
                setEndPointOrProvider();
                select.setEnabled(true);
                LogUtil.e("setting end:");
                break;
            case R.id.select:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
            case R.id.select_video:
                intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, RESULT_LOAD_VIDEO);
                break;
            case R.id.download:
                LogUtil.e("download start:");
                String str = TextUtils.isEmpty(editText.getText().toString()) ? imgName : editText.getText().toString();
                download(Config.BUCKET_NAME, str, BaseApplication.PATH_DIR);
                break;
            case R.id.upload:
                upload(Config.BUCKET_NAME, imgName, mPicturePath);
                break;
            case R.id.download2:
                downloadFile("http://bsit-public.oss-cn-beijing.aliyuncs.com/adPush/13.jpg", "jjj.jpg", BaseApplication.PATH_DIR, "", new RequestListener<String>() {
                    @Override
                    public void success(String data) {

                    }

                    @Override
                    public void fail(Exception e) {

                    }
                });
                break;
        }
    }

    public static void downloadFile(String url, String fileName, String path, final String downloadType, final RequestListener<String> requestListener) {
        OkHttpUtil.okHttpDownloadFile(url, new CallBackUtil.CallBackFile(path, fileName) {
            @Override
            public void onFailure(Call call, Exception e) {
                LogUtil.e("heartbeat downloadApk fail---->" + "  response = " + e.getMessage());
            }

            @Override
            public void onResponse(File response) {
                LogUtil.e("heartbeat downloadApk success---->" + "  response = " + response.getPath());
            }
        });
    }


    public void download(String url, final RequestListener<String> requestListener) {
        OkHttpUtil.okHttpPost(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                requestListener.fail(e);
            }

            @Override
            public void onResponse(String response) {
                requestListener.success(response);
            }
        });
    }


    public void login(String url, final RequestListener<String> requestListener) {
        OkHttpUtil.okHttpPost(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                requestListener.fail(e);
            }

            @Override
            public void onResponse(String response) {
                requestListener.success(response);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    null, null, null, null);
            cursor.moveToFirst();

            String imgNo = cursor.getString(0); // 图片编号
            LogUtil.e("imgNo:", imgNo);
            mPicturePath = cursor.getString(1); // 图片文件路径
            LogUtil.e("mPicturePath:", mPicturePath);
            String imgSize = cursor.getString(2); // 图片大小
            LogUtil.e("imgSize:", imgSize);
            // 图片文件名
            imgName = cursor.getString(3);
            LogUtil.e("imgName:", imgName);
            LogUtil.e("PickPicture", mPicturePath);
            cursor.close();

            try {
                Bitmap bm = mUIDisplayer.autoResizeFromLocalFile(mPicturePath);
                mUIDisplayer.displayImage(bm);
                /*
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bm);*/
                File file = new File(mPicturePath);
                upload.setEnabled(true);
                download.setEnabled(true);
                mUIDisplayer.displayInfo("文件: " + mPicturePath + "\n大小: " + String.valueOf(file.length()));
            } catch (IOException e) {
                e.printStackTrace();
                mUIDisplayer.displayInfo(e.toString());
            }
        }
        if (requestCode == RESULT_LOAD_VIDEO && resultCode == RESULT_OK && null != data) {
            Uri selectedVideo = data.getData();
            Cursor cursor = getContentResolver().query(selectedVideo,
                    null, null, null, null);
            cursor.moveToFirst();

            String imgNo = cursor.getString(0);     // 视频编号
            LogUtil.e("imgNo:", imgNo);
            mPicturePath = cursor.getString(1);     // 视频文件路径
            LogUtil.e("mPicturePath:", mPicturePath);
            String imgSize = cursor.getString(2);   // 视频大小
            LogUtil.e("imgSize:", imgSize);
            imgName = cursor.getString(3);          // 视频文件名
            LogUtil.e("imgName:", imgName);
            cursor.close();
            mUIDisplayer.displayInfo("文件: " + mPicturePath + "\n大小: " + String.valueOf(imgSize));

        }

    }

}
