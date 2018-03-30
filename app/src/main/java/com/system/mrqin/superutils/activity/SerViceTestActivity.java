package com.system.mrqin.superutils.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.system.mrqin.commonutil.logs.KLog;
import com.system.mrqin.superutils.R;
import com.system.mrqin.superutils.service.MyService;

public class SerViceTestActivity extends BaseActivity {

    private static final int MSG_DOWNLOAD = 0x110;
    private static final int MSG_PAUSE = 0x111;
    private static final int MSG_STOP = 0x112;

    private Messenger mServerMessenger;

    ServiceConnection mConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServerMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServerMessenger = null;
        }
    };


    @Override
    int getViewLayoutId() {
        return R.layout.activity_ser_vice_test;
    }

    @Override
    void initViews(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(getString(R.string.service_test), toolbar, true);
    }

    @Override
    void initEvent() {
        Intent intent = new Intent(mContext, MyService.class);
        startService(intent);
        bindService(intent, mConn, BIND_AUTO_CREATE);
    }

    @Override
    void initData() {

    }

    @Override
    void updateViews(boolean isRefresh) {

    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, SerViceTestActivity.class));
    }

    public void onClick(View view) {
        Message msg = Message.obtain();
        try {
            switch (view.getId()) {
                case R.id.btn_download:
                    msg.what = MSG_DOWNLOAD;
                    msg.replyTo = mMessenger;
                    mServerMessenger.send(msg);
                    break;
                case R.id.btn_pause:
                    msg.what = MSG_PAUSE;
                    msg.replyTo = mMessenger;
                    mServerMessenger.send(msg);
                    break;
                case R.id.btn_stop:
                    msg.what = MSG_STOP;
                    msg.replyTo = mMessenger;
                    mServerMessenger.send(msg);
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Messenger mMessenger = new Messenger(new LocalHandler());

    private static class LocalHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //msg 服务端传来的消息
                case MSG_DOWNLOAD:
                    KLog.e(getClass().getSimpleName(), msg.obj.toString());
                    break;
                case MSG_PAUSE:
                    KLog.e(getClass().getSimpleName(), msg.obj.toString());
                    break;
                case MSG_STOP:
                    KLog.e(getClass().getSimpleName(), msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
    }
}
