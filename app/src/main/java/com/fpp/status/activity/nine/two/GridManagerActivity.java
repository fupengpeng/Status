package com.fpp.status.activity.nine.two;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
<<<<<<< HEAD
=======
import android.support.v7.app.AppCompatActivity;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;
//import com.fpp.status.activity.nine.entity.ExerciseBottomBean;
//import com.fpp.status.entity.ExerciseTopBean;
//import com.fpp.status.okhttp.CallBackUtil;
//import com.fpp.status.okhttp.OkHttpUtil;
//import com.fpp.status.utils.LogUtils;
//import com.fpp.status.view.pulpdr.Divider.BaseDecoration;
//import com.fpp.status.view.pulpdr.HeaderAndFooter.HAFAdapter;
//import com.fpp.status.view.pulpdr.HeaderAndFooter.OnItemClickListener;
//import com.fpp.status.view.pulpdr.LayoutManager.GridLayoutManager;
//import com.fpp.status.view.pulpdr.PullToLoad.OnLoadListener;
//import com.fpp.status.view.pulpdr.PullToLoad.PullToLoadRecyclerView;
//import com.fpp.status.view.pulpdr.PullToRefresh.OnRefreshListener;
//import com.fpp.status.view.pulpdr.PullToRefresh.PullToRefreshRecyclerView;
//import com.fpp.status.view.pulpdr.SimpleAdapter.SimpleAdapter;
//import com.fpp.status.view.pulpdr.SimpleAdapter.ViewHolder;
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import okhttp3.Call;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

=======
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import static com.fpp.status.R.drawable.shilipic;

/**
 * Created by Administrator on 2016/9/30.
 */
public class GridManagerActivity extends AppCompatActivity {


//    @BindView(R.id.rcv_fragment_exam_subject_one)
//    PullToLoadRecyclerView rcvFragmentExamSubjectOne;
//    @BindView(R.id.rcv_fragment_exam_subject_two)
//    PullToLoadRecyclerView rcvFragmentExamSubjectTwo;
//    private Handler handler;
//    private List<ExerciseBottomBean.CourseDataBean.CourseBean> courseBeans;
//
//
//    private HashMap<String, String> map = new HashMap<>();
//    private ExerciseTopBean exerciseTopBean;
//    private ExerciseBottomBean exerciseBottomBean;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_manager);
//        ButterKnife.bind(this);
//        courseBeans = new ArrayList<>();
//
//        initData();
//
//        handler = new Handler();
//
//        initViewOne();
//        initViewTwo();


    }

