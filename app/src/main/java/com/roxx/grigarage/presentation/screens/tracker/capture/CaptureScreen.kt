package com.roxx.grigarage.presentation.screens.tracker.capture

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.LocalSpacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CaptureScreen(
    snackBarHostState: SnackbarHostState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: CaptureViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val controller = viewModel.cameraControllerFlow.collectAsState()
    controller.value.bindToLifecycle(lifecycleOwner)
    val context = LocalContext.current
    val openWindow = remember { mutableStateOf(false) }
    val bitmap by viewModel.bitmap.collectAsState(null)

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            CoroutineScope(Dispatchers.Main).launch {
                snackBarHostState.showSnackbar("Permission to access camera denied")
            }
        }
    }

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

    LaunchedEffect(key1 = true) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    if (openWindow.value) {
        bitmap?.let {
            TakePhotoPreviewDialog(
                photoBitmap = it,
                onConfirm = {
                    viewModel.onNextClick()
                },
                onDismiss = {
                    openWindow.value = false
                }
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(controller.value)
        Text(
            modifier = Modifier.fillMaxWidth().padding(top = LocalSpacing.current.small),
            text = "Track your beer",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(48.dp)
                .padding(bottom = 10.dp),
            onClick = {
                viewModel.takePhoto()
                openWindow.value = true
            }
        ) {
            Icon(
                Icons.Default.CameraAlt,
                contentDescription = "make photo",
                tint = Color.White,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}