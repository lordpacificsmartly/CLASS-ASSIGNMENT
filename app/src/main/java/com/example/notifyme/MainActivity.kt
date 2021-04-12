package com.example.notifyme
//      imports
import android.app.*
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color.GREEN
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    /* declaring variables */

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    private val channelId = "com.example.notifyme"
    private val description = "Test notification"

    lateinit var btnMove:Button
    lateinit var btnNotifyMe:Button

      @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get ID of Move Button
        btnMove = findViewById(R.id.move)


        //set onclick of move button and set intent
         btnMove.setOnClickListener {
             Intent(this, ResultActivity::class.java).also {
                 startActivity(it)
             }
         }

        // accessing button
        val notify = findViewById<Button>(R.id.notify_me)

        // notify the user of events that happen
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        /* onClick listener for the button */
            val intent = Intent(this, ResultActivity::class.java)

        notify.setOnClickListener {
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val contentView = RemoteViews(packageName, R.layout.activity_result)


            //check for builder version
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                  //allocate notification description
                notificationChannel = NotificationChannel(channelId, description, IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                        .setContentTitle("Coded Me")
                        .setContentText("Text Notification")
                        .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                        .setChannelId(channelId)
                        .setContentIntent(pendingIntent)
            } else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    builder = Notification.Builder(this)
                            .setContentTitle("Coded Me")
                            .setContentText("Text Notification")
                            .setSmallIcon(R.drawable.ic_launcher_round)
                            .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher))
                            .setContentIntent(pendingIntent)

                }
            }
            notificationManager.notify(1234,builder.build())
            intent.putExtra("MESSAGE", "Active")
        }
    }
}



















