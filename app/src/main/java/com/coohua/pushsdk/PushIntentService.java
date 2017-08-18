package com.coohua.pushsdk;

/**
 * @author Leo Wang
 * 2017/8/14
 * desc:
 */


public class PushIntentService {
//    @Override
//    public void onReceivePassThroughMessage(HPushMessage message) {
//        Log.e(TAG, "收到透传消息 -> " + message.getPlatform());
//        Log.e(TAG, "收到透传消息 -> " + message.getContent());
//    }
//
//    @Override
//    public void onNotificationMessageClicked(HPushMessage message) {
//        Log.e(TAG, "通知栏消息点击 -> " + message.getPlatform());
//        Log.e(TAG, "通知栏消息点击 -> " + message.getContent());
//
//        try {
//            JSONObject jsonObject = new JSONObject(message.getContent());
//            String option = jsonObject.optString("option");
//            if ("web".equals(option)) {
//                String url = jsonObject.optString("url");
//                openWebView(url);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 建议使用普通通知栏来实现打开URL，因为这样可以实现打开内部浏览器
//     */
//    private void openWebView(String url) {
//        Log.e(TAG, "打开浏览器 -> " + url);
//        // 请自行实现WebViewActivity
//
//        Intent intent = new Intent();
//        intent.setAction("android.intent.action.VIEW");
//        Uri content_url = Uri.parse(url);
//        intent.setData(content_url);
//        startActivity(intent);
//    }
}
