package com.system.mrqin.superutils.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.system.mrqin.superutils.R;

public class NotificationActivity extends BaseActivity {

    private View view;
    private WindowManager wm;
    private boolean showWm = true;//默认是应该显示悬浮通知栏
    private WindowManager.LayoutParams params;

    @Override
    int getViewLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    void initViews(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(getString(R.string.notification), toolbar, true);
        initWindowManager();
    }

    @Override
    void initEvent() {

    }

    @Override
    void initData() {

    }

    @Override
    void updateViews(boolean isRefresh) {

    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, NotificationActivity.class));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.long_notification:
                showLongNotification();
                break;
            case R.id.common_notification:
                showCommonNotification();
                break;
            case R.id.hanging_notification:
                createFloatView(getString(R.string.hanging_notification));
                break;
        }
    }

    private void showCommonNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(getString(R.string.common_notification));
        builder.setContentText(getString(R.string.common_notification));
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0));
        builder.setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, builder.build());
    }

    public void showLongNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(getString(R.string.long_notification));
        builder.setContentText(getString(R.string.long_notification));
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0));
        builder.setAutoCancel(false);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(2, builder.build());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showChannelNotification() {
        PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, null);
//        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher));
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setPriority(Notification.PRIORITY_DEFAULT);
//        builder.setContentTitle(getString(R.string.hanging_notification));
//        builder.setContentText(getString(R.string.hanging_notification));
//        builder.setContentIntent(intent);
//        builder.setAutoCancel(true);
//        builder.setFullScreenIntent(intent, true);
//        builder.setVisibility(Notification.VISIBILITY_PUBLIC);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//        notificationManagerCompat.notify(3, builder.build());


        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(intent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle(getString(R.string.hanging_notification));
        builder.setContentText(getString(R.string.hanging_notification));
        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        builder.setFullScreenIntent(intent, true);
        notificationManagerCompat.notify(3, builder.build());

    }

    private void initWindowManager() {
        wm = (WindowManager) getApplicationContext().getSystemService(
                Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        //注意是TYPE_SYSTEM_ERROR而不是TYPE_SYSTEM_ALERT
        //前面有SYSTEM才可以遮挡状态栏，不然的话只能在状态栏下显示通知栏
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        params.format = PixelFormat.TRANSPARENT;
        //设置必须触摸通知栏才可以关掉
        params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

        // 设置通知栏的长和宽
        params.width = wm.getDefaultDisplay().getWidth();
        params.height = 200;
        params.gravity = Gravity.TOP;
    }

//    //Android 2.0 - 2.3.7 PhoneWindowManager
//    public void adjustWindowParamsLw(WindowManager.LayoutParams attrs) {
//        switch (attrs.type) {
//            case WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY:
//            case WindowManager.LayoutParams.TYPE_SECURE_SYSTEM_OVERLAY:
//            case WindowManager.LayoutParams.TYPE_TOAST:
//                // These types of windows can't receive input events.
//                attrs.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
//                break;
//        }
//    }
//
//    //Android 4.0.1 - 4.3.1 PhoneWindowManager
//    public void adjustWindowParamsLw(WindowManager.LayoutParams attrs) {
//        switch (attrs.type) {
//            case WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY:
//            case WindowManager.LayoutParams.TYPE_SECURE_SYSTEM_OVERLAY:
//            case WindowManager.LayoutParams.TYPE_TOAST:
//                // These types of windows can't receive input events.
//                attrs.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
//                attrs.flags &= ~WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
//                break;
//        }
//    }
//
//
//    //Android 4.4 PhoneWindowManager
//    public void adjustWindowParamsLw(WindowManager.LayoutParams attrs) {
//        switch (attrs.type) {
//            case WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY:
//            case WindowManager.LayoutParams.TYPE_SECURE_SYSTEM_OVERLAY:
//                // These types of windows can't receive input events.
//                attrs.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
//                attrs.flags &= ~WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
//                break;
//        }
//    }

    private void createFloatView(String str) {
        view = LayoutInflater.from(this).inflate(R.layout.wechat, null);
        //在这里你可以解析你的自定义的布局成一个View
        ((TextView) (view.findViewById(R.id.tv_message))).setText(str);
        if (showWm) {
            wm.addView(view, params);
            showWm = false;
        } else {
            wm.updateViewLayout(view, params);
        }
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        wm.removeViewImmediate(view);
                        view = null;
                        break;
                }
                return true;
            }
        });

    }


}
