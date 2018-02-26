package com.fpp.status.activity.fragmentfive;


/**
 * Created by fupengpeng on 2017/6/29 0029.
 */

public class ShoppingCartFragmentEvent {


    private boolean isBatchModel;

    public ShoppingCartFragmentEvent(boolean isBatchModel) {

        this.isBatchModel = isBatchModel;
    }


    public ShoppingCartFragmentEvent(){}


    public boolean isBatchModel() {
        return isBatchModel;
    }

    public void setBatchModel(boolean isBatchModel) {
        this.isBatchModel = isBatchModel;
    }


}
