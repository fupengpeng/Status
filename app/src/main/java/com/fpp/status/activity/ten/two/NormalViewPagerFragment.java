package com.fpp.status.activity.ten.two;

import android.content.Context;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.fpp.status.R;
import com.fpp.status.activity.ten.two.holder.MZHolderCreator;
import com.fpp.status.activity.ten.two.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

=======
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c

/**
 * Created by zhouwei on 17/5/31.
 */

public class NormalViewPagerFragment extends Fragment {
    public static final int []RES = new int[]{R.mipmap.image5,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image6,R.mipmap.image7,R.mipmap.image8};
    public static final int []BANNER = new int[]{R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3,R.mipmap.banner4,R.mipmap.banner5};

    private MZBannerView mMZBannerView;
    private MZBannerView mNormalViewPager;

    public static NormalViewPagerFragment newInstance(){
        return new NormalViewPagerFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.normal_view_pager_layout,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        mMZBannerView = (MZBannerView) view.findViewById(R.id.mz_view_pager);
        mNormalViewPager = (MZBannerView) view.findViewById(R.id.normal_viewPager);



        mMZBannerView.setPages(mockData(), new MZHolderCreator<ViewPagerHolder>() {
            @Override
            public ViewPagerHolder createViewHolder() {
                return new ViewPagerHolder();
            }
        });

        mNormalViewPager.setIndicatorRes(R.drawable.dot_unselect_image,R.drawable.dot_select_image);
        mNormalViewPager.setPages(mockData(), new MZHolderCreator<ViewPagerHolder>() {
            @Override
            public ViewPagerHolder createViewHolder() {
                return new ViewPagerHolder();
            }
        });

    }


    public static final class ViewPagerHolder implements MZViewHolder<DataEntry> {
        private ImageView mImageView;
        private TextView mTitle;
        private TextView mDesc;
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.normal_banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.normal_banner_image);
            mDesc = (TextView) view.findViewById(R.id.page_desc);
            return view;
        }

        @Override
        public void onBind(Context context, int position, DataEntry data) {
           mImageView.setImageResource(data.resId);
           mDesc.setText(data.desc);
        }
    }

    private List<DataEntry> mockData(){
        List<DataEntry> list = new ArrayList<>();
        DataEntry dataEntry = null;
        for(int i=0;i<RES.length;i++){
            dataEntry = new DataEntry();
            dataEntry.resId = RES[i];
            dataEntry.desc = "The time you enjoy wasting , is not wasted.";
            list.add(dataEntry);
        }

        return list;
    }
}
