package com.system.mrqin.superutils;

import android.app.Application;

import com.system.mrqin.commonutil.log.LogUtil;

/**
 * Created by Administrator on 2017/11/28.
 */

public class UtilApplication extends Application {

    private static String TAG = "UtilApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.init(BuildConfig.LOG_DEBUG);
        LogUtil.v(TAG, BuildConfig.LOG_DEBUG);
        LogUtil.w(TAG, BuildConfig.LOG_DEBUG);
        LogUtil.i(TAG, BuildConfig.LOG_DEBUG);
        LogUtil.d(TAG, BuildConfig.LOG_DEBUG);
        LogUtil.e(TAG, BuildConfig.LOG_DEBUG);
    }
}
