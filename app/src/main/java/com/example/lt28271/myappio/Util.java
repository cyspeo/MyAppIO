package com.example.lt28271.myappio;

import android.content.Context;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;


public class Util {
    private static boolean switchOn;
    public static boolean batteryCheck = true;
    public static String hostname;
    public static String deviceName;

    public static void onBatteryLow(Context context) {
        try {
            DomoboxRestClient.post( hostname + deviceName + "/batteryLow", null, new AsyncHttpResponseHandler() {
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    // called when response HTTP status is "200 OK"
                    Util.switchOn = true;
                }
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Util.switchOn = false;
                }
            });
        }
        catch (Exception ex) {

        }
    }

    public static void onBatteryHigh(Context context) {
        try {
            DomoboxRestClient.post( hostname + deviceName +"/batteryHigh", null, new AsyncHttpResponseHandler() {
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    // called when response HTTP status is "200 OK"
                    Util.switchOn = false;
                }
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Util.switchOn = true;
                }
            });
        }
        catch (Exception ex) {
        }
    }

    public static boolean isSwitchOn() {
        return switchOn;
    }

}
