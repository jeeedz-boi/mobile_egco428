package com.egco428.lab24_simplenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val CHANNLE_ID = "147"
    private val NOTIFICATION_ID = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendNotiflyBtn.setOnClickListener {
            createNotificationChannel()
            notificationBoradcast("EGCO428 Notification", "Hello World")
        }

    }

    private fun notificationBoradcast(title: String, body: String) {
        val intentMessage = Intent(this, NotificationReceiverActivity::class.java)
        val intentSetting = Intent(this, SettingActivity::class.java)
        val intentWarning = Intent(this, WarningActivity::class.java)

        val pendingIntentMessage = PendingIntent.getActivity(this, System.currentTimeMillis().toInt(), intentMessage, 0)
        val pendingIntentSetting = PendingIntent.getActivity(this, System.currentTimeMillis().toInt(), intentSetting, 0)
        val pendingIntentWarning = PendingIntent.getActivity(this, System.currentTimeMillis().toInt(), intentWarning, 0)

        val messageAction = NotificationCompat.Action.Builder(R.drawable.ic_message, "Message", pendingIntentMessage).build()
        val settingAction = NotificationCompat.Action.Builder(R.drawable.ic_message, "Setting", pendingIntentSetting).build()
        val warningAction = NotificationCompat.Action.Builder(R.drawable.ic_message, "Warning", pendingIntentWarning).build()


        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.user)
        val mBuilder = NotificationCompat.Builder(this, CHANNLE_ID)
            .setSmallIcon(R.drawable.ic_call)
            .setLargeIcon(largeIcon) // Large Icon needed bitmap format
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(messageAction)
            .addAction(settingAction)
            .addAction(warningAction)
            .setWhen(System.currentTimeMillis()+1000)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build())
    }


    private fun createNotificationChannel() {
        val channelName = "doed_channel"
        val description = "Channel for egco428 lab"
        val importanceLevel = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(CHANNLE_ID, channelName, importanceLevel)
        channel.description = description

        var notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}