package com.roxx.grigarage.infrastructure.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.roxx.grigarage.infrastructure.service.NotificationService

class DailyNotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("KKK", "good")
        val notificationService = NotificationService(context)
        notificationService.showNotification()
    }
}