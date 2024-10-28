package com.roxx.grigarage.presentation.screens.tracker.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.roxx.grigarage.presentation.screens.onboarding.components.ActionButton
import com.roxx.grigarage.presentation.screens.tracker.components.BeerCard
import com.roxx.grigarage.presentation.screens.tracker.components.ShimmerLoadingEffect
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun FavoriteScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.small)
    ) {
        if (beerList.loadState.refresh is LoadState.Loading) {
            ShimmerLoadingEffect()
        } else if (beerList.itemCount == 0 && beerList.loadState.append.endOfPaginationReached) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No liked beers :(",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.large))
                ActionButton(
                    text = "explore beers",
                    onClick = { viewModel.onButtonClick() }
                )
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            modifier = Modifier.align(Alignment.CenterStart),
                            onClick = {
                                viewModel.onBackArrowClicked()
                            }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back arrow"
                            )
                        }
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Liked beers",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                items(beerList.itemCount) { index ->
                    val beer = beerList[index]
                    if (beer != null) {
                        BeerCard(
                            beer = beer,
                            onClick = { viewModel.onBeerClicked() },
                            onLiked = { viewModel.onBeerLiked(beer) }
                        )
                    }
                }
            }
        }
    }
}