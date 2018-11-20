
package com.reactlibrary;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

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
        }catch (Exception e){
            promise.reject(e);
        }
    }

    @Override
    public String getName() {
        return "RNReactNativePing";
    }
}