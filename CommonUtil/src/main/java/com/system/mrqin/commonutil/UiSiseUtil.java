package com.system.mrqin.commonutil;

import android.content.Context;

/**
 * Created by mrqin on 2017/12/11 16.
 * E-Mail Address：mrqinjie@qq.com
 */

public class UiSiseUtil {

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(float pxValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public static int dp2px(float dipValue, float scale) {
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue, float scale) {
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

}
