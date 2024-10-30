package com.roxx.grigarage.presentation.screens.tracker.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.roxx.grigarage.presentation.screens.tracker.components.BeerCard
import com.roxx.grigarage.presentation.screens.tracker.components.ShimmerLoadingEffect
import com.roxx.grigarage.presentation.screens.tracker.search.components.SearchTextField
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun SearchScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val beerList = viewModel.beers.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.small)) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchTextField(
                text = state.query,
                onValueChange = {
                    viewModel.onEvent(SearchEvent.OnQueryChange(it))
                },
                onSearch = {
                    viewModel.onEvent(SearchEvent.OnSearch)
                },
                onFocusChanged = {
                    viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
                },
                shouldShowHint = state.isHintVisible
            )

            if (beerList.loadState.refresh is LoadState.Loading) {
                ShimmerLoadingEffect()
            } else if (beerList.itemCount == 0 && beerList.loadState.refresh !is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "No beers :(",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                    items(
                        count = beerList.itemCount,
                        key = { index ->
                            val beer = beerList[index]
                            beer?.id ?: index
                        }
                    ) { index ->
                        val beer = beerList[index]
                        if (beer != null) {
                            BeerCard(
                                beer = beer,
                                onClick = { viewModel.onEvent(SearchEvent.OnBeerClick(beer.id)) },
                                onLiked = { viewModel.onEvent(SearchEvent.OnBeerLiked(beer)) }
                            )
                        }
                    }
                }
            }
        }
    }
}
