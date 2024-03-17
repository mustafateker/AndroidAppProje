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
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat


class bunu_unutma : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switch: SwitchCompat
    private lateinit var checkBox: CheckBox
    private lateinit var notificationManager: NotificationManager
    private val notificationChannelId = "10001"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bunu_unutma)

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
                sendNotification("Checkbox işaretlendi!")
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
            .setContentTitle("Bilgilendirme")
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.app_icon__main_top)
            .setAutoCancel(true)


        notificationManager.notify(1, builder.build())
    }

}