package com.coohua.client.meizu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.coohua.pushsdk.core.HPushMessage;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;


/**
 * @author Leo Wang
 * 2017-8-14
 * desc:
 */
public class MeizuPushMsgReceiver extends MzPushMessageReceiver {
    private static final String TAG = "MeizuPushMsgReceiver";
    private int flyme_status_ic_notification;
    private int mz_push_notification_small_icon;

    @Override
    @Deprecated
    public void onRegister(Context context, String pushid) {
        //应用在接受返回的pushid

    }

    @Override
    public void onHandleIntent(Context context, Intent intent) {
        flyme_status_ic_notification = context.getResources().getIdentifier
                ("flyme_status_ic_notification", "drawable", context.getPackageName());
        mz_push_notification_small_icon = context.getResources().getIdentifier
                ("mz_push_notification_small_icon", "drawable", context.getPackageName());


        super.onHandleIntent(context, intent);
    }

    @Override
    public void onMessage(Context context, String s) {
        //接收服务器推送的透传消息
        if (MeizuPushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setContent(s);
            message.setPlatform(MeizuPushManager.NAME);
            MeizuPushManager.hMessageProvider.onReceivePassThroughMessage(context, message);
        }
    }

    @Override
    @Deprecated
    public void onUnRegister(Context context, boolean b) {
        //调用PushManager.unRegister(context）方法后，会在此回调反注册状态
    }

    //设置通知栏小图标
    @Override
    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        if (flyme_status_ic_notification > 0) {
            pushNotificationBuilder.setmLargIcon(flyme_status_ic_notification);
            Log.d(TAG, "设置通知栏大图标");
        } else {
            Log.e(TAG, "缺少drawable/flyme_status_ic_notification.png");
        }
        if (mz_push_notification_small_icon > 0) {
            pushNotificationBuilder.setmStatusbarIcon(mz_push_notification_small_icon);
            Log.d(TAG, "设置通知栏小图标");
            Log.e(TAG, "缺少drawable/mz_push_notification_small_icon.png");
        }
    }

    @Override
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        //检查通知栏和透传消息开关状态回调
    }

    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        Log.i(TAG, "onRegisterStatus " + registerStatus);
        //新版订阅回调
    }

    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        Log.i(TAG, "onUnRegisterStatus " + unRegisterStatus);
        //新版反订阅回调
    }

    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        Log.i(TAG, "onSubTagsStatus " + subTagsStatus);
        //标签回调
    }

    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        Log.i(TAG, "onSubAliasStatus " + subAliasStatus);
        //别名回调
    }

    @Override
    public void onNotificationArrived(Context context, String title, String content, String
            selfDefineContentString) {
        //通知栏消息到达回调，flyme6基于android6.0以上不再回调
        Log.i(TAG, "onNotificationArrived title " + title + "content " + content + " " +
                "selfDefineContentString " + selfDefineContentString);

        if (MeizuPushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setPlatform(MeizuPushManager.NAME);
            message.setTitle(title);
            message.setDescription(content);
            message.setContent(selfDefineContentString);
            MeizuPushManager.hMessageProvider.onNotificationMessageArrived(context, message);
        }
    }

    @Override
    public void onNotificationClicked(Context context, String title, String content, String
            selfDefineContentString) {
        //通知栏消息点击回调
        Log.i(TAG, "onNotificationClicked title " + title + "content " + content + " " +
                "selfDefineContentString " + selfDefineContentString);

        if (MeizuPushManager.hMessageProvider != null) {
            HPushMessage message = new HPushMessage();
            message.setPlatform(MeizuPushManager.NAME);
            message.setTitle(title);
            message.setDescription(content);
            message.setContent(selfDefineContentString);
            MeizuPushManager.hMessageProvider.onNotificationMessageClicked(context, message);
        }
    }

    @Override
    public void onNotificationDeleted(Context context, String title, String content, String
            selfDefineContentString) {
        //通知栏消息删除回调；flyme6基于android6.0以上不再回调
        Log.i(TAG, "onNotificationDeleted title " + title + "content " + content + " " +
                "selfDefineContentString " + selfDefineContentString);
    }

}