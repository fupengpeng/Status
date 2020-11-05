package com.fpp.status.activity.two.customview;

import androidx.recyclerview.widget.ItemTouchHelper;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/2/7 0007 9:46
 */

public class YolandaItemTouchHelper extends ItemTouchHelper {
    Callback callback;
    public YolandaItemTouchHelper(Callback callback) {
        super(callback);
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }
}
