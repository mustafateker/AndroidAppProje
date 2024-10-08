package com.crossborders.studyhard


import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat


class bunu_unutma : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switch: Switch
    private lateinit var checkBox: CheckBox
    private lateinit var notificationManager: NotificationManager
    private val notificationChannelId = "10001"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bunu_unutma)

        val libreFranklinSemibold = R.font.libre_franklin_semibold
        findViewById<TextView>(R.id.textView15).typeface =
            ResourcesCompat.getFont(this, libreFranklinSemibold)

        switch = findViewById(R.id.switch1)
        checkBox = findViewById(R.id.checkbox)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel() // Bildirim kanalını oluştur

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sendNotification("Switch açık!")
            }
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sendNotification("Bugünkü hedefin tamamlandı,Süpersinn!Böyle devamm!")
            }
        }
    }






    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                notificationChannelId,
                "Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel Description"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(message: String) {
        val intent = Intent(this, bunu_unutma::class.java) // Hedef aktiviteyi belirle
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(this, notificationChannelId)

            .setContentTitle("Hedefin tamamlanmadı! ")
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.app_icon__main_top)
            .setAutoCancel(true)

        notificationManager.notify(1, builder.build())
    }

}