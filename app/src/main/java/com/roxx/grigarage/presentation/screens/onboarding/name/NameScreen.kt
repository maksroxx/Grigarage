package com.roxx.grigarage.presentation.screens.onboarding.name

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.roxx.grigarage.presentation.screens.onboarding.components.ActionButton
import com.roxx.grigarage.presentation.screens.onboarding.components.UnitTextField
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun NameScreen(
    snackBarHostState: SnackbarHostState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: NameScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackbar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.large)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Whats your name?",
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
            UnitTextField(
                value = viewModel.name,
                onValueChange = viewModel::onNameEnter,
                unit = "",
                keyboardType = KeyboardType.Text
            )
        }
        ActionButton(
            text = "Further",
            onClick = viewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(0.6f)
        )
    }
}