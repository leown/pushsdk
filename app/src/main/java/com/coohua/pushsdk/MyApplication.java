package com.coohua.pushsdk;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.preference.PreferenceManager;

/**
 * @author Leo Wang
 * 2017/8/14
 * desc:
 */


public class MyApplication extends Application {

    public static final String MI_APP_ID = "2882303761517535659";
    public static final String MI_APP_KEY = "5671753555659";

    public static final String MZ_APP_ID = "111063";
    public static final String MZ_APP_KEY = "23c6b530694c45bc89567d18dcb9b1ff";

    public static final String BD_APP_KEY = "rvf3m1lg4B0SUiwHBCvlllTuUIrliLTb";

    public static final String ALI_APP_KEY = "24588011";
    public static final String ALI_APP_SECRET = "3ce21b6f005ebf48a314b049edcd0dac";


    @Override
    public void onCreate() {
        super.onCreate();

//        HPushClient.addPushManager(new MiPushManager(MI_APP_ID, MI_APP_KEY));
//        HPushClient.addPushManager(new MeizuPushManager(MZ_APP_ID, MZ_APP_KEY));
//        HPushClient.addPushManager(new BaiduPushManager(BD_APP_KEY));
////        HPushClient.addPushManager(new XingePushManager());
////        HPushClient.addPushManager(new GeTuiManager());
//        HPushClient.addPushManager(new AliPushManager(getApplicationContext(), ALI_APP_KEY, ALI_APP_SECRET));
//        HPushClient.setPushIntentService(PushIntentService.class);
//        HPushClient.setSelector(new HPushClient.Selector(){
//            @Override
//            public String select(Map<String, HPushManager> pushAdapterMap) {
//                return Const.ALI_PUSH;
//            }
//        });
//
//        String usePushName = getUsePushName(getApplicationContext());
//        if (!TextUtils.isEmpty(usePushName)) {
//            HPushClient.setUsePushName(usePushName);
//        }
//
//
//        // 注册推送
//        HPushClient.registerPush(getApplicationContext());
//        // 绑定别名，一般是填写用户的ID，便于定向推送
//        HPushClient.setAlias(this, "331");
//        // 设置标签
//        HPushClient.setTags(this, "北京");


    }


    public static String getUsePushName(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("usePushName", null);
    }

    public static void setUsePushName(Context context, String usePushName) {
//        HPushClient.unsetAlias(context, "331");
//        HPushClient.unRegisterPush(context);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("usePushName", usePushName).commit();
//        throw new RuntimeException();
        Process.killProcess(Process.myPid());
    }
}
