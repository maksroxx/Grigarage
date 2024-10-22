package com.roxx.grigarage.presentation.screens.tracker.capture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = viewModel.name,
                onValueChange = { viewModel.onNameChange(it) },
                label = { Text("Name") }
            )
            TextField(
                value = viewModel.brand,
                onValueChange = { viewModel.onBrandChange(it) },
                label = { Text("Brand") }
            )
            TextField(
                value = viewModel.type,
                onValueChange = { viewModel.onTypeChange(it) },
                label = { Text("Type") }
            )
            TextField(
                value = viewModel.alcoholPercentage.toString(),
                onValueChange = { viewModel.onAlcoholChange(it.toFloat()) },
                label = { Text("Alcohol Percentage") }
            )
            TextField(
                value = viewModel.volume.toString(),
                onValueChange = { viewModel.onVolumeChange(it.toFloat()) },
                label = { Text("Volume") }
            )
            TextField(
                value = viewModel.color,
                onValueChange = { viewModel.onColorChange(it) },
                label = { Text("Color") }
            )
            TextField(
                value = viewModel.notes,
                onValueChange = { viewModel.onNotesChange(it) },
                label = { Text("Notes") }
            )
            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = { onConfirm() }
            ) {
                Text("Add bear")
            }
        }
    }
}