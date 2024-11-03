package com.roxx.grigarage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.roxx.grigarage.domain.alarm.AlarmScheduler
import com.roxx.grigarage.presentation.navigation.AppNav
import com.roxx.grigarage.ui.theme.GrigarageTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrigarageTheme {
                AppNav()
            }
        }

        alarmScheduler.schedule()
        alarmScheduler.setupDailyNotification()
    }
}