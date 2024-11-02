package com.roxx.grigarage.infrastructure.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val preferences = context?.applicationContext
            ?.getSharedPreferences("shared_pref", MODE_PRIVATE)
        preferences?.edit()
            ?.putInt("current_week_beer_count", 0)
            ?.apply()
    }
}