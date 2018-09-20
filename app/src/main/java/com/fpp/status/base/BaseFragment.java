package com.fpp.status.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.fpp.status.utils.LogUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 *
 * 详情: fragment基类
 */
public abstract class BaseFragment extends Fragment  {
    protected static final String ARG_PARAM1 = "param1";
    protected static final String ARG_PARAM2 = "param2";
    protected static final String ARG_PARAM3 = "param3";
    /**
     * 临时使用的集合
     */
    protected Map map = new HashMap();
    /**
     * 父类维护context
     */
    protected Context context;
    protected CompositeSubscription s;
//    protected UserInfo userInfo;
    private Serializable serializable;
    private Unbinder unbinder;

    public BaseFragment() {
        // 默认空的构造
    }

    @Override
    public void onAttach(Context context) {
        LogUtil.d(getClassName() +"   onAttach   嵌入到activity中了 ");
        this.context = context;
        super.onAttach(context);
    }

//    /**
//     * 生成fragment对象
//     * @param aclass
//     * @param s   Serializable 对象（）
//     * @param <T>   具体的子类的类型
//     * @return
//     */
//    public static <T extends BaseFragment> T newInstance(Class<T> aclass, Serializable s) {
//        try {
//            T fragment = aclass.newInstance();
////            RegistFragment fragment = new BaseFragment();
//            if (s != null) {
//                Bundle args = new Bundle();
//                args.putSerializable(ARG_PARAM1, s);
////            args.putString(ARG_PARAM2, param2);
//                fragment.setArguments(args);
//            }
//            return fragment;
//        } catch (java.lang.InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static <T extends BaseFragment> T newInstance(Class<T> aclass) {
//        return newInstance(aclass, null);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtil.d(getClassName() +"   onCreate   fragment对象创建了 ");

        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            serializable = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        s = new CompositeSubscription();
//        userInfo= RoleUitl.getInstance().getUserInfo();
    }

    private String getClassName() {
        return this.getClass().getSimpleName();
    }

    // 初始view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(getClassName() +"   onCreateView  view创建了 ");

        View view = inflater.inflate(getViewResourceId(), null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getViewResourceId();

    /* 加载数据*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d(getClassName() +"   onActivityCreated  嵌入的activity创建好了  fragment初始化好了 ");
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();


    @Override
    public boolean getUserVisibleHint() {

        return super.getUserVisibleHint();
    }

    @Override
    public void onResume() {
//        LogUtil.d(this.getClass().getName()+"   onResume:  可见了");
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        LogUtil.d(getClassName() +"   onDestroyView   view销毁了 ");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        LogUtil.d(getClassName() +"   onDetach   从activity中分开了");
        super.onDetach();
    }
    @Override
    public void onDestroy() {
        LogUtil.d(getClassName() +"   onDestroy   fragment 销毁了");
        super.onDestroy();
        // 解绑
        unbinder.unbind();
        // 事件传递
        if (s!=null) {
            s.clear();
        }
    }
    public void setTitle(CharSequence title) {
        if (getActivity()!=null) {
            getActivity().setTitle(title);
        }
    }
}
