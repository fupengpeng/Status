package com.fpp.status.basemvp;

import android.os.Bundle;
<<<<<<< HEAD

import androidx.appcompat.app.AppCompatActivity;
=======
import android.support.v7.app.AppCompatActivity;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c

public abstract class BaseMvpActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }

    public abstract T initPresenter();

}
