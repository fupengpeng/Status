package com.fpp.status.activity.eleven.three;

import android.graphics.Color;
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
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

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

public class ChartThreeFragment extends BaseFragment {



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

    private LineChartUtil lineChartManager1;


    public static ChartThreeFragment newInstance(int subject) {
        Bundle args = new Bundle();
        ChartThreeFragment fragment = new ChartThreeFragment();
        args.putSerializable("args", subject);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getViewResourceId() {
        return R.layout.fragment_chart_three;
    }

    @Override
    protected void initView() {


    }


    @Override
    protected void initData() {
        //获取数据
        lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(getContext(), "client_chart.json", LineChartBean.class);
        incomeBeanList = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();

        shanghai = lineChartBean.getGRID0().getResult().getCompositeIndexShanghai();
        shenzheng = lineChartBean.getGRID0().getResult().getCompositeIndexShenzhen();
        GEM = lineChartBean.getGRID0().getResult().getCompositeIndexGEM();

//        lineChartSetting(    lineChart1);


        lineChartManager1 = new LineChartUtil(lineChart1);

        //展示图表
        lineChartManager1.showLineChart(incomeBeanList, "我的收益", getResources().getColor(R.color.blue));
//        lineChartManager1.addLine(shanghai, "上证指数", getResources().getColor(R.color.orange));

        //设置曲线填充色 以及 MarkerView
        Drawable drawable = getResources().getDrawable(R.drawable.fade_blue);
        lineChartManager1.setChartFillDrawable(drawable);
        lineChartManager1.setMarkerView(getActivity());



    }

    private void lineChartSetting(LineChart lineChart) {

                /*
        //创建描述信息
Description description =new Description();
        description.setText("测试图表");
        description.setTextColor(Color.RED);
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("没有数据熬");//没有数据时显示的文字
        lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        //lineChart.setBorderColor(); //设置 chart 边框线的颜色。
        //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChart.setLogEnabled(true);//打印日志
        //lineChart.notifyDataSetChanged();//刷新数据
        //lineChart.invalidate();//重绘




             //获取此图表的x轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        //xAxis.setTextSize(20f);//设置字体
        //xAxis.setTextColor(Color.BLACK);//设置字体颜色
        //设置竖线的显示样式为虚线
        //lineLength控制虚线段的长度
        //spaceLength控制线之间的空间
        xAxis.enableGridDashedLine(10f, 10f, 0f);
//        xAxis.setAxisMinimum(0f);//设置x轴的最小值
//        xAxis.setAxisMaximum(10f);//设置最大值
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
//        设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
//        xAxis.setLabelCount(10);
//        xAxis.setTextColor(Color.BLUE);//设置轴标签的颜色
//        xAxis.setTextSize(24f);//设置轴标签的大小
//        xAxis.setGridLineWidth(10f);//设置竖线大小
//        xAxis.setGridColor(Color.RED);//设置竖线颜色
//        xAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        xAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        xAxis.setValueFormatter();//格式化x轴标签显示字符


        // Y轴默认显示左右两个轴线

        //获取右边的轴线
        YAxis rightAxis=lineChart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = lineChart.getAxisLeft();
        //设置网格线为虚线效果
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);


         */





         XAxis xAxis;                //X轴
         YAxis leftYAxis;            //左侧Y轴
         YAxis rightYAxis;           //右侧Y轴 自定义XY轴值
         Legend legend;              //图例
         LimitLine limitLine;        //限制线

        leftYAxis = lineChart.getAxisLeft();
        rightYAxis = lineChart.getAxisRight();
        xAxis = lineChart.getXAxis();

        /***图表设置***/


        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        lineChart.setBackgroundColor(Color.WHITE);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
//        lineChart.setDragEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);

        //是否有触摸事件
//        lineChart.setTouchEnabled(true);

        //设置XY轴动画效果
//        lineChart.animateY(500);
//        lineChart.animateX(500);
        Description description = new Description();
//        description.setText("需要展示的内容");
        description.setEnabled(false);
        lineChart.setDescription(description);


        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYAxis = lineChart.getAxisRight();

        xAxis.setDrawGridLines(false);
        rightYAxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(true);
        //设置Y轴网格线为虚线
        leftYAxis.enableGridDashedLine(10f, 10f, 0f);
        rightYAxis.setEnabled(false);

        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYAxis.setAxisMinimum(0f);

        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        //是否显示
        legend.setEnabled(false);

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
