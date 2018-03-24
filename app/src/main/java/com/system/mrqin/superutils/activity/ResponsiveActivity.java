package com.system.mrqin.superutils.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.system.mrqin.superutils.R;
import com.system.mrqin.superutils.adapter.EasyAdapter;
import com.system.mrqin.superutils.bean.User;
import com.system.mrqin.superutils.viewmodel.UserProfileViewModel;

import java.util.List;

public class ResponsiveActivity extends BaseActivity {

    private UserProfileViewModel viewModel;

    EasyAdapter adapter;

    @Override
    int getViewLayoutId() {
        return R.layout.activity_responsive;
    }

    @Override
    void initViews(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(getString(R.string.responsive_UI), toolbar, true);
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.init("265");
        viewModel.getUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                adapter.clear();
                adapter.addAll(users);
            }
        });
        adapter = new EasyAdapter(mContext);
        RecyclerView easyRecyclerView = (RecyclerView) findViewById(R.id.easy);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        easyRecyclerView.setAdapter(adapter);
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
        context.startActivity(new Intent(context, ResponsiveActivity.class));
    }
}
