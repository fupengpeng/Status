package com.fpp.status.utils;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;


import com.fpp.status.R;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author fpp
 * @Description: 输入键盘工具类
 * @date 2018/12/6 14:04
 */
public class KeyboardUtil {
    public static final String TAG = KeyboardUtil.class.getName();
    private KeyboardView keyboardView;
    private EditText editText;
    private Keyboard mKNum;// 自定义数字键盘
    private Keyboard mKEng;// 自定义英文键盘
    private boolean isSupper = false;//是否大写
    private boolean isNum = false;//是否是数字
    private boolean isShown = false;

    public KeyboardUtil(Activity activity, KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;
        // 系统键盘处理
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            this.editText.setInputType(InputType.TYPE_NULL);
        } else {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(this.editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.editText.setSelected(true);
        this.editText.setCursorVisible(true);
        mKNum = new Keyboard(editText.getContext(), R.xml.num);
        mKEng = new Keyboard(editText.getContext(), R.xml.eng);
        KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {

            @Override
            public void swipeUp() {
            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeLeft() {
            }

            @Override
            public void swipeDown() {
            }

            @Override
            public void onText(CharSequence text) {
            }

            @Override
            public void onRelease(int primaryCode) {
            }

            @Override
            public void onPress(int primaryCode) {
            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                Editable editable = editText.getText();
                int start = editText.getSelectionStart();
                switch (primaryCode) {
                    case Keyboard.KEYCODE_SHIFT:// 大小写切换
                        changeKey();
                        keyboardView.setKeyboard(mKEng);
                        break;
                    case Keyboard.KEYCODE_CANCEL://  2018/12/4 确定处理
//                    hideKeyboard();
                        break;
                    case Keyboard.KEYCODE_DELETE:
                        if (editable != null && editable.length() > 0) {
                            if (start > 0) {
                                editable.delete(start - 1, start);
                            }
                        }
                        break;
                    case Keyboard.KEYCODE_ALT:// 数字键盘切换
                        if (isNum) {
                            keyboardView.setKeyboard(mKNum);
                        } else {
                            keyboardView.setKeyboard(mKEng);
                        }
                        isNum = !isNum;
                        break;
                    case 13:
                        break;
                    case -7:
                        if (editable != null && editable.length() > 0) {
                            if (start > 0) {
                                editable.delete(0, editable.length());
                            }
                        }
                        break;
                    case -8://  2018/12/10   00 处理
                        editable.insert(start, "00");
                        break;
                    default:
                        editable.insert(start, Character.toString((char) primaryCode));
                        break;
                }
            }
        };
        this.keyboardView.setOnKeyboardActionListener(listener);
        this.keyboardView.setKeyboard(mKNum);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
    }

    /**
     * Activity中获取焦点时调用，显示出键盘
     */
    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }
    }

    /**
     * 变换大小写
     */
    private void changeKey() {
        List<Keyboard.Key> keys = mKEng.getKeys();
        for (Keyboard.Key key : keys) {
            if (key.label != null && isWord(String.valueOf(key.label))) {
                if (isSupper) {// up to low
                    key.label = key.label.toString().toLowerCase();
                    key.codes[0] = key.codes[0] + 32;
                } else {
                    key.label = key.label.toString().toUpperCase();
                    key.codes[0] = key.codes[0] - 32;
                }
            }
        }
        isSupper = !isSupper;
    }

    private boolean isWord(String s) {
        String reg = "[a-zA-Z]{1}";
        return s.matches(reg);
    }
}
