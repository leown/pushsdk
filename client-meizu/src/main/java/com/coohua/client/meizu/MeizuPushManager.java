package com.coohua.client.meizu;

import android.content.Context;

import com.coohua.pushsdk.core.HMessageProvider;
import com.coohua.pushsdk.core.HPushManager;
import com.coohua.pushsdk.core.util.Const;
import com.meizu.cloud.pushsdk.PushManager;

/**
 * @author Leo Wang
 * 2017/8/14
 * desc: 魅族推送只支持Flyme系统，务必需要注意
 */

public class MeizuPushManager implements HPushManager {
    public static final String NAME = Const.MEIZU_PUSH;
    public static HMessageProvider hMessageProvider;

    private String appId;
    private String appKey;

    public MeizuPushManager(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    @Override
    public void registerPush(Context context) {
        PushManager.register(context, appId, appKey);
    }

    @Override
    public void unRegisterPush(Context context) {
        PushManager.unRegister(context, appId, appKey);
    }

    @Override
    public void setAlias(Context context, String alias) {
        PushManager.subScribeAlias(context, appId, appKey, PushManager.getPushId(context), alias);
    }

    @Override
    public void unsetAlias(Context context, String alias) {
        PushManager.unSubScribeAlias(context, appId, appKey, PushManager.getPushId(context), alias);
    }

    @Override
    public void setTags(Context context, String... tags) {
        for (String tag : tags) {
            PushManager.subScribeTags(context, appId, appKey, PushManager.getPushId(context), tag);
        }
    }

    @Override
    public void unsetTags(Context context, String... tags) {
        for (String tag : tags) {
            PushManager.unSubScribeTags(context, appId, appKey, PushManager.getPushId(context),
                    tag);
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
