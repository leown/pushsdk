package com.coohua.pushsdk.client.ali;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.coohua.pushsdk.core.HMessageProvider;
import com.coohua.pushsdk.core.HPushManager;
import com.coohua.pushsdk.core.util.Const;

/**
 * @author Leo Wang
 * 2017/8/16
 * desc:
 */


public class AliPushManager implements HPushManager {
    public static final String TAG = "AliPushManager";
    public static final String NAME = "aliPush";
    public static HMessageProvider hMessageProvider;
    private CloudPushService cloudPushService;
    private String appSecret;
    private String appKey;

    public AliPushManager(Context context, String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    @Override
    public void registerPush(Context context) {
        PushServiceFactory.init(context.getApplicationContext());
        cloudPushService = PushServiceFactory.getCloudPushService();
        cloudPushService.register(context.getApplicationContext(),
                appKey, appSecret, new
                        CommonCallback() {

                            @Override
                            public void onSuccess(String response) {
                                Log.d(TAG, "register onSuccess --> " + response);
                            }

                            @Override
                            public void onFailed(String errorCode, String errorMessage) {
                                Log.d(TAG, "register onFailed --> errorCode " + errorCode + " " +
                                        "errorMessage " + errorMessage);

                            }
                        });
    }

    @Override
    public void unRegisterPush(Context context) {
        if (cloudPushService == null) return;
        unsetAlias(context, null);
        hMessageProvider = null;
        cloudPushService.turnOffPushChannel(null);
    }

    @Override
    public void setAlias(Context context, String alias) {
        if (cloudPushService == null) return;
        cloudPushService.addAlias(alias, null);
    }

    @Override
    public void unsetAlias(Context context, String alias) {
        if (cloudPushService == null) return;

        cloudPushService.removeAlias(alias, null);

    }

    @Override
    public void setTags(Context context, String... tags) {
        if (cloudPushService == null) return;

        cloudPushService.bindTag(1, tags, "", null);
    }

    @Override
    public void unsetTags(Context context, String... tags) {
        if (cloudPushService == null) return;

        cloudPushService.unbindTag(1, tags, "", null);
    }

    @Override
    public void bindAccount(Context context, String account) {
        if (cloudPushService == null) return;

        cloudPushService.bindAccount(account, null);
    }

    @Override
    public void unbindAccount(Context context, String account) {
        if (cloudPushService == null) return;

        cloudPushService.unbindAccount(null);
    }

    @Override
    public String getPushName() {
        return NAME;
    }

    @Override
    public void setMessageProvider(HMessageProvider provider) {
        hMessageProvider = provider;
    }

    @Override
    public void disable(Context context) {
        unRegisterPush(context);
    }
}
