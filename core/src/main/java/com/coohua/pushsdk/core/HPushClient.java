package com.coohua.pushsdk.core;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.coohua.pushsdk.core.util.Const;
import com.coohua.pushsdk.core.util.RomUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HPushClient {


    private static Map<String, HPushManager> pushManagerMap = new HashMap<>();
    private static String sUsePushName;
    private static boolean isAllRegister = false;
    private static Selector sSelector;
    private static String sReceiverPermission = null;// 避免被其它APP接收
    private static Class<? extends HPushIntentService> sHPushIntentServiceClass;


    private HPushClient() {

    }

    public static void setPushIntentService(Class<? extends HPushIntentService>
                                                    hPushIntentServiceClass) {
        HPushClient.sHPushIntentServiceClass = hPushIntentServiceClass;
    }

    public static void setSelector(Selector selector) {
        sSelector = selector;
        sUsePushName = sSelector.select(pushManagerMap);
    }

    public static String getUsePushName() {
        return sUsePushName;
    }

    public static void addPushManager(HPushManager pushAdapter) {
        pushManagerMap.put(pushAdapter.getPushName(), pushAdapter);
        pushAdapter.setMessageProvider(mHMessageProvider);
    }

    public static void registerPush(Context context) {

        sReceiverPermission = context.getPackageName() + ".permission.HPUSH_RECEIVE";

        Set<String> keys = pushManagerMap.keySet();
        for (String key : keys) {
            if (isAllRegister || key.equals(sUsePushName)) {
                pushManagerMap.get(key).registerPush(context);
            } else {
                pushManagerMap.get(key).unRegisterPush(context);
            }
        }
    }

    private static HPushManager getPushManager() {
        if (sUsePushName == null) {
            throw new RuntimeException("you need setSelector or setUsePushName");
        }
        return pushManagerMap.get(sUsePushName);
    }

    public static void unRegisterPush(Context context) {
        if (isAllRegister) {
            for (HPushManager pushManager : pushManagerMap.values()) {
                if (pushManager != null)
                    pushManager.unRegisterPush(context);
            }
        } else
            getPushManager().unRegisterPush(context);
    }

    public static void setUsePushName(String sUsePushName) {
        HPushClient.sUsePushName = sUsePushName;
    }

    public static void setIsAllRegister(boolean isAllRegister) {
        HPushClient.isAllRegister = isAllRegister;
    }

    public static void setAlias(Context context, String alias) {
        if (isAllRegister) {
            for (HPushManager pushManager : pushManagerMap.values()) {
                if (pushManager != null)
                    pushManager.setAlias(context, alias);
            }
        } else
            getPushManager().setAlias(context, alias);
    }

    public static void unsetAlias(Context context, String alias) {
        if (isAllRegister) {
            for (HPushManager pushManager : pushManagerMap.values()) {
                if (pushManager != null)
                    pushManager.unsetAlias(context, alias);
            }
        } else
            getPushManager().unsetAlias(context, alias);
    }

    public static void setTags(Context context, String... tags) {
        if (isAllRegister) {
            for (HPushManager pushManager : pushManagerMap.values()) {
                if (pushManager != null)
                    pushManager.setTags(context, tags);
            }
        } else
            getPushManager().setTags(context, tags);
    }

    public static void unsetTags(Context context, String... tags) {
        if (isAllRegister) {
            for (HPushManager pushManager : pushManagerMap.values()) {
                if (pushManager != null)
                    pushManager.unsetTags(context, tags);
            }
        } else
            getPushManager().unsetTags(context, tags);
    }

    public static void bindAccount(Context context, String account) {
        if (isAllRegister) {
            for (HPushManager pushManager : pushManagerMap.values()) {
                if (pushManager != null)
                    pushManager.bindAccount(context, account);
            }
        } else
            getPushManager().bindAccount(context, account);
    }

    public static void unbindAccount(Context context, String account) {
        if (isAllRegister) {
            for (HPushManager pushManager : pushManagerMap.values()) {
                if (pushManager != null)
                    pushManager.unbindAccount(context, account);
            }
        } else
            getPushManager().bindAccount(context, account);
    }

    public static class Selector {
        public String select(Map<String, HPushManager> pushAdapterMap) {
            switch (RomUtil.rom()) {
                case MIUI:
                    if (pushAdapterMap.containsKey(Const.MI_PUSH))
                        return Const.MI_PUSH;
                case EMUI:
                    if (pushAdapterMap.containsKey(Const.MEIZU_PUSH))
                        return Const.MEIZU_PUSH;
                case FLYME:
                    if (pushAdapterMap.containsKey(Const.HUAWEI_PUSH))
                        return Const.HUAWEI_PUSH;
                default:
                case BAI_DU:
                    if (pushAdapterMap.containsKey(Const.BAIDU_PUSH))
                        return Const.BAIDU_PUSH;
                    break;
            }

            return null;
        }
    }

    private static HMessageProvider mHMessageProvider = new HMessageProvider() {
        @Override
        public void onReceivePassThroughMessage(Context context, HPushMessage message) {
            message.setNotify(0);
            Intent intent = new Intent(HPushMessageReceiver.RECEIVE_THROUGH_MESSAGE);
            intent.putExtra("message", message);
            context.sendBroadcast(intent, sReceiverPermission);
            Log.d("onReceivePassThrough", message.getContent());

            if (sHPushIntentServiceClass != null) {
                intent.setClass(context, sHPushIntentServiceClass);
                context.startService(intent);
            }
        }

        @Override
        public void onNotificationMessageClicked(Context context, HPushMessage message) {
            message.setNotify(1);
            Intent intent = new Intent(HPushMessageReceiver.NOTIFICATION_CLICKED);
            intent.putExtra(HPushMessageReceiver.MESSAGE, message);
            context.sendBroadcast(intent, sReceiverPermission);
            Log.d("onNotificationClicked", message.getContent());

            if (sHPushIntentServiceClass != null) {
                intent.setClass(context, sHPushIntentServiceClass);
                context.startService(intent);
            }
        }

        @Override
        public void onNotificationMessageArrived(Context context, HPushMessage message) {
            Intent intent = new Intent(HPushMessageReceiver.NOTIFICATION_ARRIVED);
            intent.putExtra("message", message);
            context.sendBroadcast(intent, sReceiverPermission);
            Log.d("onNotificationArrived", message.getContent());

            if (sHPushIntentServiceClass != null) {
                intent.setClass(context, sHPushIntentServiceClass);
                context.startService(intent);
            }
        }
    };
}
