package com.system.mrqin.commonutil;

import android.content.Context;

import com.system.mrqin.commonutil.widget.ShapeLoadingDialog;

/**
 * @Description Dialog工具类
 * @Author 一花一世界
 */
public class DialogUtil {

    private static ShapeLoadingDialog shapeLoadingDialog;

    /**
     * @Description 显示加载状态框
     */
    public static void showDialogLoading(Context context, String message) {
        if (shapeLoadingDialog != null) {
            hideDialogLoading();
            shapeLoadingDialog = new ShapeLoadingDialog(context);
        } else {
            shapeLoadingDialog = new ShapeLoadingDialog(context);
        }

        if (StringUtil.isNotEmpty(message)) {
            shapeLoadingDialog.setLoadingText(message);
        } else {
            shapeLoadingDialog.setLoadingText(UIUtils.getString(R.string.loading_desc));
        }

        shapeLoadingDialog.setCanceledOnTouchOutside(false);
        shapeLoadingDialog.show();
    }

    /**
     * @Description 关闭加载框
     */
    public static void hideDialogLoading() {
        if (shapeLoadingDialog != null) {
            if (shapeLoadingDialog.isShowing()) {
                shapeLoadingDialog.dismiss();
            }
            shapeLoadingDialog = null;
        }
    }
}
