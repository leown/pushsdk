package com.coohua.pushsdk.client.xinge;

import android.content.Context;
import android.util.Log;

import com.coohua.pushsdk.core.HPushMessage;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * @author Leo Wang
 * 2017/8/14
 * desc:
 */


public class XingePushMessageReceiver extends XGPushBaseReceiver {

    private static final String TAG = "XGPushMessageReceiver";

    @Override
    public void onRegisterResult(Context context, int errorCode, XGPushRegisterResult
            xgPushRegisterResult) {

        if (errorCode == XingePushMessageReceiver.SUCCESS)
            Log.d(TAG, "绑定成功");
        Log.d(TAG, "onRegisterResult ---> " + xgPushRegisterResult.toString());
    }

    @Override
    public void onUnregisterResult(Context context, int errorCode) {
        if (errorCode == XingePushMessageReceiver.SUCCESS)
            Log.d(TAG, "解绑成功");
    }

    @Override
    public void onSetTagResult(Context context, int errorCode, String tagName) {
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "\"" + tagName + "\"设置成功";
        } else {
            text = "\"" + tagName + "\"设置失败,错误码：" + errorCode;
        }
        Log.d(TAG, text);
    }

    @Override
    public void onDeleteTagResult(Context context, int errorCode, String tagName) {
        String text = "";
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            text = "\"" + tagName + "\"删除成功";
        } else {
            text = "\"" + tagName + "\"删除失败,错误码：" + errorCode;
        }
        Log.d(TAG, text);
    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {

        Log.d(TAG, "收到消息：\" " + xgPushTextMessage.toString());

        if (XingePushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setContent(xgPushTextMessage.getContent());
            message.setPlatform(XingePushManager.NAME);
            XingePushManager.hMessageProvider.onReceivePassThroughMessage(context, message);
        }


    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult
            xgPushClickedResult) {

        Log.d(TAG, "通知点击 onNotifactionClickedResult ：" + xgPushClickedResult.toString());

        if (XingePushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setPlatform(XingePushManager.NAME);
            message.setTitle(xgPushClickedResult.getTitle());
            message.setDescription(xgPushClickedResult.getContent());
            message.setContent(xgPushClickedResult.getCustomContent());
            XingePushManager.hMessageProvider.onNotificationMessageArrived(context, message);
        }
    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {

        Log.d(TAG, "通知展示 onNotifactionShowedResult ： " + xgPushShowedResult.toString());

        if (XingePushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setPlatform(XingePushManager.NAME);
            message.setTitle(xgPushShowedResult.getTitle());
            message.setDescription(xgPushShowedResult.getContent());
            message.setContent(xgPushShowedResult.getCustomContent());
            XingePushManager.hMessageProvider.onNotificationMessageArrived(context, message);
        }
    }
}
