package com.fpp.status.view.pulpdr.LayoutManager;

import android.content.Context;
<<<<<<< HEAD
import android.util.AttributeSet;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

=======
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
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
