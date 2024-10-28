package com.roxx.grigarage.presentation.screens.tracker.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun WeeklyBeerIntakeDialog(
    viewModel: ProfileViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    val spacing = LocalSpacing.current
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.medium)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Set Weekly Beer Intake", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(spacing.medium))

            TextField(
                value = viewModel.beerCount.value.toString(),
                onValueChange = {
                    it.toIntOrNull()?.let { count ->
                        viewModel.onEvent(ProfileEvent.UpdateBeerCount(count))
                    }
                },
                label = { Text("Number of beers per week") },
                placeholder = { Text("Enter number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(spacing.medium))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
                        viewModel.beerCount.value.let {
                            onConfirm()
                        }
                    }
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}