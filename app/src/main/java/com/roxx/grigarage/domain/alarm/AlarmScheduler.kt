package com.roxx.grigarage.domain.alarm

interface AlarmScheduler {
    fun schedule()
    fun setupDailyNotification()
}