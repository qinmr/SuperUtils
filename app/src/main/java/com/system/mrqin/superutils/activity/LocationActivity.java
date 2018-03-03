package com.system.mrqin.superutils.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.system.mrqin.commonutil.logs.KLog;
import com.system.mrqin.superutils.R;

import java.io.File;

public class LocationActivity extends BaseActivity {

    @Override
    int getViewLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    void initViews(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(getString(R.string.location_util), toolbar, true);
    }

    @Override
    void initEvent() {

    }

    @Override
    void initData() {

    }

    @Override
        void updateViews(boolean isRefresh) {

    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, LocationActivity.class));
    }
}
