package com.fpp.status.view.pulpdr.LayoutManager;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;


/**
 * Created by Administrator on 2016/11/2.
 */
public class LinearLayoutManager extends StaggeredGridLayoutManager {


    public LinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setGapStrategy(GAP_HANDLING_NONE);
    }

    private LinearLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public LinearLayoutManager() {
        super(1,VERTICAL);
        setGapStrategy(GAP_HANDLING_NONE);
    }

    public LinearLayoutManager(int orientation) {
        super(1,orientation);
        setGapStrategy(GAP_HANDLING_NONE);
    }

    public LinearLayoutManager(int orientation, boolean reverseLayout) {
        super(1,orientation);
        setReverseLayout(reverseLayout);
        setGapStrategy(GAP_HANDLING_NONE );
    }



}
