package com.fpp.status.activity.popupwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.adapter.OrderParticularsAdapter;
import com.fpp.status.entity.LVOne;
import com.fpp.status.entity.ServiceItemData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 服务单详情页面
 */

public class OrderParticularsActivity extends AppCompatActivity {


    public static final String TAG = "ServiceOrderParticularsActivity";

    LVOne lvOne = new LVOne();


    /**
     * 模拟数据（用户服务项）
     */
    List<ServiceItemData> data;

    /**
     * 转为会员的点击事件识别  为true时，转为会员，为false时，拨打电话
     */
    boolean call = true;
    @BindView(R.id.tv_atvt_service_order_particulars_time)
    TextView tvAtvtServiceOrderParticularsTime;
    @BindView(R.id.lv_atvt_service_order_particulars_service_item)
    ListView lvAtvtServiceOrderParticularsServiceItem;

    List list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_particulars);
        ButterKnife.bind(this);
        Log.e(TAG, "onCreate: " + "OrderParticularsActivity");
        /**
         * 设置服务单时间
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
        Date date = new Date();
        String dateString = sdf.format(date);
        tvAtvtServiceOrderParticularsTime.setText(dateString);


        initview();
    }

    private void initview() {
        //获取将要绑定的数据设置到data中
        Log.e(TAG, "initview: " + "OrderParticularsActivity  ======");
        data = getData();
        OrderParticularsAdapter adapter = new OrderParticularsAdapter(this, data);
        lvAtvtServiceOrderParticularsServiceItem.setAdapter(adapter);
    }

    public List<ServiceItemData> getData() {
        List<ServiceItemData> data = new ArrayList<ServiceItemData>();
        for (int i = 1; i < 20; i++) {
            ServiceItemData serviceItemData = new ServiceItemData();
            serviceItemData.setName("服务or商品" + i);
            serviceItemData.setNumber(i);
            serviceItemData.setPrice(5 + i);
            data.add(serviceItemData);
        }
        return data;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }



}
