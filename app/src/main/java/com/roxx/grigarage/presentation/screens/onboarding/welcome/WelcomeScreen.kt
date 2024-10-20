package com.roxx.grigarage.presentation.screens.onboarding.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.DarkYellow
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: WelcomeViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().background(DarkYellow).padding(LocalSpacing.current.medium)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Welcome to Grigarage",
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )
        SwipeWidget(
            modifier = Modifier.align(Alignment.BottomCenter),
            onReady = {
                onNavigate(UiEvent.Navigate(Route.INFO))
            }
        )
    }
}

@Preview
@Composable
private fun WelcomeScreenPrev() {
    WelcomeScreen({})
}