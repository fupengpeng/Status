package com.fpp.status.activity.eight.four;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.fpp.status.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/20 0020 14:18
 */

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.lv_atvt_result)
    ListView lvAtvtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        List<Map<String, String>> list = (List<Map<String, String>>) data.getSerializable("data");
        SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this, list,
                R.layout.line, new String[]{"word", "detail"},
                new int[]{R.id.tv_lvi_title, R.id.file_name});
        lvAtvtResult.setAdapter(adapter);
    }

}
