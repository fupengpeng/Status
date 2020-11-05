package com.fpp.status.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import retrofit2.http.HEAD;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.util.Random;

/**
<<<<<<< HEAD
 * @Description
 * @Author fpp
 * @Date 2020/8/14 0014 16:31
 */
public class UIUtil {

//    private static Toast mToast = SuperApplication.mToast;
    private static Context context = null;
    private static Toast mToast = null;

    /**
     * 非空校验
     *
     * @param o o
     * @return true
     */
    public static boolean isEmpty(Object o) {
        if (null == o) return true;
        if (o instanceof TextView) {
            return TextUtils.isEmpty(((TextView) o).getText().toString().trim());
        } else if (o instanceof Spinner) {
            SpinnerAdapter adapter = ((Spinner) o).getAdapter();
            return adapter == null || adapter.isEmpty() || TextUtils.isEmpty(((Spinner) o).getSelectedItem().toString().trim());
        } else if (o instanceof CharSequence) {
            return TextUtils.isEmpty((CharSequence) o);
        }
        return true;
    }

    /**
     * dp值转化成px（纯代码中使用）
     *
     * @param dipValue dp
     * @return int
     */
    static int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 网络是否连接
     *
     * @param context context
     * @return true
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert conn != null;
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    /**
     * 校验手机号
     *
     * @param mobiles 手机号
     * @return true
     */
    public static boolean isMobileNum(String mobiles) {
        /*
        06.    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        07.    联通：130、131、132、152、155、156、185、186
        08.    电信：133、153、180、189、（1349卫通）
        09.    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        10.    */
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = ("^[1][3,4,5,7,8,9][0-9]{9}$");
        if (TextUtils.isEmpty(mobiles)) {
            toast("手机号码不能为空");
            return false;
        } else {
            if (mobiles.length() != 11) {
                toast("手机号码位数不够");
                return false;
            } else {
                if (!mobiles.matches(telRegex)) {
                    toast("请输入有效手机号");
                    return false;
                }
                return true;
            }
        }
    }

    /**
     * 获取手机IMEI  需要READ_PHONE_STATE 权限
     *
     * @return str
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI() {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取隐藏姓名和手机号
     *
     * @param s w
     * @return str
     */
    public static String getHideName(String s) {
        String replaceAll = null;
        if (s.length() > 10) {
            // 电话号码
            if (s.startsWith("+86")) {
                s = s.substring(3, s.length());
                replaceAll = s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            } else {
                replaceAll = s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            }
        } else {
            // 姓名
            if (s.length() == 2) {
                replaceAll = s.substring(0, 1) + "*";
            } else if (s.length() > 2 && s.length() < 11) {
                replaceAll = s.substring(0, 1) + "**";
            }
        }
        return replaceAll;
    }

    /**
     * 吐司工具
     */
    public static void toast(String str) {
        mToast.setText(str);
        mToast.show();
    }

    /**
     * 填充布局
     */
    public static View inflate(int resId) {
        return View.inflate(context, resId, null);
    }

    public static <T extends View> T $(View view, int resId) {
        return (T) (view.findViewById(resId));
    }

    /**
     * 设置圆角图片
     *
     * @param bitmap    bitmap
     * @param imageView iv
     */
    public static void setRoundBitmap(Bitmap bitmap, ImageView imageView) {
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        drawable.setCornerRadius(dip2px(10));
        drawable.setAntiAlias(true);
        drawable.setGravity(Gravity.CENTER);
        drawable.setBounds(imageView.getLeft(), imageView.getTop(), imageView.getRight(), imageView.getBottom());
        imageView.setImageDrawable(drawable);
    }

    /**
     * 获取 textview   spinner  值
     *
     * @param o o
     * @return str
     */
    public static String getText(Object o) {
        if (o instanceof TextView) {
            return ((TextView) o).getText().toString().trim();
        } else if (o instanceof Spinner) {
            return ((Spinner) o).getSelectedItem().toString().trim();
        } else if (o instanceof EditText) {
            if (!isEmpty(o))
                return ((EditText) o).getText().toString().trim();
        }
        return "";
    }

    /**
     * 校验相机可用
     *
     * @return true
     */
    public static boolean hardwareSupportCheck() {
        // Camera needs to open
        Camera c = null;
        try {
            c = Camera.open();
            if (null == c) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != c) {
                c.release();
            }
        }
        return true;
    }


    public static void checkNull(String object, String noticeStr) {
        if (TextUtils.isEmpty(object)) {
            throw new InvalidParameterException(noticeStr);
        }
    }

    /**
     * 校验中国名字
     *
<<<<<<< HEAD
     * @param tv tv
     * @return true
=======
     * @return
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
     */
    public static boolean isRequiredName(TextView tv) {
        String input = getText(tv);
        String rex1 = "•";
        String rex2 = "·";
        if (input.startsWith(rex1) || input.startsWith(rex2)) {
            toast("姓名首位不能为'·'");
            return false;
        }
        if (input.length() > 10) {
            toast("姓名最长不超过10个汉字");
            return false;
        }
        if (input.contains(rex1) || input.contains(rex2)) {
            input = input.replaceAll(rex1, "").replaceAll(rex2, "");
        }
        if (!input.matches("^[\u4e00-\u9fff]{1,18}$")) {
            toast("姓名中不能包含字母、数字和特殊符号");
            return false;
        }
        return true;
    }

    /**
     * 替换fragment
     *
<<<<<<< HEAD
     * @param manager  manager
     * @param layoutId layoutId
     * @param fragment fragment
=======
     * @param manager
     * @param layoutId
     * @param fragment
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
     */
    public static void replace(FragmentManager manager, int layoutId, Fragment fragment) {
        manager.beginTransaction().replace(layoutId, fragment).commitAllowingStateLoss();
    }

    public static void hideSoftInput(Activity context) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = context.getCurrentFocus();
        if (im.isActive()) {
            //如果开启
//                    im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            //关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
            try {
                boolean ok = im.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
//                if (im.isActive()) {
//                    im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                }
            }
        }
    }

    /**
     * 获取随机颜色
     *
     * @return
     */
    public static int getRandomColor() {
        return Color.rgb(getInt(), getInt(), getInt());
    }

    private static int getInt() {
        return new Random().nextInt(200) + 20;
    }

    public static void init(Context mContext, Toast mToast) {
        context = mContext;
        UIUtil.mToast = mToast;
    }

    /**
     * 开启另一个界面
     *
     * @param aClass
     */
    public static void start(Context activity, Class aClass) {
        Intent intent = new Intent(context, aClass);
        activity.startActivity(intent);
    }

    public static Context getContext() {
        return context;
    }

    public static Double getDouble(Object o) {

        DecimalFormat df = new DecimalFormat("0.00");

        String format = df.format(Double.parseDouble(o + "") * 100);

        return Double.parseDouble(format);
    }
}
