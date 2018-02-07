package com.fpp.status.activity.two.customview;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/2/7 0007 9:14
 */

public class DefaultItemTouchHelper extends YolandaItemTouchHelper
//        ItemTouchHelper
{


//    /**
//     * Creates an ItemTouchHelper that will work with the given Callback.
//     * <p>
//     * You can attach ItemTouchHelper to a RecyclerView via
//     * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
//     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
//     *
//     * @param callback The Callback which controls the behavior of this touch helper.
//     */
//    public DefaultItemTouchHelper(Callback callback) {
//        super(callback);
//    }

    private DefaultItemTouchHelpCallback itemTouchHelpCallback;

    public DefaultItemTouchHelper(DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener) {
        super(new DefaultItemTouchHelpCallback(onItemTouchCallbackListener));
        itemTouchHelpCallback = (DefaultItemTouchHelpCallback) getCallback();
    }

    /**
     * 设置是否可以被拖拽
     *
     * @param canDrag 是true，否false
     */
    public void setDragEnable(boolean canDrag) {
        itemTouchHelpCallback.setDragEnable(canDrag);
    }

    /**
     * 设置是否可以被滑动
     *
     * @param canSwipe 是true，否false
     */
    public void setSwipeEnable(boolean canSwipe) {
        itemTouchHelpCallback.setSwipeEnable(canSwipe);
    }

}
