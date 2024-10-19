package com.roxx.grigarage.presentation.navigation

import androidx.navigation.NavController
import com.roxx.grigarage.presentation.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}