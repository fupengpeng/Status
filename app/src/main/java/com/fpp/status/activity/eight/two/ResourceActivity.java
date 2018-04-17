package com.fpp.status.activity.eight.two;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fpp.status.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/17 0017 11:06
 */

public class ResourceActivity extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.iv_anim)
    ImageView ivAnim;
    @BindView(R.id.btn_anim)
    Button btnAnim;
    @BindView(R.id.ll_anim)
    LinearLayout llAnim;
    private ClipDrawable drawable;
    private Animation animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);
        ButterKnife.bind(this);
        ImageView imageview = (ImageView) findViewById(R.id.image_view);
        // 获取图片所要显示的ClipDrawable对象
        drawable = (ClipDrawable) imageview.getDrawable();


        initView();
        initViewAnim();
        initViewPropertyAnim();
        initViewXml();


    }

    private void initViewXml() {
        // 根据 XML 资源的ID获取解析该资源的解析器
        // XmlResoureParser是XmlPullParser的子类
        XmlResourceParser xrp = getResources().getXml(R.xml.books);

        StringBuilder sb = new StringBuilder("");
        // 还没有到xml文档的结尾处
        try {
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
                // 如果遇到开始标签
                if (xrp.getEventType() == XmlResourceParser.START_DOCUMENT){
                    // 获取该标签的标签名
                    String tagName = xrp.getName();
                    // 如果遇到book标签
                    if (tagName.equals("book")){
                        // 根据属性名来获取属性值
                        String bookName = xrp.getAttributeValue(null,"price");
                        sb.append("价格：" );
                        sb.append(bookName);
                        // 根据属性索引来获取属性值
                        String bookPrice = xrp.getAttributeValue(1);
                        sb.append("    出版日期：");
                        sb.append(bookPrice);
                        sb.append("    书名：");
                        // 获取文本节点的值
                        sb.append(xrp.nextText());

                    }
                    sb.append("\n");

                }
                // 获取解析器的下一个事件
                xrp.next();

            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initViewPropertyAnim() {
        llAnim.addView(new MyAnimationView(this));
    }

    private void initViewAnim() {
        // 加载动画资源
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_drawable_example);
        // 设置动画结束后保留结束状态
        animation.setFillAfter(true);

    }

    private void initView() {


        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 如果消息是本程序所发出的的
                if (msg.what == 1122) {
                    // 修改ClipDrawable的level值
                    if (drawable != null) {
                        drawable.setLevel(drawable.getLevel() + 200);
                    }

                }
            }
        };
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1122;
                // 发送消息，通知修改ClipDrawable对象的level值
                handler.sendMessage(msg);
                if (drawable != null) {
                    if (drawable.getLevel() >= 1000) {
                        timer.cancel();
                    }
                }

            }
        }, 0, 300);

    }

    @OnClick({R.id.image_view, R.id.iv_anim, R.id.btn_anim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_view:
                break;
            case R.id.iv_anim:
                break;
            case R.id.btn_anim:
                // 开始动画
                ivAnim.startAnimation(animation);
                break;
        }
    }

    private class MyAnimationView extends View {
        public MyAnimationView(Context context){
            super(context);
            // 加载动画资源
            ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(
                    ResourceActivity.this,R.animator.property_animation_example);
            objectAnimator.setEvaluator(new ArgbEvaluator());
            // 对该View本身应用属性动画
            objectAnimator.setTarget(this);
            // 开始指定动画
            objectAnimator.start();

        }
    }
}
