package com.fpp.status;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.fpp.status.activity.DHActivity;
import com.fpp.status.activity.customview.CustomViewEightActivity;
import com.fpp.status.activity.customview.CustomViewFiveActivity;
import com.fpp.status.activity.customview.CustomViewFourActivity;
import com.fpp.status.activity.customview.CustomViewOneActivity;
import com.fpp.status.activity.customview.CustomViewSevenActivity;
import com.fpp.status.activity.customview.CustomViewSixActivity;
import com.fpp.status.activity.customview.CustomViewThreeActivity;
import com.fpp.status.activity.customview.CustomViewTwoActivity;
import com.fpp.status.activity.eight.four.GestureOneActivity;
import com.fpp.status.activity.eight.four.SQLiteActivity;
import com.fpp.status.activity.eight.four.SharePreferencesActivity;
import com.fpp.status.activity.eight.four.SharePreferencesExampleActivity;
import com.fpp.status.activity.eight.one.AllSelectListActivity;
import com.fpp.status.activity.eight.three.PermissionManageActivity;
import com.fpp.status.activity.eight.three.PermissionManageOneActivity;
import com.fpp.status.activity.eight.two.ResourceActivity;
import com.fpp.status.activity.eleven.eight.NfcActivity;
import com.fpp.status.activity.eleven.five.KeyBoardActivity;
import com.fpp.status.activity.eleven.four.InstallActivity;
import com.fpp.status.activity.eleven.one.UsbCameraActivity;
import com.fpp.status.activity.eleven.six.BarcodeScannerActivity;
import com.fpp.status.activity.eleven.two.ExcelActivity;
import com.fpp.status.activity.four.FourFourActivity;
import com.fpp.status.activity.four.FourThreeActivity;
import com.fpp.status.activity.four.eight.HandlerActivity;
import com.fpp.status.activity.fourfive.GuideTwoActivity;
import com.fpp.status.activity.fourseven.GuideFourActivity;
import com.fpp.status.activity.foursix.GuideThreeActivity;
import com.fpp.status.activity.fragmenteight.FragmentEightActivity;
import com.fpp.status.activity.fragmentfive.AllSelectActivity;
import com.fpp.status.activity.fragmentfour.ListViewNestListViewActivity;
import com.fpp.status.activity.fragmentone.OneFragmentActivity;
import com.fpp.status.activity.fragmentseven.FragmentSevenActivity;
import com.fpp.status.activity.fragmentsix.FragmentSixActivity;
import com.fpp.status.activity.fragmentthree.ThreeFragmentActivity;
import com.fpp.status.activity.fragmenttwo.FragmentTwoActivity;
import com.fpp.status.activity.handler.HandlerFiveActivity;
import com.fpp.status.activity.handler.HandlerFourActivity;
import com.fpp.status.activity.handler.HandlerOneActivity;
import com.fpp.status.activity.handler.HandlerTestActivity;
import com.fpp.status.activity.handler.HandlerThreeActivity;
import com.fpp.status.activity.move.MoveFourActivity;
import com.fpp.status.activity.move.MoveOneActivity;
import com.fpp.status.activity.move.MoveThreeActivity;
import com.fpp.status.activity.move.MoveTwoActivity;
import com.fpp.status.activity.nine.five.JsActivity;
import com.fpp.status.activity.nine.four.WebActivity;
import com.fpp.status.activity.nine.one.RVOneActivity;
import com.fpp.status.activity.nine.six.AnimationActivity;
import com.fpp.status.activity.nine.three.PictureLargenActivity;
import com.fpp.status.activity.nine.two.GridManagerActivity;
import com.fpp.status.activity.popupwindow.LoginActivity;
import com.fpp.status.activity.popupwindow.PWOneActivity;
import com.fpp.status.activity.ten.eight.TenEightActivity;
import com.fpp.status.activity.ten.five.VideoActivity;
import com.fpp.status.activity.ten.four.TenFourTwoActivity;
import com.fpp.status.activity.ten.one.GreenDaoActivity;
import com.fpp.status.activity.ten.three.TenThreeActivity;
import com.fpp.status.activity.ten.two.TenTwoActivity;
import com.fpp.status.activity.test.MoveActivity;
import com.fpp.status.activity.test.MoveImageActivity;
import com.fpp.status.activity.test.MoveTestActivity;
import com.fpp.status.activity.test.MoveViewGroupActivity;
import com.fpp.status.activity.test.TestActivity;
import com.fpp.status.activity.three.eight.EventTwoActivity;
import com.fpp.status.activity.three.seven.ConfigurationActivity;
import com.fpp.status.activity.three.six.MoveDeleteRecycleViewItemActivity;
import com.fpp.status.activity.two.RecycleViewActivity;
import com.fpp.status.activity.twotwo.PWActivity;
import com.fpp.status.receiver.UpdateReceiver;
import com.fpp.status.view.customvideoview.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends Activity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //监听系统新安装程序的广播
        UpdateReceiver receiver = new UpdateReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addDataScheme("package");    //必须添加这项，否则拦截不到广播
        registerReceiver(receiver, filter);

        getPermissions();


        // TODO: 2018/6/23 极光推送 --------------------------------------------------------------------


