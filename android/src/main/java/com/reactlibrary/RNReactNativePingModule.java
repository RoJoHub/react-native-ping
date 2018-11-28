
package com.reactlibrary;

import android.net.TrafficStats;
import android.os.Handler;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;

public class RNReactNativePingModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNReactNativePingModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @ReactMethod
    public void start(String ipAddress, Promise promise) {
        try {
            int rtt = PingUtil.getAvgRTT("http://" + ipAddress);
            promise.resolve(Integer.valueOf(rtt));
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getTrafficStats(final Promise promise) {
        final long receiveTotal = TrafficStats.getTotalRxBytes();
        final long sendTotal = TrafficStats.getTotalTxBytes();
        final String receivedNetworkTotal = bytesToAvaiUnit(receiveTotal);
        final String sendNetworkTotal = bytesToAvaiUnit(sendTotal);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                long newReceivedTotal = TrafficStats.getTotalRxBytes();
                long newSendTotal = TrafficStats.getTotalTxBytes();

                String receivedNetworkSpeed = bytesToAvaiUnit(newReceivedTotal - receiveTotal) + "/s";
                String sendNetworkSpeed = bytesToAvaiUnit(newSendTotal - sendTotal) + "/s";
                WritableMap map = Arguments.createMap();

                map.putString("receivedNetworkTotal", receivedNetworkTotal);
                map.putString("sendNetworkTotal", sendNetworkTotal);
                map.putString("receivedNetworkSpeed", receivedNetworkSpeed);
                map.putString("sendNetworkSpeed", sendNetworkSpeed);

                promise.resolve(map);
            }
        }, 1000);

    }

    String bytesToAvaiUnit(long bytes) {

        if (bytes < 1024) {   // B
            return bytes + "B";
        } else if (bytes >= 1024 && bytes < 1024 * 1024) { // KB
            return String.format("%.1fKB", bytes / 1024.0);
        } else if (bytes >= 1024 * 1024 && bytes < 1024 * 1024 * 1024) { // MB
            return String.format("%.1fMB", bytes / (1024 * 1024.0));
        } else { // GB
            return String.format("%.1fGB", bytes / (1024 * 1024 * 1024.0));
        }
    }

    @Override
    public String getName() {
        return "RNReactNativePing";
    }
}