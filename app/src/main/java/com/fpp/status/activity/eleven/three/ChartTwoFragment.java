package com.fpp.status.activity.eleven.three;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.activity.eleven.three.data.CompositeIndexBean;
import com.fpp.status.activity.eleven.three.data.IncomeBean;
import com.fpp.status.activity.eleven.three.data.LineChartBean;
import com.fpp.status.base.BaseFragment;
import com.fpp.status.utils.LocalJsonAnalyzeUtil;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description:  图表二
 * Author: fpp
 * Date: 2018/9/19  9:41
 */

public class ChartTwoFragment extends BaseFragment {



    @BindView(R.id.lineChart)
    LineChart lineChart1;
    @BindView(R.id.view_shanghai)
    TextView view_shanghai;
    @BindView(R.id.cl_shanghai)
    ConstraintLayout cl_shanghai;
    @BindView(R.id.view_shenzhen)
    View view_shenzhen;
    @BindView(R.id.cl_shenzhen)
    ConstraintLayout cl_shenzhen;
    @BindView(R.id.view_gem)
    View view_gem;
    @BindView(R.id.cl_gem)
    ConstraintLayout cl_gem;


    Unbinder unbinder;
    private LineChartBean lineChartBean;
    private List<IncomeBean> incomeBeanList;//个人收益
    private List<CompositeIndexBean> shanghai;//沪市指数
    private List<CompositeIndexBean> shenzheng;//深市指数
    private List<CompositeIndexBean> GEM;//创业板指数

    private LineChartManager lineChartManager1;


    public static ChartTwoFragment newInstance(int subject) {
        Bundle args = new Bundle();
        ChartTwoFragment fragment = new ChartTwoFragment();
        args.putSerializable("args", subject);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getViewResourceId() {
        return R.layout.fragment_chart_two;
    }

    @Override
    protected void initView() {


    }


    @Override
    protected void initData() {
        //获取数据
        lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(getContext(), "line_chart.json", LineChartBean.class);
        incomeBeanList = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();

        shanghai = lineChartBean.getGRID0().getResult().getCompositeIndexShanghai();
        shenzheng = lineChartBean.getGRID0().getResult().getCompositeIndexShenzhen();
        GEM = lineChartBean.getGRID0().getResult().getCompositeIndexGEM();


        lineChartManager1 = new LineChartManager(lineChart1);

        //展示图表
        lineChartManager1.showLineChart(incomeBeanList, "我的收益", getResources().getColor(R.color.blue));
        lineChartManager1.addLine(shanghai, "上证指数", getResources().getColor(R.color.orange));

        //设置曲线填充色 以及 MarkerView
        Drawable drawable = getResources().getDrawable(R.drawable.fade_blue);
        lineChartManager1.setChartFillDrawable(drawable);
        lineChartManager1.setMarkerView(getActivity());



    }


    @OnClick({R.id.cl_shanghai, R.id.cl_shenzhen, R.id.cl_gem})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_shanghai:
                view_shanghai.setBackground(getResources().getDrawable(R.drawable.shape_round_orange));

                view_gem.setBackground(getResources().getDrawable(R.drawable.shape_round_gray));
                view_shenzhen.setBackground(getResources().getDrawable(R.drawable.shape_round_gray));

                lineChartManager1.resetLine(1, shanghai, "上证指数", getResources().getColor(R.color.orange));
                break;
            case R.id.cl_shenzhen:
                view_shenzhen.setBackground(getResources().getDrawable(R.drawable.shape_round_orange));

                view_gem.setBackground(getResources().getDrawable(R.drawable.shape_round_gray));
                view_shanghai.setBackground(getResources().getDrawable(R.drawable.shape_round_gray));

                lineChartManager1.resetLine(1, shenzheng, "深证指数", getResources().getColor(R.color.orange));
                break;
            case R.id.cl_gem:
                view_gem.setBackground(getResources().getDrawable(R.drawable.shape_round_orange));
                view_shanghai.setBackground(getResources().getDrawable(R.drawable.shape_round_gray));
                view_shenzhen.setBackground(getResources().getDrawable(R.drawable.shape_round_gray));

                lineChartManager1.resetLine(1, GEM, "创业指数",
                        getResources().getColor(R.color.orange));
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
