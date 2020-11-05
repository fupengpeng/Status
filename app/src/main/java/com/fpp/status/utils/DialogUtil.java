package com.fpp.status.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpp.status.R;

import java.lang.reflect.Field;

import androidx.annotation.Nullable;


/**
 * 作者：杨庆伟
 * 时间：2016/2/15 15:35
 * 邮箱：379734891@qq.com
 * 详情： dialog 帮助类(仅限简单的使用)
 */
public class DialogUtil {


    private static Dialog dialog;

    private static ExamDialogFragment dialogFragment;



    public static void showProgressbarDialog(Context context) {
        dialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCanceledOnTouchOutside(false);
    }

    public static void hideProgressbarDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (dialogFragment != null && dialogFragment.isVisible()) {
            dialogFragment.dismiss();
            dialogFragment.isRemoving();
            dialogFragment.dismissAllowingStateLoss();
        }
    }

    /**
     * 关闭弹出框
     */
    public static void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (dialogFragment != null && dialogFragment.isVisible()) {
            dialogFragment.dismiss();
            dialogFragment.isRemoving();
            dialogFragment.dismissAllowingStateLoss();
        }
    }


    public static void onDismiss(DialogInterface.OnDismissListener listener) {
        if (dialog != null) {
            dialog.setOnDismissListener(listener);
        }
    }


    /**
     * @param context
     * @param content     正文的内容
     * @param butStr      按钮文字
     * @param listener    点击事件按钮
     * @param orientation 按钮的布局方向   LinearLayout.VERTICAL  or LinearLayout.HORIZONTAL
     */
    public static void showBasicDialog(Activity context, String content,
                                       String[] butStr, View.OnClickListener[] listener,
                                       int orientation) {
         checkDialog(context);
        if (context != null) {
            LinearLayout layout = (LinearLayout) View.inflate(context, R.layout.dialog_basic, null);
            TextView tv = (TextView) layout.findViewById(R.id.tv);
            // 正文
            tv.setText(content);
            //  横向
            if (butStr.length == 2 && orientation == LinearLayout.HORIZONTAL) {
                LinearLayout buttonLayout = (LinearLayout) layout.findViewById(R.id.button_group);
                buttonLayout.setVisibility(View.VISIBLE);
                tv = (TextView) buttonLayout.getChildAt(0);
                tv.setText(butStr[0]);
                checkListener(tv, listener[0]);
                tv = (TextView) buttonLayout.getChildAt(buttonLayout.getChildCount() - 1);
                tv.setText(butStr[1]);
                checkListener(tv, listener[1]);
                // 竖向
            } else {
                for (int i = 0; i < butStr.length; i++) {
                    tv = (TextView) View.inflate(context, R.layout.dialog_button, null);
                    tv.setText(butStr[i]);
                    checkListener(tv, listener[i]);
                    layout.addView(tv);
                }
            }
            dialog.setContentView(layout);
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    private static void checkListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener == null ? new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        } : listener);
    }

    /**
     * 例如：  DialogUtil.showBasicDialog(getActivity(),"先预约",null);
     *
     * @param activity
     * @param content
     * @param listener
     */
    public static void showBasicDialog(Activity activity, String content, View.OnClickListener listener) {
        showBasicDialog(activity, content, new String[]{"确定"}, new View.OnClickListener[]{listener}, LinearLayout.VERTICAL);
    }

    public static void showDialogFromRight(Activity activity, View view, int width, int height) {
        checkDialog(activity);
        view.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setContentView(view);


        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.RIGHT | Gravity.BOTTOM);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.mypopwindow_anim_style);  //添加动画
//        window.getDecorView().setPadding(0,0,0,0);
        WindowManager.LayoutParams params = window.getAttributes();

//        params.width = context.getWindowManager().getDefaultDisplay().getWidth();
        params.width = UIUtil.dip2px(width);
//        params.height = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = activity.getWindowManager().getDefaultDisplay().getHeight()-MarginTop;
        params.height = height;
        params.horizontalMargin = 0;
        dialog.getWindow().setAttributes(params);
