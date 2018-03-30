package com.system.mrqin.superutils.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.system.mrqin.commonutil.logs.KLog;
import com.system.mrqin.superutils.R;

/**
 * Created by Administrator on 2018/2/24.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected String simpleName;
    protected Context mContext;
    protected View mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getViewLayoutId() != 0) {
            //设置布局资源文件
            mRootView = View.inflate(this, getViewLayoutId(), null);
            setContentView(mRootView);

            mContext = this;
            this.getClass().getSimpleName();
            simpleName = this.getClass().getSimpleName();
            KLog.i(simpleName, "onCreate");

            initViews(savedInstanceState);
            initEvent();
            initData();
            updateViews(false);
        } else {
            mRootView = View.inflate(this, R.layout.layout_error, null);
            setContentView(mRootView);
        }
    }

    //生命周期----start---------------------------

    @Override
    protected void onRestart() {
        super.onRestart();
        KLog.i(simpleName, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        KLog.i(simpleName, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        KLog.i(simpleName, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        KLog.i(simpleName, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.i(simpleName, "onDestroy");
    }

    protected void initToolBar(String title, Toolbar toolbar, boolean homeAsUpEnabled) {
        setSupportActionBar(toolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public <V extends View> V findView(int id) {
        return (V) findViewById(id);
    }

    //生命周期----end---------------------------
    abstract int getViewLayoutId();

    abstract void initViews(Bundle savedInstanceState);

    abstract void initEvent();

    abstract void initData();

    abstract void updateViews(boolean isRefresh);
}
