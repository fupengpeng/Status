package com.fpp.status.model.impl;

import com.fpp.status.model.RequestListener;
import com.fpp.status.model.interf.ILoginModel;
import com.fpp.status.okhttp.CallBackUtil;
import com.fpp.status.okhttp.OkHttpUtil;

import java.util.HashMap;

import okhttp3.Call;

/**
 * Description:
 * Author: fpp
 * Date: 2018/5/15  15:24
 */

public class LoginModel implements ILoginModel {

    @Override
    public void login(String name, String password, final RequestListener<String> requestListener) {
        String url = "https://www.baidu.com/";
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("name",name);
        paramsMap.put("password",password);
        OkHttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                requestListener.fail(e);
            }

            @Override
            public void onResponse(String response) {
                requestListener.success(response);
            }
        });
    }
}
