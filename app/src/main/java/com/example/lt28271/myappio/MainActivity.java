package com.example.lt28271.myappio;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private BatteryChangeReceiver receiver = new BatteryChangeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // init preferences
        readPref();

        try {
            IntentFilter ifilter = new IntentFilter();
            ifilter.addAction(Intent.ACTION_BATTERY_CHANGED);
            ifilter.addAction(Intent.ACTION_BATTERY_LOW);
            this.registerReceiver(this.receiver, ifilter);
        }
        catch (Exception e) {
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.receiver);
    }

    public void onMajClicked(View view) {
        EditText etUrl = (EditText) findViewById(R.id.etUrl);
        Util.hostname = etUrl.getText().toString();
        EditText etDeviceName = (EditText) findViewById(R.id.et_device_name);
        Util.deviceName = etDeviceName.getText().toString();
        CheckBox batCheck = (CheckBox) findViewById(R.id.batteryCheck);
        Util.batteryCheck = batCheck.isChecked();

        savePref();
    }


    private void readPref() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        Util.batteryCheck = sharedPref.getBoolean("pref_battery_check_key", true);
        Util.hostname = sharedPref.getString("pref_url_key", "");
        Util.deviceName = sharedPref.getString("pref_deviceName_key", "");
    }

    private void savePref() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("pref_battery_check_key", Util.batteryCheck);
        editor.putString("pref_url_key", Util.hostname);
        editor.putString("pref_deviceName_key", Util.deviceName);
        editor.commit();
    }

    public void onBatteryLowClicked(View view) {
        Util.onBatteryLow(view.getContext());
    }

    public void onBatteryHighClicked(View view) {
        Util.onBatteryHigh(view.getContext());
    }
}
