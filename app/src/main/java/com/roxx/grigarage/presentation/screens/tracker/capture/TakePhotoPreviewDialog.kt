package com.roxx.grigarage.presentation.screens.tracker.capture

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TakePhotoPreviewDialog(
    photoBitmap: Bitmap,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Image(
                bitmap = photoBitmap.asImageBitmap(),
                contentDescription = "Preview Photo",
                modifier = Modifier.fillMaxSize()
            )

            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Dismiss", tint = Color.White)
            }

            IconButton(
                onClick = { onConfirm() },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(Icons.Default.Check, contentDescription = "Confirm", tint = Color.White)
            }
        }
    }
}
