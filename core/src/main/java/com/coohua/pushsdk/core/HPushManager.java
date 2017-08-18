package com.coohua.pushsdk.core;

import android.content.Context;

import com.coohua.pushsdk.core.util.Const;

public interface HPushManager {

    void registerPush(Context context);

    void unRegisterPush(Context context);

    void setAlias(Context context, String alias);

    void unsetAlias(Context context, String alias);

    void setTags(Context context, String... tags);

    void unsetTags(Context context, String... tags);

    void bindAccount(Context context, String account);

    void unbindAccount(Context context, String account);

    String getPushName();

    void setMessageProvider(HMessageProvider provider);

    //如果从小米推送->小米&个推，所以上线新版可能会导致收到2个平台的推送，所以没有用得平台必须让其失效（取消注册）。
    void disable(Context context);

}
