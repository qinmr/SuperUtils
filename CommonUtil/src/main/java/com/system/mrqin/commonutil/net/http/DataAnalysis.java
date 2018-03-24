package com.system.mrqin.commonutil.net.http;


import com.system.mrqin.commonutil.DialogUtil;
import com.system.mrqin.commonutil.R;
import com.system.mrqin.commonutil.StringUtil;
import com.system.mrqin.commonutil.ToastUtil;
import com.system.mrqin.commonutil.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @Description 数据解析
 * @Author 一花一世界
 */

public class DataAnalysis {

    public enum JSON_TYPE {
        /**
         * JSONObject
         */
        JSON_OBJECT,
        /**
         * JSONArray
         */
        JSON_ARRAY,
        /**
         * 不是JSON格式的字符串
         */
        JSON_ERROR
    }

    /**
     * @param result 返回数据
     * @Description 获取result数据格式
     */
    private static JSON_TYPE getJSONType(String result) {
        if (!StringUtil.isNotEmpty(result)) {
            return JSON_TYPE.JSON_ERROR;
        }

        final char[] strChar = result.substring(0, 1).toCharArray();
        final char firstChar = strChar[0];

        if (firstChar == '{') {
            return JSON_TYPE.JSON_OBJECT;
        } else if (firstChar == '[') {
            return JSON_TYPE.JSON_ARRAY;
        } else {
            return JSON_TYPE.JSON_ERROR;
        }
    }

    /**
     * @param result 请求返回字符串
     * @Description 返回数据解析
     */
    public static ResultDesc getReturnData(String result) {
        ResultDesc resultDesc;

        if (!StringUtil.isNotEmpty(result)) {
            //返回数据为空
            resultDesc = dataRestructuring(-1, UIUtils.getString(R.string.back_abnormal_results), "");
            return resultDesc;
        }

        //返回码
        int code = -1;
        //返回说明
        String reason = null;
        //返回数据
        String resultData = null;

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);

            try {
                code = jsonObject.getInt("code");
            } catch (JSONException e) {
                e.printStackTrace();
                code = -1;
            }
            try {
                reason = jsonObject.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
                reason = null;
            }
            try {
                resultData = jsonObject.getString("data");
            } catch (JSONException e) {
                e.printStackTrace();
                resultData = null;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return dataRestructuring(-1, UIUtils.getString(R.string.back_abnormal_results), "");
        }

        if (code == 500) {
            ToastUtil.showText(reason);
            DialogUtil.hideDialogLoading();
        }
        if (code == 404) {
            ToastUtil.showText(reason);
            DialogUtil.hideDialogLoading();
        }
        return dataRestructuring(code, reason, resultData);
    }

    /**
     * @param code   返回码
     * @param reason 返回说明
     * @param result 返回数据
     * @Description 数据重组
     */
    private static ResultDesc dataRestructuring(int code, String reason, String result) {
        return new ResultDesc(code, reason, result);
    }
}
