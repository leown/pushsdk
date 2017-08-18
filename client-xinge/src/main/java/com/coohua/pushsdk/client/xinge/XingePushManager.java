package com.coohua.pushsdk.client.xinge;

import android.content.Context;

import com.coohua.pushsdk.core.HMessageProvider;
import com.coohua.pushsdk.core.HPushManager;
import com.coohua.pushsdk.core.util.Const;
import com.tencent.android.tpush.XGPushManager;

/**
 * @author Leo Wang
 * 2017/8/14
 * desc:
 */


public class XingePushManager implements HPushManager {
    public static final String NAME = Const.XINGE_PUSH;
    public static HMessageProvider hMessageProvider;

    public XingePushManager() {
    }

    @Override
    public void registerPush(Context context) {
        XGPushManager.registerPush(context.getApplicationContext());
    }

    @Override
    public void unRegisterPush(Context context) {
        unsetAlias(context, null);
        hMessageProvider = null;
        XGPushManager.unregisterPush(context.getApplicationContext());
    }

    @Override
    public void setAlias(Context context, String alias) {
    }

    @Override
    public void unsetAlias(Context context, String alias) {
    }

    @Override
    public void setTags(Context context, String... tags) {
        for (String tag : tags) {
            XGPushManager.setTag(context, tag);
        }

    }

    @Override
    public void unsetTags(Context context, String... tags) {
        for (String tag : tags) {
            XGPushManager.deleteTag(context, tag);
        }
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