//    private void initViewTwo() {
//
//        rcvFragmentExamSubjectTwo.setLayoutManager(new GridLayoutManager(2, GridLayoutManager.VERTICAL));
////        设置适配器，封装后的适配器只需要实现一个函数
//        rcvFragmentExamSubjectTwo.setAdapter(new SimpleAdapter<ExerciseTopBean.DataBean.CourseList>(this
//                , (ArrayList<ExerciseTopBean.DataBean.CourseList>) exerciseTopBean.getData().getList(), R.layout.item_exam_bottom) {
//            @Override
//            protected void onBindViewHolder(ViewHolder holder, ExerciseTopBean.DataBean.CourseList data) {
//                TextView title = holder.getView(R.id.tv_item_exam_bottom);
//                ImageView content = holder.getView(R.id.iv_item_exam_bottom);
//                title.setText(data.getPost_title());
//                content.setBackgroundResource(R.drawable.shilipic);
//            }
//
//
//        });
//        rcvFragmentExamSubjectTwo.setRefreshEnable(false);
////        设置刷新监听
////        rcvFragmentExamSubjectTwo.setOnRefreshListener(new PullToRefreshRecyclerView.OnRefreshListener() {
////            @Override
////            public void onStartRefreshing() {
////                handler.postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        List<ExerciseBottomBean.CourseDataBean.CourseBean> courseBeanss = new ArrayList<>();
////                        for (int i = 1; i < 11; i++) {
////                            ExerciseBottomBean.CourseDataBean.CourseBean courseBean = new ExerciseBottomBean.CourseDataBean.CourseBean();
////                            courseBean.setId(i);
////                            courseBean.setPost_content("刷新  练车第一步：" + i);
////                            courseBean.setPost_title("练车  " + i);
////                            courseBean.setUpdate_time(100000000+i);
////                            courseBeanss.add(courseBean);
////                        }
////                        imgs.clear(); // 清除之前的数据
////                        courseBeans.addAll(courseBeanss);
////                        rcvFragmentExamSubjectTwo.completeRefresh();
////                    }
////                }, 1000);
////            }
////        });
////        设置加载监听
//        rcvFragmentExamSubjectTwo.setOnLoadListener(new OnLoadListener() {
//            @Override
//            public void onStartLoading(int skip) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        rcvFragmentExamSubjectTwo.completeLoad(initDataBottom().getData().getList().size());
//                    }
//                }, 1000);
//            }
//        });
////        设置分割线
//        rcvFragmentExamSubjectTwo.addItemDecoration(new BaseDecoration(this, R.color.colorAccent));
//
//        // 点击事件
//        rcvFragmentExamSubjectTwo.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void OnItemClick(int position) {
//                Toast.makeText(GridManagerActivity.this, "item" + position + " has been clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        // 长按事件
////        rcvFragmentExamSubjectTwo.setOnItemLongClickListener(new HAFAdapter.OnItemLongClickListener() {
////            @Override
////            public boolean onItemLongClick(int position) {
////                Toast.makeText(GridManagerActivity.this, "item" + position + " has been long clicked", Toast.LENGTH_SHORT).show();
////                return true;
////            }
////        });
//    }
//
//    private void initViewOne() {
//
//        rcvFragmentExamSubjectOne.setLayoutManager(new GridLayoutManager(4, GridLayoutManager.VERTICAL));
////        设置适配器，封装后的适配器只需要实现一个函数
//        rcvFragmentExamSubjectOne.setAdapter(new SimpleAdapter<ExerciseTopBean.DataBean.CourseList>(this
//                , (ArrayList<ExerciseTopBean.DataBean.CourseList>) exerciseTopBean.getData().getList(), R.layout.item_exam_top) {
//
//
//            @Override
//            protected void onBindViewHolder(ViewHolder holder, ExerciseTopBean.DataBean.CourseList data) {
//                TextView title = holder.getView(R.id.tv_item_exam_top);
//                title.setText(data.getPost_title());
//                ImageView content = holder.getView(R.id.iv_item_exam_top);
//                content.setBackgroundResource(R.drawable.shilipic);
//
//            }
//        });
////        设置刷新监听
//        rcvFragmentExamSubjectOne.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onStartRefreshing() {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        initData();
//                        rcvFragmentExamSubjectOne.completeRefresh();
//                    }
//                }, 1000);
//            }
//        });
////        设置加载监听
////        rcvFragmentExamSubjectOne.setOnLoadListener(new OnLoadListener() {
////            @Override
////            public void onStartLoading(int skip) {
////                handler.postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        List<ExerciseBottomBean.CourseDataBean.CourseBean> courseBeansss = new ArrayList<>();
////                        for (int i = 21; i < 31; i++) {
////                            ExerciseBottomBean.CourseDataBean.CourseBean courseBean = new ExerciseBottomBean.CourseDataBean.CourseBean();
////                            courseBean.setId(i);
////                            courseBean.setPost_content("加载  练车第一步：" + i);
////                            courseBean.setPost_title("练车  " + i);
////                            courseBean.setUpdate_time(100000000 + i);
////                            courseBeansss.add(courseBean);
////                        }
////                        courseBeans.addAll(courseBeansss);
////                        rcvFragmentExamSubjectOne.completeLoad(courseBeansss.size());
////                    }
////                }, 1000);
////            }
////        });
//
////        设置分割线
//        rcvFragmentExamSubjectOne.addItemDecoration(new BaseDecoration(this, R.color.colorAccent));
//
//        // 点击事件
//        rcvFragmentExamSubjectOne.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void OnItemClick(int position) {
//                Toast.makeText(GridManagerActivity.this, "item" + position + " has been clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        // 长按事件
////        rcvFragmentExamSubjectOne.setOnItemLongClickListener(new HAFAdapter.OnItemLongClickListener() {
////            @Override
////            public boolean onItemLongClick(int position) {
////                Toast.makeText(GridManagerActivity.this, "item" + position + " has been long clicked", Toast.LENGTH_SHORT).show();
////                return true;
////            }
////        });
//    }
//
//    String url = "https://yun.xiaojiangjiakao.com/api/portal/lists/getCategoryPostLists";
//    private ExerciseTopBean initData() {
//
//        map.clear();
//        map.put("category_id", "7");
//        map.put("field", "post.id,post.post_title,post.more");
//        map.put("limit", "4");
//        map.put("is_top", "1");
//        map.put("order", "-is_top");
//        OkHttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
//
//
//            @Override
//            public void onFailure(Call call, Exception e) {
//                LogUtils.e(" ----------------- ");
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                exerciseTopBean = gson.fromJson(response, ExerciseTopBean.class);
//                initDataBottom();
//            }
//        });
//        return exerciseTopBean;
//
//    }
//
//    private ExerciseBottomBean initDataBottom() {
//
//        map.clear();
//        map.put("category_id", "7");
//        map.put("field", "post.id,post.post_title,post.more");
//        map.put("page", "1,6");
//        map.put("order", "-id");
//        map.put("my_where", "is_top,neq,1");
//        OkHttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
//            @Override
//            public void onFailure(Call call, Exception e) {
//                LogUtils.e("sdadasda");
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                exerciseBottomBean = gson.fromJson(response, ExerciseBottomBean.class);
//
//            }
//        });
//        return exerciseBottomBean;
//    }

}
