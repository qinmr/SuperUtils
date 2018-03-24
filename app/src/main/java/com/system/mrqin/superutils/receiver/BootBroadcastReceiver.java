package com.system.mrqin.superutils.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.system.mrqin.superutils.activity.MainActivity;

/**
 * Created by Administrator on 2018/3/13.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }

}
