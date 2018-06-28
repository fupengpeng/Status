package com.fpp.status;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
import com.fpp.status.activity.eight.three.PermissionManageActivity;
import com.fpp.status.activity.eight.three.PermissionManageOneActivity;
import com.fpp.status.activity.eight.two.ResourceActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    Intent intent;
    @BindView(R.id.btn_one_one)
    Button btnOneOne;
    @BindView(R.id.btn_one_two)
    Button btnOneTwo;
    @BindView(R.id.btn_one_three)
    Button btnOneThree;
    @BindView(R.id.btn_one_four)
    Button btnOneFour;
    @BindView(R.id.btn_one_five)
    Button btnOneFive;
    @BindView(R.id.btn_one_six)
    Button btnOneSix;
    @BindView(R.id.btn_one_seven)
    Button btnOneSeven;
    @BindView(R.id.btn_two_one)
    Button btnTwoOne;
    @BindView(R.id.btn_two_two)
    Button btnTwoTwo;
    @BindView(R.id.btn_two_three)
    Button btnTwoThree;
    @BindView(R.id.btn_two_four)
    Button btnTwoFour;
    @BindView(R.id.btn_two_five)
    Button btnTwoFive;
    @BindView(R.id.btn_two_six)
    Button btnTwoSix;
    @BindView(R.id.btn_two_seven)
    Button btnTwoSeven;
    @BindView(R.id.btn_two_eight)
    Button btnTwoEight;
    @BindView(R.id.btn_three_one)
    Button btnThreeOne;
    @BindView(R.id.btn_three_two)
    Button btnThreeTwo;
    @BindView(R.id.btn_three_three)
    Button btnThreeThree;
    @BindView(R.id.btn_three_four)
    Button btnThreeFour;
    @BindView(R.id.btn_three_five)
    Button btnThreeFive;
    @BindView(R.id.btn_three_six)
    Button btnThreeSix;
    @BindView(R.id.btn_three_seven)
    Button btnThreeSeven;
    @BindView(R.id.btn_three_eight)
    Button btnThreeEight;
    @BindView(R.id.btn_four_one)
    Button btnFourOne;
    @BindView(R.id.btn_four_two)
    Button btnFourTwo;
    @BindView(R.id.btn_four_three)
    Button btnFourThree;
    @BindView(R.id.btn_four_four)
    Button btnFourFour;
    @BindView(R.id.btn_four_five)
    Button btnFourFive;
    @BindView(R.id.btn_four_six)
    Button btnFourSix;
    @BindView(R.id.btn_four_seven)
    Button btnFourSeven;
    @BindView(R.id.btn_four_eight)
    Button btnFourEight;
    @BindView(R.id.btn_five_one)
    Button btnFiveOne;
    @BindView(R.id.btn_five_two)
    Button btnFiveTwo;
    @BindView(R.id.btn_five_three)
    Button btnFiveThree;
    @BindView(R.id.btn_five_four)
    Button btnFiveFour;
    @BindView(R.id.btn_five_five)
    Button btnFiveFive;
    @BindView(R.id.btn_five_six)
    Button btnFiveSix;
    @BindView(R.id.btn_five_seven)
    Button btnFiveSeven;
    @BindView(R.id.btn_five_eight)
    Button btnFiveEight;
    @BindView(R.id.btn_six_one)
    Button btnSixOne;
    @BindView(R.id.btn_six_two)
    Button btnSixTwo;
    @BindView(R.id.btn_six_three)
    Button btnSixThree;
    @BindView(R.id.btn_six_four)
    Button btnSixFour;
    @BindView(R.id.btn_six_five)
    Button btnSixFive;
    @BindView(R.id.btn_six_six)
    Button btnSixSix;
    @BindView(R.id.btn_six_seven)
    Button btnSixSeven;
    @BindView(R.id.btn_six_eight)
    Button btnSixEight;
    @BindView(R.id.btn_eight_one)
    Button btnEightOne;
    @BindView(R.id.btn_eight_two)
    Button btnEightTwo;
    @BindView(R.id.btn_eight_three)
    Button btnEightThree;
    @BindView(R.id.btn_eight_four)
    Button btnEightFour;
    @BindView(R.id.btn_eight_five)
    Button btnEightFive;
    @BindView(R.id.btn_eight_six)
    Button btnEightSix;
    @BindView(R.id.btn_eight_seven)
    Button btnEightSeven;
    @BindView(R.id.btn_eight_eight)
    Button btnEightEight;
    @BindView(R.id.btn_nine_one)
    Button btnNineOne;
    @BindView(R.id.btn_nine_two)
    Button btnNineTwo;
    @BindView(R.id.btn_nine_three)
    Button btnNineThree;
    @BindView(R.id.btn_nine_four)
    Button btnNineFour;
    @BindView(R.id.btn_nine_five)
    Button btnNineFive;
    @BindView(R.id.btn_nine_six)
    Button btnNineSix;
    @BindView(R.id.btn_nine_seven)
    Button btnNineSeven;
    @BindView(R.id.btn_nine_eight)
    Button btnNineEight;


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
            ,R.id.btn_nine_one, R.id.btn_nine_two, R.id.btn_nine_three
            , R.id.btn_nine_four, R.id.btn_nine_five, R.id.btn_nine_six
            , R.id.btn_nine_seven, R.id.btn_nine_eight


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
                // TODO: 2018/4/10 0010


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
                break;
        }
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
