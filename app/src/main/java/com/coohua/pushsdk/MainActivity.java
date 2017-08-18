package com.coohua.pushsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.push_result);


//        IntentFilter filter = new IntentFilter();
//        filter.addAction(HPushMessageReceiver.RECEIVE_THROUGH_MESSAGE);
//        filter.addAction(HPushMessageReceiver.NOTIFICATION_ARRIVED);
//        filter.addAction(HPushMessageReceiver.NOTIFICATION_CLICKED);
//        filter.addAction(HPushMessageReceiver.ERROR);
//        this.registerReceiver(mBroadcastReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        this.unregisterReceiver(mBroadcastReceiver);
    }

//    private BroadcastReceiver mBroadcastReceiver = new HPushMessageReceiver() {
//
//        @Override
//        public void onReceivePassThroughMessage(Context context, HPushMessage message) {
//            text.setText(String.format("%s\n收到透传消息:%s", text.getText().toString(), message
//                    .getPlatform()));
//            text.setText(String.format("%s\n收到透传消息:%s", text.getText().toString(), message
//                    .getContent()));
//        }
//
//        @Override
//        public void onNotificationMessageClicked(Context context, HPushMessage message) {
//            text.setText(String.format("%s\n通知栏消息点击:%s", text.getText().toString(), message
//                    .getPlatform()));
//            text.setText(String.format("%s\n通知栏消息点击:%s", text.getText().toString(), message
//                    .getContent()));
//        }
//
//        @Override
//        public void onNotificationMessageArrived(Context context, HPushMessage message) {
//            text.setText(String.format("%s\n通知栏消息到达:%s", text.getText().toString(), message
//                    .getPlatform()));
//            text.setText(String.format("%s\n通知栏消息到达:%s", text.getText().toString(), message
//                    .getContent()));
//        }
//    };

}
