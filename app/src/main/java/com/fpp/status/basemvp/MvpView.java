package com.fpp.status.basemvp;


import java.util.List;

public interface MvpView extends BaseView{
     void setListItem(List<String> data);
     void showMessage(String message);
}
