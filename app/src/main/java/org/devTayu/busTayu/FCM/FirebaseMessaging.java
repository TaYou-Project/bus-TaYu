package org.devTayu.busTayu.FCM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.devTayu.busTayu.R;

public class FirebaseMessaging extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "FCM_BUS-TAYU";
    private static final CharSequence CHANNEL_NAME = "Bus TaYu";

    public FirebaseMessaging() {
        super();
    }

    // 푸시메시지 수신시 할 작업
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // 수신한 메시지를 처리

        // 메세지 정보를 받아 notification으로 등록
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }
            builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(getApplicationContext());
        }

        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        builder.setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_launcher_background);

        Notification notification = builder.build();
        notificationManager.notify(1, notification);
    }

    // 이 기기의 토큰이 바뀌었을 때 할 작업 = 주로 서버로 변경된 키값을 전달
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        // token을 서버로 전송
    }


}
