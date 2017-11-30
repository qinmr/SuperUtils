package com.system.mrqin.commonutil.log.wrapper;

import android.util.Log;

import com.system.mrqin.commonutil.log.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Administrator on 2017/11/28.
 */

public class JsonLog {

    public static void printJson(String tag, String msg) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(LogUtil.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(LogUtil.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        Line.printLine(tag, true);
        message = LogUtil.LINE_SEPARATOR + message;
        String[] lines = message.split(LogUtil.LINE_SEPARATOR);
        for (String line : lines) {
            Log.w(tag, "║ " + line);
        }
        Line.printLine(tag, false);
    }


}
