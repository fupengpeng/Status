package com.fpp.status.activity.three.six;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.activity.three.six.adapter.RVAdapter;
import com.fpp.status.activity.three.six.customview.LeftSwipeMenuRecyclerView;
import com.fpp.status.activity.three.six.entity.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_LONG;

public class MoveDeleteRecycleViewItemActivity extends AppCompatActivity {

    @BindView(R.id.commodity_list)
    LeftSwipeMenuRecyclerView commodityList;


    /**
     * 商品数据
     */
    List<Message> datas;


    RVAdapter adapters;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_delete_recycle_view_item);
        ButterKnife.bind(this);

        datas = getDatas();
        // 初始化布局管理器
        LeftSwipeMenuRecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        // 设置布局管理器
        commodityList.setLayoutManager(layoutManager);
        // 初始化适配器
        adapters=new RVAdapter(this,datas);
        // 设置适配器
        commodityList.setAdapter(adapters);

        commodityList.setOnItemActionListener(new LeftSwipeMenuRecyclerView.IOnItemActionListener() {
            @Override
            public void delete(int position) {
                Toast.makeText(MoveDeleteRecycleViewItemActivity.this,
                        "点击删除第" + position + "条数据" , LENGTH_LONG).show();
                datas.remove(position);
                adapters.notifyDataSetChanged();

            }
        });


    }


    public List<Message> getDatas(){
        List<Message> messageList = new ArrayList<Message>();
        for (int i = 0; i < 15 ; i++) {
            Message message = new Message();
            message.setName("商品 " + i);
            message.setFid(" +"  + i);
            messageList.add(message);
        }

        return messageList;
    }


}
