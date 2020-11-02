package com.fpp.status.view.pulpdr.DefaultHeaderAndFooterCreator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.view.pulpdr.AutoLoad.ALFooterCreator;

import androidx.recyclerview.widget.RecyclerView;


/**
 * 默认自动加载头部
 */
public class DALFooterCreator extends ALFooterCreator {

    protected View mAutoLoadFooter;
    protected ImageView iv;
    protected ValueAnimator ivAnim;
    private int loadingDuration = 1000;
    protected View mNoMoreView;

    @Override
    protected View getLoadView(Context context, RecyclerView recyclerView) {
        if (mAutoLoadFooter == null) {
            mAutoLoadFooter = LayoutInflater.from(context).inflate(R.layout.layout_auto_load_footer,recyclerView,false);
            iv = (ImageView) mAutoLoadFooter.findViewById(R.id.iv);
            startLoadingAnim();
        }
        return mAutoLoadFooter;
    }

    @Override
    protected View getNoMoreView(Context context, RecyclerView recyclerView) {
        if (mNoMoreView == null) {
            mNoMoreView = LayoutInflater.from(context).inflate(R.layout.layout_auto_load_footer, recyclerView, false);
            mNoMoreView.findViewById(R.id.iv).setVisibility(View.GONE);
            ((TextView)mNoMoreView.findViewById(R.id.tv)).setText("没有更多了哦");
        }
        return mNoMoreView;
    }

    private void startLoadingAnim() {
        if (ivAnim != null) {
            ivAnim.cancel();
        }
        ivAnim = ObjectAnimator.ofFloat(0, 360).setDuration(loadingDuration);
        ivAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                iv.setRotation((Float) animation.getAnimatedValue());
            }
        });
        ivAnim.setRepeatMode(ObjectAnimator.RESTART);
        ivAnim.setRepeatCount(ObjectAnimator.INFINITE);
        ivAnim.setInterpolator(new LinearInterpolator());
        ivAnim.start();
    }

    //注销监听，防止内存泄露
    public void cancelListener() {
        ivAnim.removeAllUpdateListeners();
        ivAnim.cancel();
    }

}
