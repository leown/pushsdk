package com.coohua.client.baidu;

import android.content.Context;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.coohua.pushsdk.core.HPushMessage;

import java.util.List;

/**
 * @author Leo Wang
 * 2017/8/14
 * desc:
 */


public class BaiduPushMessageReceiver extends PushMessageReceiver {
    private static final String TAG = "BDPushMessageReceiver";

    @Override
    public void onBind(Context context, int errorCode, String appid,
                       String userId, String channelId, String requestId) {
        String responseString = "onBind errorCode=" + errorCode + " appid="
                + appid + " userId=" + userId + " channelId=" + channelId
                + " requestId=" + requestId;
        if (errorCode == 0)
            Log.d(TAG, "绑定成功");
        Log.d(TAG, "onBind ---> " + responseString);
    }

    @Override
    public void onUnbind(Context context, int errorCode, String requestId) {
        String responseString = "onUnbind errorCode=" + errorCode
                + " requestId = " + requestId;
        if (errorCode == 0) {
            // 解绑定成功
            Log.d(TAG, "解绑成功");
        }
        Log.d(TAG, responseString);
    }

    @Override
    public void onSetTags(Context context, int errorCode,
                          List<String> successTags, List<String> failTags, String requestId) {
        String responseString = "onSetTags errorCode=" + errorCode
                + " successTags=" + successTags + " failTags=" + failTags
                + " requestId=" + requestId;
        Log.d(TAG, responseString);
    }

    @Override
    public void onDelTags(Context context, int errorCode,
                          List<String> successTags, List<String> failTags, String requestId) {
        String responseString = "onDelTags errorCode=" + errorCode
                + " successTags=" + successTags + " failTags=" + failTags
                + " requestId=" + requestId;
        Log.d(TAG, responseString);
    }

    @Override
    public void onListTags(Context context, int errorCode, List<String> tags,
                           String requestId) {
        String responseString = "onListTags errorCode=" + errorCode + " tags="
                + tags;
        Log.d(TAG, responseString);
    }

    @Override
    public void onMessage(Context context, String messageStr,
                          String customContentString) {
        String messageString = "透传消息 messageStr=\"" + messageStr
                + "\" customContentString=" + customContentString;
        Log.d(TAG, messageString);

        //接收服务器推送的透传消息
        if (BaiduPushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setContent(messageStr);
            message.setPlatform(BaiduPushManager.NAME);
            BaiduPushManager.hMessageProvider.onReceivePassThroughMessage(context, message);
        }
    }

    @Override
    public void onNotificationClicked(Context context, String title,
                                      String description, String customContentString) {
        String notifyString = "通知点击 onNotificationClicked title=\"" + title + "\" description=\""
                + description + "\" customContent=" + customContentString;
        Log.d(TAG, notifyString);

        if (BaiduPushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setPlatform(BaiduPushManager.NAME);
            message.setTitle(title);
            message.setDescription(description);
            message.setContent(customContentString);
            BaiduPushManager.hMessageProvider.onNotificationMessageArrived(context, message);
        }
    }

    @Override
    public void onNotificationArrived(Context context, String title,
                                      String description, String customContentString) {
        String notifyString = "通知到达 onNotificationArrived  title=\"" + title
                + "\" description=\"" + description + "\" customContent="
                + customContentString;
        Log.d(TAG, notifyString);


        if (BaiduPushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setPlatform(BaiduPushManager.NAME);
            message.setTitle(title);
            message.setDescription(description);
            message.setContent(customContentString);
            BaiduPushManager.hMessageProvider.onNotificationMessageArrived(context, message);
        }
    }
}
