package com.roxx.grigarage.infrastructure.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.roxx.grigarage.infrastructure.service.NotificationService

class DailyNotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationService = NotificationService(context)
        notificationService.showNotification()
    }
}