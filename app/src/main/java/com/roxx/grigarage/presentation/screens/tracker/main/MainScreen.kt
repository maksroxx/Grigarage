package com.roxx.grigarage.presentation.screens.tracker.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.roxx.grigarage.presentation.screens.tracker.components.BeerCard
import com.roxx.grigarage.presentation.screens.tracker.components.ShimmerBeerCard
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun MainScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val beerList = viewModel.beers.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(LocalSpacing.current.small)) {
        if (beerList.loadState.refresh is LoadState.Loading) {
            ShimmerLoadingEffect()
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Beers list",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
                items(beerList.itemCount) { index ->
                    val beer = beerList[index]
                    if (beer != null) {
                        BeerCard(
                            beer = beer,
                            onClick = { viewModel.onBeerClicked() },
                            onLiked = { viewModel.onBeerLiked(beer) }
                        )
                    } else {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "Beers list empty :(",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ShimmerLoadingEffect() {
    Column {
        repeat(6) {
            ShimmerBeerCard()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}