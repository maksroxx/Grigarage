package com.roxx.grigarage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.roxx.grigarage.presentation.navigation.AppNav
import com.roxx.grigarage.ui.theme.GrigarageTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrigarageTheme {
                AppNav()
            }
        }
    }
}