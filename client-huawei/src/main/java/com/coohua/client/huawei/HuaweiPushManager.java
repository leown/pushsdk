package com.coohua.client.huawei;

import android.content.Context;
import android.util.Log;

import com.coohua.pushsdk.core.HMessageProvider;
import com.coohua.pushsdk.core.HPushManager;
import com.coohua.pushsdk.core.util.Const;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.push.HuaweiPush;

/**
 * @author Leo Wang
 * 2017/8/14
 * desc:
 */


public class HuaweiPushManager implements HPushManager, HuaweiApiClient.ConnectionCallbacks,
        HuaweiApiClient.OnConnectionFailedListener {
    public static final String TAG = "HuaweiPushManager";
    public static final String NAME = Const.HUAWEI_PUSH;
    public static HMessageProvider hMessageProvider;
    //华为移动服务Client
    private HuaweiApiClient client;

    public HuaweiPushManager(Context context) {
        client = new HuaweiApiClient.Builder(context.getApplicationContext())
                .addApi(HuaweiPush.PUSH_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void registerPush(Context context) {
        if (client == null) return;
        if (!client.isConnected())
            client.connect();
        client.connect();
    }

    @Override
    public void unRegisterPush(Context context) {
        if (client == null) return;
        if (client.isConnected())
            client.disconnect();
    }

    @Override
    public void setAlias(Context context, String alias) {
    }

    @Override
    public void unsetAlias(Context context, String alias) {

    }

    @Override
    public void setTags(Context context, String... tags) {

    }

    @Override
    public void unsetTags(Context context, String... tags) {

    }

    @Override
    public void bindAccount(Context context, String account) {

    }

    @Override
    public void unbindAccount(Context context, String account) {

    }

    @Override
    public String getPushName() {
        return NAME;
    }

    @Override
    public void setMessageProvider(HMessageProvider provider) {

    }

    @Override
    public void disable(Context context) {
        unRegisterPush(context);
    }

    @Override
    public void onConnected() {
        //华为移动服务client连接成功，在这边处理业务自己的事件
        Log.i(TAG, "HuaweiApiClient 连接成功");
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (client == null) return;
        //HuaweiApiClient异常断开连接, if 括号里的条件可以根据需要修改
        client.connect();
        Log.i(TAG, "HuaweiApiClient 连接断开 异常");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "HuaweiApiClient连接失败，错误码：" + connectionResult.getErrorCode());
    }
}
