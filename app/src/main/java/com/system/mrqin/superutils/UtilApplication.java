package com.system.mrqin.superutils;

import android.app.Application;

import com.system.mrqin.commonutil.log.LogUtil;
import com.system.mrqin.superutils.util.CityListLoader;

/**
 * Created by Administrator on 2017/11/28.
 *
 */

public class UtilApplication extends Application {

    private static String TAG = "UtilApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //城市列表
                CityListLoader.getInstance().loadProData(getApplicationContext());
            }
        }).start();
        LogUtil.init(BuildConfig.LOG_DEBUG);
    }
}