//
//
//
//        /*------------------隐藏状态栏---------------------*/
//        //完全隐藏状态栏
////        View decorView = getWindow().getDecorView();
////        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
////        decorView.setSystemUiVisibility(option);
////        ActionBar actionBar = getSupportActionBar();
////        actionBar.hide();
//        /*-------------------隐藏状态栏--------------------*/
//
//         /*------------------隐藏状态栏---------------------*/
//        /*
//        透明状态栏
//
//         首先需要注意，饿了么这样的效果是只有5.0及以上系统才支持，因此这里先进行了一层if判断，
//        只有系统版本大于或等于5.0的时候才会执行下面的代码。
//
//        接下来我们使用了SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN和SYSTEM_UI_FLAG_LAYOUT_STABLE，
//        注意两个Flag必须要结合在一起使用，表示会让应用的主体内容占用系统状态栏的空间，
//        最后再调用Window的setStatusBarColor()方法将状态栏设置成透明色就可以了。
//        */
//
//
//
//        /*-------------------隐藏状态栏--------------------*/
//
//        /*------------------隐藏状态栏---------------------*/
//        /*
//         此时只是初次隐藏了，当屏幕点击之后，状态栏和导航栏就会恢复原状
//        */
//
//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(option);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
//        /*-------------------隐藏状态栏--------------------*/
//
//        /*------------------隐藏状态栏---------------------*/
//        /*
//         设置状态栏和导航栏都为透明
//         使用了SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION，表示会让应用的主体内容占用系统导航栏的空间，
//         然后又调用了setNavigationBarColor()方法将导航栏设置成透明色。
//        */
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
//
//        /*-------------------隐藏状态栏--------------------*/
//

    }

    @OnClick({R.id.btn_one_one, R.id.btn_one_two, R.id.btn_one_three, R.id.btn_one_four,
            R.id.btn_one_five, R.id.btn_one_six, R.id.btn_one_seven, R.id.btn_two_one,
            R.id.btn_two_two, R.id.btn_two_three, R.id.btn_two_four, R.id.btn_two_five,
            R.id.btn_two_six, R.id.btn_two_seven, R.id.btn_two_eight, R.id.btn_three_one,
            R.id.btn_three_two, R.id.btn_three_three, R.id.btn_three_four,
            R.id.btn_three_five, R.id.btn_three_six, R.id.btn_three_seven,
            R.id.btn_three_eight, R.id.btn_four_one, R.id.btn_four_two, R.id.btn_four_three,
            R.id.btn_four_four, R.id.btn_four_five, R.id.btn_four_six, R.id.btn_four_seven,
            R.id.btn_four_eight, R.id.btn_five_one, R.id.btn_five_two, R.id.btn_five_three,
            R.id.btn_five_four,
            R.id.btn_five_five, R.id.btn_five_six, R.id.btn_five_seven, R.id.btn_five_eight,
            R.id.btn_six_one, R.id.btn_six_two, R.id.btn_six_three, R.id.btn_six_four,
            R.id.btn_six_five, R.id.btn_six_six, R.id.btn_six_seven, R.id.btn_six_eight,
            R.id.btn_eight_one, R.id.btn_eight_two, R.id.btn_eight_three, R.id.btn_eight_four,
            R.id.btn_eight_five, R.id.btn_eight_six, R.id.btn_eight_seven, R.id.btn_eight_eight
            , R.id.btn_nine_one, R.id.btn_nine_two, R.id.btn_nine_three
            , R.id.btn_nine_four, R.id.btn_nine_five, R.id.btn_nine_six
            , R.id.btn_nine_seven, R.id.btn_nine_eight
            , R.id.btn_ten_one, R.id.btn_ten_two
            , R.id.btn_ten_three, R.id.btn_ten_four
            , R.id.btn_ten_five, R.id.btn_ten_six
            , R.id.btn_ten_seven, R.id.btn_ten_eight
            , R.id.btn_eleven_one, R.id.btn_eleven_two, R.id.btn_eleven_three, R.id.btn_eleven_four
            , R.id.btn_eleven_five, R.id.btn_eleven_six, R.id.btn_eleven_seven, R.id.btn_eleven_eight
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one_one:
                intent = new Intent(this, HandlerOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_one_two:
                intent = new Intent(this, HandlerTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_one_three:
                intent = new Intent(this, HandlerThreeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_one_four:
                intent = new Intent(this, HandlerFourActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_one_five:
                intent = new Intent(this, HandlerFiveActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_one_six:
                intent = new Intent(this, MoveViewGroupActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_one_seven:
                intent = new Intent(this, MoveViewGroupActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_two_one:
                intent = new Intent(this, DLActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_two_two:
                intent = new Intent(this, PWActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_two_three:
                intent = new Intent(this, DHActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_two_four:
                intent = new Intent(this, MoveActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_two_five:
                intent = new Intent(this, MoveTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_two_six:
                intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_two_seven:
                intent = new Intent(this, MoveImageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_two_eight:
                intent = new Intent(this, RecycleViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_three_one:
                intent = new Intent(this, MoveOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_three_two:
                intent = new Intent(this, MoveTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_three_three:
                intent = new Intent(this, MoveThreeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_three_four:
                intent = new Intent(this, MoveFourActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_three_five:
                intent = new Intent(this, MoveActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_three_six:
                intent = new Intent(this, MoveDeleteRecycleViewItemActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_three_seven:
                intent = new Intent(this, ConfigurationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_three_eight:
                intent = new Intent(this, EventTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_four_one:
                intent = new Intent(this, PWOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_four_two:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_four_three:
                intent = new Intent(this, FourThreeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_four_four:
                intent = new Intent(this, FourFourActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_four_five:
                intent = new Intent(this, GuideTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_four_six:
                intent = new Intent(this, GuideThreeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_four_seven:
                intent = new Intent(this, GuideFourActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_four_eight:
                intent = new Intent(this, HandlerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_five_one:
                intent = new Intent(this, CustomViewOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_five_two:
                intent = new Intent(this, CustomViewTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_five_three:
                intent = new Intent(this, CustomViewThreeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_five_four:
                intent = new Intent(this, CustomViewFourActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_five_five:
                intent = new Intent(this, CustomViewFiveActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_five_six:
                intent = new Intent(this, CustomViewSixActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_five_seven:
                intent = new Intent(this, CustomViewSevenActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_five_eight:
                intent = new Intent(this, CustomViewEightActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_six_one:
                intent = new Intent(this, OneFragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_six_two:
                intent = new Intent(this, FragmentTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_six_three:
                intent = new Intent(this, ThreeFragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_six_four:
                intent = new Intent(this, ListViewNestListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_six_five:
                intent = new Intent(this, AllSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_six_six:
                intent = new Intent(this, FragmentSixActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_six_seven:
                intent = new Intent(this, FragmentSevenActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_six_eight:
                intent = new Intent(this, FragmentEightActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eight_one:
                intent = new Intent(this, AllSelectListActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eight_two:
                intent = new Intent(this, ResourceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eight_three:
                intent = new Intent(this, PermissionManageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eight_four:
                intent = new Intent(this, PermissionManageOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eight_five:
                intent = new Intent(this, SharePreferencesActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eight_six:
                intent = new Intent(this, SharePreferencesExampleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eight_seven:
                intent = new Intent(this, SQLiteActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eight_eight:
                intent = new Intent(this, GestureOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_nine_one:
                intent = new Intent(this, RVOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_nine_two:
                intent = new Intent(this, GridManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_nine_three:
                intent = new Intent(this, PictureLargenActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_nine_four:
                intent = new Intent(this, WebActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_nine_five:
                intent = new Intent(this, JsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_nine_six:
                intent = new Intent(this, AnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_nine_seven:
                intent = new Intent(this, com.fpp.status.activity.nine.seven.RecycleViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_nine_eight:
                intent = new Intent(this, com.fpp.status.transformer.MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ten_one:
                intent = new Intent(this, GreenDaoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ten_two:
                intent = new Intent(this, TenTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ten_three:
                intent = new Intent(this, TenThreeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ten_four:
                intent = new Intent(this, TenFourTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ten_five:
                intent = new Intent(this, VideoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ten_six:
                intent = new Intent(this, com.fpp.status.activity.ten.six.DLActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ten_seven:
//                intent = new Intent(this, TenSevenActivity.class);
//                startActivity(intent);
                break;
            case R.id.btn_ten_eight:
                intent = new Intent(this, TenEightActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eleven_one:
                intent = new Intent(this, UsbCameraActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eleven_two:
                intent = new Intent(this, ExcelActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eleven_three:
//                intent = new Intent(this, SerialPortActivity.class);
//                startActivity(intent);
                break;
            case R.id.btn_eleven_four:
                intent = new Intent(this, InstallActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eleven_five:
                intent = new Intent(this, KeyBoardActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eleven_six:
                intent = new Intent(this, BarcodeScannerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eleven_seven:
//                intent = new Intent(this, ChartActivity.class);
//                startActivity(intent);
                break;
            case R.id.btn_eleven_eight:
                intent = new Intent(this, NfcActivity.class);
                startActivity(intent);
//                intent = new Intent(this, LinkmanActivity.class);
//                startActivity(intent);
//                intent = new Intent(this, CheckActivity.class);
//                startActivity(intent);
                break;
        }
    }


    private void getPermissions() {
        List<String> perms01 = new ArrayList<>();
        perms01.add(Manifest.permission.CAMERA);
        perms01.add(Manifest.permission.ACCEPT_HANDOVER);
        perms01.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        perms01.add(Manifest.permission.ACCESS_CHECKIN_PROPERTIES);
        perms01.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        perms01.add(Manifest.permission.ACCESS_FINE_LOCATION);
        perms01.add(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS);
        perms01.add(Manifest.permission.ACCESS_MEDIA_LOCATION);
        perms01.add(Manifest.permission.ACCESS_NETWORK_STATE);
        perms01.add(Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        perms01.add(Manifest.permission.ACCESS_WIFI_STATE);
        perms01.add(Manifest.permission.ACCOUNT_MANAGER);
        perms01.add(Manifest.permission.ACTIVITY_RECOGNITION);
        perms01.add(Manifest.permission.ADD_VOICEMAIL);
        perms01.add(Manifest.permission.ANSWER_PHONE_CALLS);
        perms01.add(Manifest.permission.BATTERY_STATS);
        perms01.add(Manifest.permission.BIND_ACCESSIBILITY_SERVICE);
        perms01.add(Manifest.permission.BIND_APPWIDGET);
        perms01.add(Manifest.permission.BIND_AUTOFILL_SERVICE);
        perms01.add(Manifest.permission.BIND_CALL_REDIRECTION_SERVICE);
        perms01.add(Manifest.permission.BIND_CARRIER_MESSAGING_CLIENT_SERVICE);
        List<String> perms02 = new ArrayList<>();
        perms02.add(Manifest.permission.BIND_CARRIER_MESSAGING_SERVICE);
        perms02.add(Manifest.permission.BIND_CARRIER_SERVICES);
        perms02.add(Manifest.permission.BIND_CHOOSER_TARGET_SERVICE);
        perms02.add(Manifest.permission.BIND_CONDITION_PROVIDER_SERVICE);
        perms02.add(Manifest.permission.BIND_DEVICE_ADMIN);
        perms02.add(Manifest.permission.BIND_DREAM_SERVICE);
        perms02.add(Manifest.permission.BIND_INCALL_SERVICE);
        perms02.add(Manifest.permission.BIND_INPUT_METHOD);
        perms02.add(Manifest.permission.BIND_MIDI_DEVICE_SERVICE);
        perms02.add(Manifest.permission.BIND_NFC_SERVICE);
        perms02.add(Manifest.permission.BIND_NOTIFICATION_LISTENER_SERVICE);
        perms02.add(Manifest.permission.BIND_PRINT_SERVICE);
        perms02.add(Manifest.permission.BIND_QUICK_SETTINGS_TILE);
        perms02.add(Manifest.permission.BIND_REMOTEVIEWS);
        perms02.add(Manifest.permission.BIND_SCREENING_SERVICE);
        perms02.add(Manifest.permission.BIND_TELECOM_CONNECTION_SERVICE);
        perms02.add(Manifest.permission.BIND_TEXT_SERVICE);
        perms02.add(Manifest.permission.BIND_TV_INPUT);
        perms02.add(Manifest.permission.BIND_VISUAL_VOICEMAIL_SERVICE);
        perms02.add(Manifest.permission.BIND_VOICE_INTERACTION);
        perms02.add(Manifest.permission.BIND_VPN_SERVICE);
        perms02.add(Manifest.permission.BIND_VR_LISTENER_SERVICE);
        perms02.add(Manifest.permission.BIND_WALLPAPER);
        perms02.add(Manifest.permission.BLUETOOTH);
        perms02.add(Manifest.permission.BLUETOOTH_ADMIN);
        perms02.add(Manifest.permission.BLUETOOTH_PRIVILEGED);
        perms02.add(Manifest.permission.BODY_SENSORS);
        perms02.add(Manifest.permission.BROADCAST_PACKAGE_REMOVED);
        perms02.add(Manifest.permission.BROADCAST_SMS);
        perms02.add(Manifest.permission.BROADCAST_STICKY);
        perms02.add(Manifest.permission.BROADCAST_WAP_PUSH);
        perms02.add(Manifest.permission.CALL_COMPANION_APP);
        perms02.add(Manifest.permission.CALL_PHONE);
        perms02.add(Manifest.permission.CALL_PRIVILEGED);
        perms02.add(Manifest.permission.CAMERA);
        perms02.add(Manifest.permission.CAPTURE_AUDIO_OUTPUT);
        perms02.add(Manifest.permission.CHANGE_COMPONENT_ENABLED_STATE);
        perms02.add(Manifest.permission.CHANGE_CONFIGURATION);
        perms02.add(Manifest.permission.CHANGE_NETWORK_STATE);
        perms02.add(Manifest.permission.CHANGE_WIFI_MULTICAST_STATE);
        perms02.add(Manifest.permission.CHANGE_WIFI_STATE);
        perms02.add(Manifest.permission.CLEAR_APP_CACHE);
        perms02.add(Manifest.permission.CONTROL_LOCATION_UPDATES);
        perms02.add(Manifest.permission.DELETE_CACHE_FILES);
        perms02.add(Manifest.permission.DELETE_PACKAGES);
        perms02.add(Manifest.permission.DIAGNOSTIC);
        perms02.add(Manifest.permission.DISABLE_KEYGUARD);
        perms02.add(Manifest.permission.DUMP);
        perms02.add(Manifest.permission.EXPAND_STATUS_BAR);
        perms02.add(Manifest.permission.FACTORY_TEST);
        perms02.add(Manifest.permission.FOREGROUND_SERVICE);
        perms02.add(Manifest.permission.GET_ACCOUNTS);
        perms02.add(Manifest.permission.GET_ACCOUNTS_PRIVILEGED);
        perms02.add(Manifest.permission.GET_PACKAGE_SIZE);

        List<String> perms03 = new ArrayList<>();
        perms03.add(Manifest.permission.GET_TASKS);
        perms03.add(Manifest.permission.GLOBAL_SEARCH);
        perms03.add(Manifest.permission.INSTALL_LOCATION_PROVIDER);
        perms03.add(Manifest.permission.INSTALL_PACKAGES);
        perms03.add(Manifest.permission.INSTALL_SHORTCUT);
        perms03.add(Manifest.permission.INSTANT_APP_FOREGROUND_SERVICE);
        perms03.add(Manifest.permission.INTERNET);
        perms03.add(Manifest.permission.KILL_BACKGROUND_PROCESSES);
        perms03.add(Manifest.permission.LOCATION_HARDWARE);
        perms03.add(Manifest.permission.MANAGE_DOCUMENTS);
        perms03.add(Manifest.permission.MANAGE_OWN_CALLS);
        perms03.add(Manifest.permission.MASTER_CLEAR);
        perms03.add(Manifest.permission.MEDIA_CONTENT_CONTROL);
        perms03.add(Manifest.permission.MODIFY_AUDIO_SETTINGS);
        perms03.add(Manifest.permission.MODIFY_PHONE_STATE);
        perms03.add(Manifest.permission.MOUNT_FORMAT_FILESYSTEMS);
        perms03.add(Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS);
        perms03.add(Manifest.permission.NFC);
        perms03.add(Manifest.permission.NFC_TRANSACTION_EVENT);
        perms03.add(Manifest.permission.PACKAGE_USAGE_STATS);

        List<String> perms04 = new ArrayList<>();
        perms04.add(Manifest.permission.PERSISTENT_ACTIVITY);

        List<String> perms05 = new ArrayList<>();
        perms05.add(Manifest.permission.PROCESS_OUTGOING_CALLS);
        perms05.add(Manifest.permission.READ_CALENDAR);
        perms05.add(Manifest.permission.READ_CALL_LOG);
        perms05.add(Manifest.permission.READ_CONTACTS);
        perms05.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> perms06 = new ArrayList<>();
        perms06.add(Manifest.permission.READ_INPUT_STATE);
        perms06.add(Manifest.permission.READ_LOGS);
        perms06.add(Manifest.permission.READ_PHONE_NUMBERS);
        perms06.add(Manifest.permission.READ_PHONE_STATE);
        perms06.add(Manifest.permission.READ_SMS);
        perms06.add(Manifest.permission.READ_SYNC_SETTINGS);
        perms06.add(Manifest.permission.READ_SYNC_STATS);
        perms06.add(Manifest.permission.READ_VOICEMAIL);
        perms06.add(Manifest.permission.REBOOT);
        perms06.add(Manifest.permission.RECEIVE_BOOT_COMPLETED);
        perms06.add(Manifest.permission.RECEIVE_MMS);
        perms06.add(Manifest.permission.RECEIVE_SMS);
        perms06.add(Manifest.permission.RECEIVE_WAP_PUSH);
        perms06.add(Manifest.permission.RECORD_AUDIO);
        perms06.add(Manifest.permission.REORDER_TASKS);
        perms06.add(Manifest.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND);
        perms06.add(Manifest.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND);
        perms06.add(Manifest.permission.REQUEST_DELETE_PACKAGES);
        perms06.add(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        perms06.add(Manifest.permission.REQUEST_INSTALL_PACKAGES);
        perms06.add(Manifest.permission.REQUEST_PASSWORD_COMPLEXITY);


        List<String> perms07 = new ArrayList<>();
        perms07.add(Manifest.permission.RESTART_PACKAGES);
        perms07.add(Manifest.permission.SEND_RESPOND_VIA_MESSAGE);
        perms07.add(Manifest.permission.SEND_SMS);
        perms07.add(Manifest.permission.SET_ALARM);
        perms07.add(Manifest.permission.SET_ALWAYS_FINISH);
        perms07.add(Manifest.permission.SET_ANIMATION_SCALE);
        perms07.add(Manifest.permission.SET_DEBUG_APP);

        List<String> perms08 = new ArrayList<>();
        perms08.add(Manifest.permission.SET_PREFERRED_APPLICATIONS);
        perms08.add(Manifest.permission.SET_PROCESS_LIMIT);
        perms08.add(Manifest.permission.SET_TIME);
        perms08.add(Manifest.permission.SET_TIME_ZONE);
        perms08.add(Manifest.permission.SET_WALLPAPER);
        perms08.add(Manifest.permission.SET_WALLPAPER_HINTS);
        perms08.add(Manifest.permission.SIGNAL_PERSISTENT_PROCESSES);
        perms08.add(Manifest.permission.SMS_FINANCIAL_TRANSACTIONS);
        perms08.add(Manifest.permission.START_VIEW_PERMISSION_USAGE);
        perms08.add(Manifest.permission.STATUS_BAR);
        perms08.add(Manifest.permission.SYSTEM_ALERT_WINDOW);
        perms08.add(Manifest.permission.TRANSMIT_IR);
        perms08.add(Manifest.permission.UNINSTALL_SHORTCUT);
        perms08.add(Manifest.permission.UPDATE_DEVICE_STATS);
        perms08.add(Manifest.permission.USE_BIOMETRIC);

        List<String> perms09 = new ArrayList<>();
        perms09.add(Manifest.permission.USE_FINGERPRINT);
        perms09.add(Manifest.permission.USE_FULL_SCREEN_INTENT);
        perms09.add(Manifest.permission.USE_SIP);
        perms09.add(Manifest.permission.VIBRATE);
        perms09.add(Manifest.permission.WAKE_LOCK);
        perms09.add(Manifest.permission.WRITE_APN_SETTINGS);
        perms09.add(Manifest.permission.WRITE_CALENDAR);
        perms09.add(Manifest.permission.WRITE_CALL_LOG);
        perms09.add(Manifest.permission.WRITE_CONTACTS);
        perms09.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        perms09.add(Manifest.permission.WRITE_GSERVICES);
        perms09.add(Manifest.permission.WRITE_SECURE_SETTINGS);
        perms09.add(Manifest.permission.WRITE_SETTINGS);
        perms09.add(Manifest.permission.WRITE_SYNC_SETTINGS);
        perms09.add(Manifest.permission.WRITE_VOICEMAIL);


        // 所需权限集合
        List<String> perms = new ArrayList<>();
        perms.addAll(perms01);
        perms.addAll(perms02);
        perms.addAll(perms03);
        perms.addAll(perms04);
        perms.addAll(perms05);
        perms.addAll(perms06);
        perms.addAll(perms07);
        perms.addAll(perms08);
        perms.addAll(perms09);

        List<String> permsNot = new ArrayList<>();
        // 检测那些权限未被授权
        for (int i = 0; i < perms.size(); i++) {
            int per = ContextCompat.checkSelfPermission(this, perms.get(i));
            if (per != PERMISSION_GRANTED) {
                permsNot.add(perms.get(i));
            }
        }
        if (permsNot.size() > 0) {
            String[] permissions = new String[permsNot.size()];
            permsNot.toArray(permissions);
            LogUtil.e("申请常用权限");
            // 申请授权
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            LogUtil.e("已获取权限");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (hasAllPermissionsGranted(grantResults)) {
                LogUtil.e("申请权限响应成功");

            }
        }
    }

    /**
     * 是否授权所有权限
     *
     * @param grantResults 授权响应集合
     * @return 响应
     */
    private boolean hasAllPermissionsGranted( int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }










    /*------------------隐藏状态栏---------------------*/
    //模拟爱奇艺播放视频时隐藏状态栏和导航栏

    //    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }
    /*-------------------隐藏状态栏--------------------*/

}
