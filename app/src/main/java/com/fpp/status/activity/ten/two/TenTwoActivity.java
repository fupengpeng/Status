package com.fpp.status.activity.ten.two;

import android.os.Bundle;
import android.view.MenuItem;

import com.fpp.status.R;
import com.fpp.status.activity.ten.two.remote.RemoteTestFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

/**
 * Description:
 * Author: fpp
 * Date: 2018/7/11  10:25
 */

public class TenTwoActivity  extends AppCompatActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        StatusBarUtils.setColor(this,getResources().getColor(R.color.colorPrimary),0);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.inflateMenu(R.menu.setting);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                if(item.getItemId() ==R.id.banner_mode){
//                    switchBannerMode();
//                }else if(item.getItemId() == R.id.viewPager_mode){
//                    switchViewPagerMode();
//                }else if(item.getItemId() == R.id.remote_mode){
//                    switchRemoteMode();
//                }else if(item.getItemId() == R.id.mz_mode_not_cover){
//                    switchMZModeNotCover();
//                }
                return true;
            }
        });

//        Fragment fragment = MZModeBannerFragment.newInstance();
//        getSupportFragmentManager().beginTransaction().add(R.id.home_container,fragment).commit();

        Fragment fragment = NormalViewPagerFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();

    }

    /**
     * banner模式
     */
    public void switchBannerMode(){
        Fragment fragment = MZModeBannerFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
    }

    /**
     * 普通ViewPager模式
     */
    public void switchViewPagerMode(){
        Fragment fragment = NormalViewPagerFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
    }

    /**
     * 从网络获取数据
     */
    public void switchRemoteMode(){
        Fragment fragment = RemoteTestFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
    }

    public void switchMZModeNotCover(){
        Fragment fragment = MZModeNotCoverFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
    }
}