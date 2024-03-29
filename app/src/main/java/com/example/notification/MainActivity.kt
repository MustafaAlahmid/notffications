package com.example.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationBuilderWithBuilderAccessor
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var buildder: Notification.Builder
    private val channelId = "com.example.notification"
    private val description = "test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        button.setOnClickListener {

            val intent = Intent(this,LauncherActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.enableVibration(true)
                notificationChannel.lightColor = Color.GREEN
                notificationManager.createNotificationChannel(notificationChannel)

                buildder = Notification.Builder(this,channelId)
                    .setContentTitle("dvv")
                    .setContentText("jicib")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.ic_launcher))
                    .setContentIntent(pendingIntent)


            }else{
                buildder = Notification.Builder(this)
                    .setContentTitle("dvv")
                    .setContentText("jicib")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.ic_launcher))
                    .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234,buildder.build())
        }


    }
}


