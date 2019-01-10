package com.fpp.status.view.pulpdr.LayoutManager;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/11/2.
 */
public class GridLayoutManager extends StaggeredGridLayoutManager {

    public GridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setGapStrategy(GAP_HANDLING_NONE );
    }

    public GridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public GridLayoutManager(int spanCount) {
        super(spanCount,VERTICAL);
        setGapStrategy(GAP_HANDLING_NONE);
    }

    public GridLayoutManager(int spanCount, int orientation,
                             boolean reverseLayout) {
        super(spanCount, orientation);
        setReverseLayout(reverseLayout);
        setGapStrategy(GAP_HANDLING_NONE );
    }

}
