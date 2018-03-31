package com.system.mrqin.superutils.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.system.mrqin.superutils.R;

public class HotfixActivity extends BaseActivity {


    @Override
    int getViewLayoutId() {
        return R.layout.activity_hotfix;
    }

    @Override
    void initViews(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(getString(R.string.hotfix), toolbar, true);
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
        context.startActivity(new Intent(context, HotfixActivity.class));
    }
}
