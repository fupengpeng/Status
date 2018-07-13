package com.fpp.status.transformer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fpp.status.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
    }

    public void onStandardViewPager(View view) {
        Intent intent = new Intent(MainActivity.this, StandardViewPagerActivity.class);
        startActivity(intent);
    }

    public void onCircleViewPager(View view) {
        Intent intent = new Intent(MainActivity.this, CircleViewPagerActivity.class);
        startActivity(intent);
    }
    public void onCarouselViewPager(View view) {
        Intent intent = new Intent(MainActivity.this, CarouselActivity.class);
        startActivity(intent);
    }

    public void onCarouselViewPagerOne(View view) {
        Intent intent = new Intent(MainActivity.this, CustomViewPagerActivity.class);
        startActivity(intent);
    }
}
