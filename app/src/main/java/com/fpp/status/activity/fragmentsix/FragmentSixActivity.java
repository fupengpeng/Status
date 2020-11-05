package com.fpp.status.activity.fragmentsix;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.fpp.status.R;
import com.fpp.status.activity.fragmentsix.fragment.SixSixFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fupengpeng on 2017/12/15 0015.
 */

public class FragmentSixActivity extends AppCompatActivity {

    @BindView(R.id.ll_atvt_fragment_six_content)
    LinearLayout llAtvtFragmentSixContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_six);
        ButterKnife.bind(this);

        SixSixFragment sixSixFragment = new SixSixFragment();
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.ll_atvt_fragment_six_content,sixSixFragment);
        transaction.show(sixSixFragment);
        transaction.commitAllowingStateLoss();



    }

}
