package com.coohua.pushsdk.core;

import android.content.Context;

/**
 * @author Leo Wang
 * 2017/8/14
 * desc:
 */

public interface HMessageProvider {
    /**
     * 透传
     */
    public void onReceivePassThroughMessage(Context context, HPushMessage message);

    /**
     * 通知栏消息点击
     */
    public void onNotificationMessageClicked(Context context, HPushMessage message);

    /**
     * 通知栏消息到达
     */
    public void onNotificationMessageArrived(Context context, HPushMessage message);

//    public void onError(Context context, HPushMessage message);
}
