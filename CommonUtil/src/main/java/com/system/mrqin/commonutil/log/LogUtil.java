package com.system.mrqin.commonutil.log;

import android.text.TextUtils;
import android.util.Log;

import com.system.mrqin.commonutil.log.wrapper.JsonLog;
import com.system.mrqin.commonutil.log.wrapper.XmlLog;

/**
 * Created by Administrator on 2017/11/28.
 */

public class LogUtil {

    public static final int JSON_INDENT = 4;
    public static final String LINE_SEPARATOR = System.getProperty("Line.separator");
    public static final String NULL_TIPS = "Log with null object";

    private static final int V = 0x1;
    private static final int D = 0x2;
    private static final int I = 0x3;
    private static final int W = 0x4;
    private static final int E = 0x5;
    private static final int JSON = 0x6;
    private static final int XML = 0x7;
    private static final int UI = 0x8;
    private static final int TEST = 0x9;

    private static boolean IS_SHOW_LOG = true;

    public static void init(boolean isShowLog) {
        IS_SHOW_LOG = isShowLog;
    }

    public static void v(String tag, Object msg) {
        v(tag, msg, null);
    }

    public static void v(String tag, Object msg, Throwable r) {
        printLog(V, tag, msg, r);
    }

    public static void d(String tag, Object msg) {
        d(tag, msg, null);
    }

    public static void d(String tag, Object msg, Throwable r) {
        printLog(D, tag, msg, r);
    }

    public static void i(String tag, Object msg) {
        i(tag, msg, null);
    }

    public static void i(String tag, Object msg, Throwable r) {
        printLog(I, tag, msg, r);
    }

    public static void w(String tag, Object msg) {
        w(tag, msg, null);
    }

    public static void w(String tag, Object msg, Throwable r) {
        printLog(W, tag, msg, r);
    }

    public static void e(String tag, Object msg) {
        e(tag, msg, null);
    }

    public static void e(String tag, Object msg, Throwable r) {
        printLog(E, tag, msg, r);
    }

    public static void json(String tag, Object jsonFormat) {
        printLog(JSON, tag, jsonFormat, null);
    }

    public static void xml(String tag, Object xml) {
        printLog(XML, tag, xml, null);
    }

    public static void ui(Object msg) {
        printLog(UI, "ui", msg, null);
    }

    public static void test(Object msg) {
        printLog(TEST, "test", msg, null);
    }

    private static void printLog(int type, String tag, Object msg, Throwable r) {

        if (!IS_SHOW_LOG) {
            return;
        }

        switch (type) {
            case V:
                Log.v(buildTag(tag), buildMessage(msg.toString()), r);
                break;
            case D:
                Log.d(buildTag(tag), buildMessage(msg.toString()), r);
                break;
            case I:
                Log.i(buildTag(tag), buildMessage(msg.toString()), r);
                break;
            case W:
                Log.w(buildTag(tag), buildMessage(msg.toString()), r);
                break;
            case E:
                Log.e(buildTag(tag), buildMessage(msg.toString()), r);
                break;
            case JSON:
                JsonLog.printJson(tag, msg.toString());
                break;
            case XML:
                XmlLog.printXml(tag, msg.toString());
                break;
            case UI:
                XmlLog.printXml(tag, msg.toString());
                break;
            case TEST:
                XmlLog.printXml(tag, msg.toString());
                break;
            default:break;
        }
    }

    private static String buildTag(String tag) {
        return TextUtils.isEmpty(tag) ? LogUtil.NULL_TIPS : tag + ":";
    }

    private static String buildMessage(String msg) {
        return TextUtils.isEmpty(msg) ? "null" : msg;
    }

}
