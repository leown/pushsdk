package com.coohua.pushsdk.client.ali;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.coohua.pushsdk.core.HPushMessage;
import com.coohua.pushsdk.core.util.JsonUtils;

import java.util.Map;


/**
 * @author Leo Wang
 * 2017-8-14
 * desc:
 */
public class AliPushMsgReceiver extends MessageReceiver {
    private static final String TAG = "AliPushMsgReceiver";

    /**
     * 接收服务器推送的消息
     *
     * @param context      上下文环境
     * @param cPushMessage 可以获取消息Id、消息标题和内容
     */
    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        if (AliPushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setContent(cPushMessage.getContent());
            message.setPlatform(AliPushManager.NAME);
            AliPushManager.hMessageProvider.onReceivePassThroughMessage(context, message);
        }
    }


    /**
     * 客户端接收到通知后，回调该方法。
     *
     * @param context  上下文环境
     * @param title    通知标题
     * @param summary  通知内容
     * @param extraMap 通知额外参数，包括部分系统自带参数：
     */
    @Override
    protected void onNotification(Context context, String title, String summary, Map<String,
            String> extraMap) {
        Log.i(TAG, "onNotification title " + title + "summary " + summary + " " +
                "extraMap " + extraMap.toString());

        if (AliPushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setPlatform(AliPushManager.NAME);
            message.setTitle(title);
            message.setDescription(summary);
            message.setContent(summary);
            if (!extraMap.isEmpty()) {
                try {
                    String json = JsonUtils.setJson(extraMap).toString();
                    message.setExtraMapStr(json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            AliPushManager.hMessageProvider.onNotificationMessageArrived(context, message);
        }
    }

    @Override
    protected void onNotificationOpened(Context context, String title, String summary,
                                        String extraMap) {

        Log.i(TAG, "onNotificationOpened title " + title + "summary " + summary + " " +
                "extraMap " + extraMap);

        if (AliPushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setPlatform(AliPushManager.NAME);
            message.setTitle(title);
            message.setDescription(summary);
            message.setContent(summary);
            message.setExtraMapStr(extraMap);
            AliPushManager.hMessageProvider.onNotificationMessageClicked(context, message);
        }
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        Log.d(TAG, "onNotificationRemoved messageId ---> " + messageId);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary,
                                               Map<String, String> extraMap, int openType,
                                               String openActivity, String openUrl) {
        Log.i(TAG, "onNotificationReceivedInApp title " + title + "summary " + summary + " " +
                "extraMap " + extraMap + " openType " + openType + " openActivity " +
                openActivity + " openUrl " + openUrl);
    }
}