//        window.setWindowAnimations(R.style.dialogWindowAnimFromRight);  //添加动画
    }

    /**
     * 带“确定”“取消”按钮的确认对话框
     *
     * @param title           标题
     * @param message         确认信息
     * @param confirmListener 确认监听器
     * @param cancelListener  取消监听器
     * @return
     */
    public static Dialog confirm(Context context, CharSequence title, CharSequence message,
                                 DialogInterface.OnClickListener confirmListener,
                                 DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        // 不可取消
        builder.setCancelable(false);

        try {
            builder.setPositiveButton("确定", confirmListener);
        } catch (Exception e) {
            builder.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }

        try {
            builder.setNegativeButton("取消", cancelListener);
        } catch (Exception e) {
            builder.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }

        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }


    /**
     * 动画移动view并摆放至相应的位置
     *
     * @param view               控件
     * @param xFromDeltaDistance x起始位置的偏移量
     * @param xToDeltaDistance   x终止位置的偏移量
     * @param yFromDeltaDistance y起始位置的偏移量
     * @param yToDeltaDistance   y终止位置的偏移量
     * @param duration           动画的播放时间
     * @param delay              延迟播放时间
     * @param isBack             是否需要返回到开始位置
     */
    public static void moveViewWithAnimation(final View view, final float xFromDeltaDistance
            , final float xToDeltaDistance, final float yFromDeltaDistance
            , final float yToDeltaDistance, int duration, int delay, final boolean isBack) {
        //创建位移动画
        TranslateAnimation ani = new TranslateAnimation(xFromDeltaDistance, xToDeltaDistance, yFromDeltaDistance, yToDeltaDistance);
        ani.setInterpolator(new OvershootInterpolator());//设置加速器
        ani.setDuration(duration);//设置动画时间
        ani.setStartOffset(delay);//设置动画延迟时间
        //监听动画播放状态
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int deltaX = (int) (xToDeltaDistance - xFromDeltaDistance);
                int deltaY = (int) (yToDeltaDistance - yFromDeltaDistance);
                int layoutX = view.getLeft();
                int layoutY = view.getTop();
                int tempWidth = view.getWidth();
                int tempHeight = view.getHeight();
                view.clearAnimation();
                if (isBack == false) {
                    layoutX += deltaX;
                    layoutY += deltaY;
                    view.layout(layoutX, layoutY, layoutX + tempWidth, layoutY + tempHeight);
                } else {
                    view.layout(layoutX, layoutY, layoutX + tempWidth, layoutY + tempHeight);
                }
            }
        });
        view.startAnimation(ani);
    }


    @SuppressLint("ValidFragment")
    public static class ExamDialogFragment extends DialogFragment {
        View view;

        public ExamDialogFragment(View view) {
            this.view = view;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//            return inflater.inflate(layoutId, container, false);
            return view;
        }
    }


    public static Dialog dialogUtil;


    /**
     * 检查dialog 是否存在
     */
    private static Context checkDialog(Context context) {
        dismiss();
//        WeakReference<Activity> reference = new WeakReference<>(context);
        dialogUtil = new Dialog(context);
        return context;
    }

    public Dialog showAlertDialog(Context context, CharSequence title, CharSequence message,
                                  DialogInterface.OnClickListener confirmListener,
                                  DialogInterface.OnClickListener cancelListener) {

        checkDialog(context);
        // import android.support.v7.app.AlertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message);


        // 不可取消
        builder.setCancelable(false);

        try {
            builder.setPositiveButton("确定", confirmListener);
        } catch (Exception e) {
            builder.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }

        try {
            builder.setNegativeButton("取消", cancelListener);
        } catch (Exception e) {
            builder.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }


        dialogUtil = builder.create();

        dialogUtil.show();
        // 在dialog执行show之后才能来设置
        TextView tvMsg = (TextView) dialogUtil.findViewById(android.R.id.message);
        tvMsg.setTextSize(16);
        tvMsg.setTextColor(Color.parseColor("#4E4E4E"));

        Button button1 = (Button) dialogUtil.findViewById(android.R.id.button2);
        button1.setTextSize(16);
        button1.setTextColor(Color.parseColor("#1DA6DD"));

        Button button2 = (Button) dialogUtil.findViewById(android.R.id.button2);
        button2.setTextSize(16);
        button2.setTextColor(Color.parseColor("#8C8C8C"));

        Button button3 = (Button) dialogUtil.findViewById(android.R.id.button2);
        button3.setTextSize(16);
        button3.setTextColor(Color.parseColor("#8C8C8C"));

        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object alertController = mAlert.get(dialogUtil);

            Field mTitleView = alertController.getClass().getDeclaredField("mTitleView");
            mTitleView.setAccessible(true);

            TextView tvTitle = (TextView) mTitleView.get(alertController);
            if (null != tvTitle) {
                tvTitle.setTextSize(16);
                tvTitle.setTextColor(Color.parseColor("#000000"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dialogUtil;
    }

    public void dismissAlertDialog(){
        if (dialogUtil != null && dialogUtil.isShowing()) {
            dialogUtil.dismiss();
        }
    }


    /**
     * 从右边弹出
     *
     * @param context
     * @param view
     */
    public static void showDialogFromRight(Activity context, View view, int width) {
        showDialogFromRight(context, view, width, 0);
    }

    /**
     * 从底部弹出对话框
     *
     * @param context
     * @param view
     */
    public static void showDialogFromBottom(Activity context, View view) {
        checkDialog(context);
        view.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setContentView(view);

        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.dialog_anim_bottom);  //添加动画
//        window.getDecorView().setPadding(0,0,0,0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (context.getWindowManager().getDefaultDisplay().getWidth());
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.horizontalMargin = 0;
        dialog.getWindow().setAttributes(params);
    }

    /**
     * 从底部弹出对话框
     *
     * @param context
     * @param view
     */
    public static void showShareDialogBottom(Activity context, View view) {
        checkDialog(context);
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.dialog_anim_bottom);  //添加动画
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (context.getWindowManager().getDefaultDisplay().getWidth());
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.horizontalMargin = 0;
        dialog.getWindow().setAttributes(params);
    }

    /**
     * 从底部弹出对话框
     *
     * @param context
     * @param view
     */
    public static void showDialogFragmentBottom(Activity context, View view) {
        checkDialog(context);
        if (dialogFragment == null) {
            dialogFragment = new ExamDialogFragment(view);
        }
        dialogFragment.show(context.getFragmentManager(), "view");
        Window window = context.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.dialog_anim_bottom);  //添加动画
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (context.getWindowManager().getDefaultDisplay().getWidth());
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.horizontalMargin = 0;
        context.getWindow().setAttributes(params);
    }


}
