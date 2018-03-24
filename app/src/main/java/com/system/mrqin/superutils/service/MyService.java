package com.system.mrqin.superutils.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.system.mrqin.commonutil.logs.KLog;

/**
 * Created by Administrator on 2018/3/3.
 *
 */

public class MyService extends Service {

    private static final int MSG_DOWNLOAD = 0x110;
    private static final int MSG_PAUSE = 0x111;
    private static final int MSG_STOP = 0x112;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mServerMessenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private Messenger mServerMessenger = new Messenger(new LocalHandler());

    private static class LocalHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Message msgToClient = Message.obtain(msg);//返回给客户端的消息
            switch (msg.what) {
                //msg 客户端传来的消息
                case MSG_DOWNLOAD:
                    msgToClient.what = MSG_DOWNLOAD;
                    msgToClient.obj = "download return";
                    try {
                        msg.replyTo.send(msgToClient);
                        KLog.e(getClass().getSimpleName(), "download");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case MSG_PAUSE:
                    msgToClient.what = MSG_PAUSE;
                    msgToClient.obj = "pause return";
                    try {
                        msg.replyTo.send(msgToClient);
                        KLog.e(getClass().getSimpleName(), "pause");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case MSG_STOP:
                    msgToClient.what = MSG_STOP;
                    msgToClient.obj = "stop return";
                    try {
                        msg.replyTo.send(msgToClient);
                        KLog.e(getClass().getSimpleName(), "stop");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }


}
