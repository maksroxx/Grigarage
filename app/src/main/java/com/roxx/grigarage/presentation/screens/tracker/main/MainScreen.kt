package com.roxx.grigarage.presentation.screens.tracker.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.roxx.grigarage.presentation.util.UiEvent

@Composable
fun MainScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val beerList = viewModel.beers.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (beerList.itemSnapshotList.items.isEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                fontSize = 26.sp,
                text = "Beers list empty :("
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Bears list",
                        textAlign = TextAlign.Center
                    )
                }
                items(
                    items = beerList.itemSnapshotList.items,
                ) {
                    BeerCard(it) { }
                }
            }
        }
    }
}