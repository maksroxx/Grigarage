package com.roxx.grigarage.presentation.screens.onboarding.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.screens.onboarding.components.ActionButton
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.DarkYellow
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun InfoScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkYellow)
            .padding(LocalSpacing.current.medium)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "In our app you can track your beers \nsee bottles goal for week \nand another",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.displaySmall
        )

        ActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    horizontal = LocalSpacing.current.large,
                    vertical = LocalSpacing.current.medium
                )
                .fillMaxWidth(),
            text = "Further",
            onClick = { onNavigate(UiEvent.Navigate(Route.NAME)) }
        )
    }
}

@Preview
@Composable
private fun InfoScreenPrev() {
    InfoScreen { }
}