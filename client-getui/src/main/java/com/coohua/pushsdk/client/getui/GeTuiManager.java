package com.coohua.pushsdk.client.getui;

import android.content.Context;

import com.coohua.pushsdk.core.HMessageProvider;
import com.coohua.pushsdk.core.HPushManager;
import com.coohua.pushsdk.core.util.Const;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.Tag;

/**
 * Created by Wiki on 2017/6/1.
 */

public class GeTuiManager implements HPushManager {
    public static final String NAME = Const.GETUI_PUSH;

    public static HMessageProvider hMessageProvider;

    @Override
    public void registerPush(Context context) {
        PushManager.getInstance().initialize(context, null);
        PushManager.getInstance().registerPushIntentService(context, GeTuiMessageIntentService.class);
    }

    @Override
    public void unRegisterPush(Context context) {
        PushManager.getInstance().stopService(context);
    }


    @Override
    public void setAlias(Context context, String alias) {
        PushManager.getInstance().bindAlias(context, alias);
    }

    @Override
    public void unsetAlias(Context context, String alias) {
        PushManager.getInstance().unBindAlias(context, alias, false);
    }

    @Override
    public void setTags(Context context, String... tags) {
        Tag[] temps = new Tag[tags.length];
        for (int i = 0; i < tags.length; i++) {
            Tag tag = new Tag();
            tag.setName(tags[i]);
            temps[i] = tag;
        }
        PushManager.getInstance().setTag(context, temps, null);
    }

    @Override
    public void unsetTags(Context context, String... tags) {
        PushManager.getInstance().setTag(context, new Tag[0], null);
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
