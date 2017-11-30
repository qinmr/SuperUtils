package com.system.mrqin.commonutil.log.wrapper;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/28.
 */

class Line {

    static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

}
