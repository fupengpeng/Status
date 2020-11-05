package com.fpp.status.activity.test;

import android.os.Bundle;

import com.fpp.status.R;
import com.fpp.status.view.Workspace;

import androidx.appcompat.app.AppCompatActivity;

public class MoveViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Workspace(this));
    }
}
