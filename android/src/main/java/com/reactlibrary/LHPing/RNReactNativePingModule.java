
package com.reactlibrary.LHPing;

import android.net.TrafficStats;
import android.os.Handler;
import android.os.HandlerThread;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
public class RNReactNativePingModule extends ReactContextBaseJavaModule {
    private final String TIMEOUT_KEY = "timeout";
    private final ReactApplicationContext reactContext;
    HandlerThread handlerThread = new HandlerThread("HandlerThread");

    public RNReactNativePingModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        handlerThread.start();
    }

    @ReactMethod
    public void start(final String ipAddress, ReadableMap option, final Promise promise) {
        if (ipAddress == null || (ipAddress != null && ipAddress.length() == 0)) {
            LHDefinition.PING_ERROR_CODE error = LHDefinition.PING_ERROR_CODE.HostErrorNotSetHost;
            promise.reject(error.getCode(), error.getMessage());
            return;
        }

        final boolean[] isFinish = {false};
        int timeout = 1000;
        if (option.hasKey(TIMEOUT_KEY)) {
            timeout = option.getInt(TIMEOUT_KEY);
        }
        final int finalTimeout = timeout;

        Handler mHandler = new Handler(handlerThread.getLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (isFinish[0]) {
                        return;//Prevent multiple calls
                    }
                    int rtt = PingUtil.getAvgRTT(ipAddress, 1, finalTimeout);
                    promise.resolve(Integer.valueOf(rtt));
                    isFinish[0] = true;
                } catch (Exception e) {
                    if (isFinish[0]) {//Prevent multiple calls
                        return;
                    }
                    LHDefinition.PING_ERROR_CODE error =
                            LHDefinition.PING_ERROR_CODE.HostErrorUnknown;
                    promise.reject(error.getCode(), error.getMessage());
                    isFinish[0] = true;
                }
            }
        });

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFinish[0]) {//Prevent multiple calls
                    return;
                }
                LHDefinition.PING_ERROR_CODE error = LHDefinition.PING_ERROR_CODE.Timeout;
                promise.reject(error.getCode(), error.getMessage());
                isFinish[0] = true;
            }
        }, timeout);

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

                String receivedNetworkSpeed = bytesToAvaiUnit(newReceivedTotal - receiveTotal) +
                        "/s";
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