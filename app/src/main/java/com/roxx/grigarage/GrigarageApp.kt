package com.roxx.grigarage

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.roxx.grigarage.infrastructure.service.NotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GrigarageApp: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationService.COUNTER_CHANNEL_ID,
            "Daily beer",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "Daily beer"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}