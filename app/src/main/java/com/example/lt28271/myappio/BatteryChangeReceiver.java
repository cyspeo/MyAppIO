package com.example.lt28271.myappio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryChangeReceiver extends BroadcastReceiver {


    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
                Util.onBatteryLow(context);
            } else if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                if (level > 80 && Util.isSwitchOn()) {
                    Util.onBatteryHigh(context);
                }
            }
        }
        catch (NullPointerException npe) {

        }
    }
}
