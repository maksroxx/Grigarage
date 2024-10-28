package com.roxx.grigarage.presentation.screens.tracker.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.roxx.grigarage.presentation.util.UiEvent

@Composable
fun DetailScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.onEvent(DetailEvent.LoadBeer(id))
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
}