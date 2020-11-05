package com.fpp.status.activity.fragmentseven;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.entity.ShopAssistant;
import com.fpp.status.entity.ShopAssistantCategory;
import com.fpp.status.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author fupengpeng
 * @description 竖向listview嵌套listview
 * @date 2017/12/25 0025 8:50
 */

public class FragmentSevenActivity extends AppCompatActivity {


    @BindView(R.id.tv_title_activity_title)
    TextView tvTitleActivityTitle;

    @BindView(R.id.lv_atvt_select_waiter_classify_content)
    ListView lvAtvtSelectWaiterClassifyContent;
    @BindView(R.id.tv_atvt_select_waiter_affirm_transfer_order)
    TextView tvAtvtSelectWaiterAffirmTransferOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_seven);
        ButterKnife.bind(this);

        tvTitleActivityTitle.setText("选择服务员");

        initView();


    }


    private void initView() {

        final ShopAssistantCategoryAdapter shopAssistantCategoryAdapter = new ShopAssistantCategoryAdapter(FragmentSevenActivity.this, getData());

        lvAtvtSelectWaiterClassifyContent.setAdapter(shopAssistantCategoryAdapter);



    }


    public List<ShopAssistantCategory> getData() {
        List<ShopAssistantCategory> shopAssistantCategoryList = new ArrayList<ShopAssistantCategory>();
        for (int i = 0; i < 5; i++) {
            ShopAssistantCategory shopAssistantCategory = new ShopAssistantCategory();
            shopAssistantCategory.setPostid("岗位id" + i);
            shopAssistantCategory.setPostname("岗位名称---" + i);

            List<ShopAssistant> shopAssistantList = new ArrayList<ShopAssistant>();
            for (int j = 0; j < 3; j++) {
                ShopAssistant shopAssistant = new ShopAssistant();
                shopAssistant.setUserid("人员id" + j);
                shopAssistant.setUsername("人员姓名" + j);
                shopAssistant.setJobnumber("人员工号" + j);
                shopAssistant.setJobid("人员级别" + j);
                shopAssistant.setJobname("人员级别名" + j);
                shopAssistant.setServe(false);

                shopAssistantList.add(shopAssistant);
            }

            shopAssistantCategory.setUserlist(shopAssistantList);

            shopAssistantCategoryList.add(shopAssistantCategory);

        }

        return shopAssistantCategoryList;

    }

    public TextView getTvAtvtSelectWaiterAffirmTransferOrder() {
        return tvAtvtSelectWaiterAffirmTransferOrder;
    }

    public void setTvAtvtSelectWaiterAffirmTransferOrder(TextView tvAtvtSelectWaiterAffirmTransferOrder) {
        this.tvAtvtSelectWaiterAffirmTransferOrder = tvAtvtSelectWaiterAffirmTransferOrder;
    }

    @OnClick(R.id.tv_atvt_select_waiter_affirm_transfer_order)
    public void onViewClicked() {

        for (ShopAssistantCategory shopAssistantCategory : getData()) {
            for (ShopAssistant shopAssistant : shopAssistantCategory.getUserlist()) {
                LogUtils.e("设置所有的工作人员没有被选中");
                if (shopAssistant.isServe()) {
                    tvAtvtSelectWaiterAffirmTransferOrder.setBackgroundColor(Color.BLACK);
                }else {
                    Toast.makeText(this,"请选择服务员",Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
