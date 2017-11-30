package com.system.mrqin.commonutil;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/11/28.
 */

public class InstallUtil {

    private static final String TAG = "InstallUtil";


    /**
     * 是否已安装app
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            if (TextUtils.isEmpty(packageName))
                return false;
            return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            return false;
        }
    }

    /**
     * 打开app
     *
     * @param packageName
     * @param context
     */
    public static void openApp(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        if (intent != null)
            context.startActivity(intent);
    }

    /**
     * 安装apk文件
     */
    public static void installApk(Context context, String filepath) {
        context.startActivity(getInstallApkIntent(filepath));
    }

    /**
     * 获取本地apk文件intent
     */
    private static Intent getInstallApkIntent(String filepath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        File file = new File(filepath);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * 获取当前程序的版本号
     */
    public static String getVersionName(Context cx) {
        PackageInfo packageInfo = getPackageInfo(cx);
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return "";
    }
    /**
     * 获得包信息
     */
    private static PackageInfo getPackageInfo(Context cx) {
        try {
            return cx.getPackageManager().getPackageInfo(cx.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询包名是否存在
     */
    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


}
