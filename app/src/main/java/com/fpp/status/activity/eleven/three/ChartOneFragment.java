package com.fpp.status.activity.eleven.three;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpp.status.R;
import com.fpp.status.utils.LocalJsonAnalyzeUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:  图表一
 * Author: fpp
 * Date: 2018/9/19  9:41
 */

public class ChartOneFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.lc_fragment_chart_one)
    LineChart lcFragmentChartOne;
    @BindView(R.id.spread_line_chart)
    LineChart lineChart1;
    @BindView(R.id.line_chart)
    LineChart lineChart2;

    private BarData data;
    private BarDataSet dataSet;
    private Random random;

    public static ChartOneFragment newInstance(int subject) {
        Bundle args = new Bundle();

        ChartOneFragment fragment = new ChartOneFragment();
        args.putSerializable("args", subject);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart_one, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {


        initChart1();
        initChart2();


    }


    private void initChart2() {


    }

    private void initChart1() {
        initChart(lineChart1);
        LineChartBean lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(getContext(),
                "chart.json", LineChartBean.class);

        List<IncomeBean> list = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();
        showLineChart(list, "我的收益", Color.CYAN);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }





    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private LimitLine limitLine;        //限制线
//  private MyMarkerView markerView;    //标记视图 即点击xy轴交点时弹出展示信息的View 需自定义


    /**
     * 初始化图表
     */
    private void initChart(LineChart lineChart) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);

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
    }


    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }


    /**
     * 展示曲线
     *
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart(List<IncomeBean> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            IncomeBean data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, (float) data.getValue());
            entries.add(entry);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart1.setData(lineData);
    }


    /**
     * 我的收益
     */
    public class IncomeBean {
        /**
         * tradeDate : 20180502
         * value : 0.03676598
         */
        private String tradeDate;
        private double value;

        public String getTradeDate() {
            return tradeDate;
        }

        public void setTradeDate(String tradeDate) {
            this.tradeDate = tradeDate;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
    /**
     * 沪深创指数
     */
    public class CompositeIndexBean {
        /**
         * rate : -0.00034196
         * tradeDate : 20180502
         */
        private String rate;
        private String tradeDate;

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getTradeDate() {
            return tradeDate;
        }

        public void setTradeDate(String tradeDate) {
            this.tradeDate = tradeDate;
        }
    }

    public class LineChartBean {

        private int ERRORNO;
        private Grido GRID0;

        public int getERRORNO() {
            return ERRORNO;
        }

        public void setERRORNO(int ERRORNO) {
            this.ERRORNO = ERRORNO;
        }

        public Grido getGRID0() {
            return GRID0;
        }

        public void setGRID0(Grido GRID0) {
            this.GRID0 = GRID0;
        }

        public class Grido {
            private int code;
            private String message;
            private Result result;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public Result getResult() {
                return result;
            }

            public void setResult(Result result) {
                this.result = result;
            }

            public class Result {

                private List<CompositeIndexBean> compositeIndexGEM;
                private List<IncomeBean> clientAccumulativeRate;
                private List<CompositeIndexBean> compositeIndexShanghai;
                private List<CompositeIndexBean> compositeIndexShenzhen;

                public List<CompositeIndexBean> getCompositeIndexGEM() {
                    return compositeIndexGEM;
                }

                public void setCompositeIndexGEM(List<CompositeIndexBean> compositeIndexGEM) {
                    this.compositeIndexGEM = compositeIndexGEM;
                }

                public List<IncomeBean> getClientAccumulativeRate() {
                    return clientAccumulativeRate;
                }

                public void setClientAccumulativeRate(List<IncomeBean> clientAccumulativeRate) {
                    this.clientAccumulativeRate = clientAccumulativeRate;
                }

                public List<CompositeIndexBean> getCompositeIndexShanghai() {
                    return compositeIndexShanghai;
                }

                public void setCompositeIndexShanghai(List<CompositeIndexBean> compositeIndexShanghai) {
                    this.compositeIndexShanghai = compositeIndexShanghai;
                }

                public List<CompositeIndexBean> getCompositeIndexShenzhen() {
                    return compositeIndexShenzhen;
                }

                public void setCompositeIndexShenzhen(List<CompositeIndexBean> compositeIndexShenzhen) {
                    this.compositeIndexShenzhen = compositeIndexShenzhen;
                }




            }

        }

    }
}
