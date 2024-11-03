package com.roxx.grigarage.infrastructure.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.roxx.grigarage.MainActivity
import com.roxx.grigarage.R
import com.roxx.grigarage.domain.service.Notification

class NotificationService (
    private val context: Context
): Notification {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    override fun showNotification() {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            2,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setSmallIcon(R.drawable.beer_icon)
            .setContentTitle("Op-op-op")
            .setContentText("Time to drink beer!")
            .setContentIntent(activityPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(2, notification)
    }

    companion object {
        const val COUNTER_CHANNEL_ID = "daily_notification_channel"
    }
}