package com.egco428.lab25_firebasecloudmessaging

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    val CHANNEL_ID = "com.egco428.lab25_firebasecloudmessaging"
    val NOTIFICATION_ID = 105

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("FCMLab", "msg received")

        val title:String?
        val body:String?
        if (remoteMessage.data.size > 0){
            title = remoteMessage.data["title"]
            body = remoteMessage.data["body"]
        }
        else{
            title = remoteMessage.notification!!.title
            body = remoteMessage.notification!!.body
        }
        showNotification(applicationContext, title, body)
    }

    private fun showNotification(context: Context, title: String?, body: String?) {
        val intent = Intent(context, MainActivity::class.java)
        intent.data = Uri.parse("custom//"+System.currentTimeMillis())
        intent.action = "actionstring"+System.currentTimeMillis()
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification:Notification

        notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setOngoing(true)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_message)
            .setContentText(body)
            .setContentTitle(title)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setWhen(System.currentTimeMillis()+1000)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .build()

        val notificationManger = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(CHANNEL_ID, title, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManger.createNotificationChannel(notificationChannel)
        notificationManger.notify(NOTIFICATION_ID, notification)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("DEV", "new token")
    }

}