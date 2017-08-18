package com.coohua.client.huawei;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.coohua.pushsdk.core.HPushMessage;
import com.coohua.pushsdk.core.util.JsonUtils;
import com.huawei.hms.support.api.push.PushReceiver;

import java.io.UnsupportedEncodingException;

/**
 * @author Leo Wang
 * 2017/8/17
 * <p>
 * 应用需要创建一个子类继承com.huawei.hms.support.api.push.PushReceiver，
 * 实现onToken，onPushState ，onPushMsg，onEvent，这几个抽象方法，用来接收token返回，push连接状态，透传消息和通知栏点击事件处理。
 * onToken 调用getToken方法后，获取服务端返回的token结果，返回token以及belongId
 * onPushState 调用getPushState方法后，获取push连接状态的查询结果
 * onPushMsg 推送消息下来时会自动回调onPushMsg方法实现应用透传消息处理。本接口必须被实现。 在开发者网站上发送push消息分为通知和透传消息
 * 通知为直接在通知栏收到通知，通过点击可以打开网页，应用 或者富媒体，不会收到onPushMsg消息
 * 透传消息不会展示在通知栏，应用会收到onPushMsg
 * onEvent 该方法会在设置标签、点击打开通知栏消息、点击通知栏上的按钮之后被调用。由业务决定是否调用该函数。
 */


public class HuaweiPushReceiver extends PushReceiver {
    public static final String TAG = "HuaweiPushRevicer";

    @Override
    public void onToken(Context context, String token, Bundle extras) {
        String belongId = extras.getString("belongId");
        Log.d(TAG, "belongId为:" + belongId);
        Log.d(TAG, "Token为:" + token);
    }

    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
        //通知栏消息到达回调
        Log.i(TAG, "onNotificationArrived content" + new String(msg));

        if (HuaweiPushManager.hMessageProvider != null) {
            try {
                String content = new String(msg, "UTF-8");
                HPushMessage message = new HPushMessage();
                message.setPlatform(HuaweiPushManager.NAME);
                message.setContent(content);
                HuaweiPushManager.hMessageProvider.onNotificationMessageArrived(context, message);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void onEvent(Context context, Event event, Bundle extras) {
        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {

            String content = extras.getString(BOUND_KEY.pushMsgKey);
            String jsonExtra = JsonUtils.getJson(extras);
            //通知栏消息点击回调
            Log.i(TAG, "onEvent content " + content + " jsonExtra " + jsonExtra);

            if (HuaweiPushManager.hMessageProvider != null) {
                HPushMessage message = new HPushMessage();
                message.setPlatform(HuaweiPushManager.NAME);
                message.setContent(content);
                message.setExtraMapStr(jsonExtra);
                HuaweiPushManager.hMessageProvider.onNotificationMessageClicked(context, message);
            }
        }

        super.onEvent(context, event, extras);
    }

    @Override
    public void onPushState(Context context, boolean pushState) {
        Log.i("TAG", "Push连接状态为:" + pushState);
    }
}
