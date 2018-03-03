package com.system.mrqin.commonutil.logs;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by mrqin on 2018/1/31 10.
 * E-Mail Address：mrqinjie@qq.com
 */

public class KLogUtil {

    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

}
