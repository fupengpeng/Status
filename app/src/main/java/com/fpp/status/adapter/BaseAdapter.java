package com.fpp.status.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的BaseAdapter，用于编写所有Adpater中重复的代码
 *
 * @author zss
 *
 * @param <T>
 *            需要显示的数据的类型
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

	/**
	 * Context
	 */
	private Context context;
	/**
	 * 数据源，即要显示在AdapterView中的数据的集合
	 */
	private List<T> data;
	/**
	 * 用于将XML加载为View对象的工具
	 */
	private LayoutInflater inflater;

	/**
	 * 构造方法
	 *
	 * @param context
	 *            Context对象，值不能为null
	 * @param data
	 *            数据源，即要显示在AdapterView中的数据的集合
	 */
	public BaseAdapter(Context context, List<T> data) {
		super();
		setContext(context);
		setData(data);
		setLayoutInflater();
	}

	@Override
	public int getCount() {
		// 返回要显示数据的数量
		return data.size();
	}

	/**
	 * 取得Context对象
	 *
	 * @return Context对象
	 */
	protected final Context getContext() {
		return context;
	}

	/**
	 * 设置Context对象
	 *
	 * @param context
	 *            Context对象，值不能为null，否则将抛出IllegalArgumentException
	 */
	private void setContext(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("参数Context不允许为null");
		}

		this.context = context;
	}

	/**
	 * 取得数据源
	 *
	 * @return 数据源，即要显示在AdapterView中的数据的集合
	 */
	protected List<T> getData() {
		return data;
	}

	/**
	 * 设置数据源
	 *
	 * @param data
	 *            数据源，即要显示在AdapterView中的数据的集合
	 */
	private void setData(List<T> data) {
		if (data == null) {
			this.data = new ArrayList<T>();
		} else {
			this.data = data;
		}
	}

	/**
	 * 取得LayoutInflater的对象
	 *
	 * @return LayoutInflater的对象
	 */
	protected final LayoutInflater getLayoutInflater() {
		return inflater;
	}

	/**
	 * 设置LayoutInflater属性的值，该方法必须在{@link #setContext(Context)}方法之后调用
	 */
	private void setLayoutInflater() {
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
