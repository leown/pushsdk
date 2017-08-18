package com.coohua.client.baidu;

import android.content.Context;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.coohua.pushsdk.core.HMessageProvider;
import com.coohua.pushsdk.core.HPushManager;
import com.coohua.pushsdk.core.util.Const;

import java.util.Arrays;

/**
 * @author Leo Wang
 * 2017/8/14
 * desc:
 */


public class BaiduPushManager implements HPushManager {
    public static final String NAME = Const.BAIDU_PUSH;
    public static HMessageProvider hMessageProvider;
    private String appKey;

    public BaiduPushManager(String appKey) {
        this.appKey = appKey;
    }


    @Override
    public void registerPush(Context context) {
        PushManager.startWork(context.getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY,
                appKey);
    }

    @Override
    public void unRegisterPush(Context context) {
        unsetAlias(context, null);
        hMessageProvider = null;
        PushManager.stopWork(context.getApplicationContext());
    }

    @Override
    public void setAlias(Context context, String alias) {
    }

    @Override
    public void unsetAlias(Context context, String alias) {

    }

    @Override
    public void setTags(Context context, String... tags) {
        PushManager.setTags(context.getApplicationContext(), Arrays.asList(tags));
    }

    @Override
    public void unsetTags(Context context, String... tags) {
        PushManager.delTags(context.getApplicationContext(), Arrays.asList(tags));
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
        hMessageProvider = provider;
    }

    @Override
    public void disable(Context context) {
        unRegisterPush(context);
    }
}
