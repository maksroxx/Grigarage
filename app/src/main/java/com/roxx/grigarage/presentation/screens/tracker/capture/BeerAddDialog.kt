package com.roxx.grigarage.presentation.screens.tracker.capture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BeerAddDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: CaptureViewModel = hiltViewModel()
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Add New Beer", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = viewModel.name.ifEmpty { "" },
                onValueChange = { viewModel.onEvent(CaptureEvent.OnNameChange(it)) },
                label = { Text("Name") },
                placeholder = { Text("Enter beer name") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.brand.ifEmpty { "" },
                onValueChange = { viewModel.onEvent(CaptureEvent.OnBrandChange(it)) },
                label = { Text("Brand") },
                placeholder = { Text("Enter brand name") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.type.ifEmpty { "" },
                onValueChange = { viewModel.onEvent(CaptureEvent.OnTypeChange(it)) },
                label = { Text("Type") },
                placeholder = { Text("Enter type of beer") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.alcoholPercentage.takeIf { it > 0f }?.toString() ?: "",
                onValueChange = {
                    it.toFloatOrNull()?.let { percentage ->
                        if (percentage in 0f..100f) {
                            viewModel.onEvent(CaptureEvent.OnAlcoholChange(percentage))
                        }
                    }
                },
                label = { Text("Alcohol Percentage") },
                placeholder = { Text("0 to 100") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.volume.takeIf { it > 0f }?.toString() ?: "",
                onValueChange = {
                    it.toFloatOrNull()?.let { vol ->
                        viewModel.onEvent(CaptureEvent.OnVolumeChange(vol))
                    }
                },
                label = { Text("Volume (ml)") },
                placeholder = { Text("Enter volume") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.notes.ifEmpty { "" },
                onValueChange = { viewModel.onEvent(CaptureEvent.OnNotesChange(it)) },
                label = { Text("Notes") },
                placeholder = { Text("Additional notes") },
                singleLine = false
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Add Beer")
            }
        }
    }
}