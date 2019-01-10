package com.fpp.status.base;

/**
 * 作者：杨庆伟
 * 时间：2016/8/15 18:48
 * 邮箱：379734891@qq.com
 * 详情： MVP   view层
 *
 */
public interface BaseView<T> {
    /**
     * @param basePresenter  获取到P层的引用
     */
    void setPresenter(T basePresenter);
}
