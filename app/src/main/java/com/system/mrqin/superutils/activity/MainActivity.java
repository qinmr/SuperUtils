package com.system.mrqin.superutils.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.system.mrqin.superutils.R;

/**
 * Created by mrqin on 2018/1/29 15.
 * E-Mail Address：mrqinjie@qq.com
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    public void startCameraActivity(View view) {
        CameraActivity.start(MainActivity.this);
    }

    public void startLocationActivity(View view) {
        LocationActivity.start(MainActivity.this);
    }
}
