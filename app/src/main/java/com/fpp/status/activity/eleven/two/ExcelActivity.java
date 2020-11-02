package com.fpp.status.activity.eleven.two;

import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.base.BaseApplication;
import com.fpp.status.entity.CardBean;
import com.fpp.status.utils.DateTimeUtil;
import com.fpp.status.utils.ExcelUtil;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelActivity extends Activity implements View.OnClickListener {

    private Button exportButton;
    private Button openButton;
    private TextView textView;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        exportButton = findViewById(R.id.export_button);
        exportButton.setOnClickListener(this);
        openButton = findViewById(R.id.open_button);
        openButton.setOnClickListener(this);
        textView = findViewById(R.id.textView);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.export_button:
                exportExcel(this);
                break;
            case R.id.open_button:
                openDir();
            default:
                break;
        }
    }

    private void openDir() {
        File file = new File(BaseApplication.PATH_DIR);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setDataAndType(Uri.fromFile(file), "file/*");
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "没有正确打开文件管理器", Toast.LENGTH_SHORT).show();
        }
    }


    private void exportExcel(Context context) {
        File file = new File(BaseApplication.PATH_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        String excelFileName = File.separator + "RECORDS_" + DateTimeUtil.getDateTime("yyyyMMdd_hhmmss") + ".xls";
        String[] title = {"atr",  "boolean", "编号", "数量","atrr", "artt"};
        List<CardBean> demoBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CardBean cardBean = new CardBean("0000", "1111", "2222", "true", "" + i, "" + i);
            demoBeanList.add(cardBean);
        }
        String filePath = BaseApplication.PATH_DIR + excelFileName;
        ExcelUtil.initExcel(filePath, title);
        ExcelUtil.writeObjListToExcel(demoBeanList, filePath, context);
        textView.setText("excel已导出至：" + filePath);
    }
}
