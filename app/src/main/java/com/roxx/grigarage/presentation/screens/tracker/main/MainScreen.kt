package com.roxx.grigarage.presentation.screens.tracker.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.roxx.grigarage.presentation.components.ActionButton
import com.roxx.grigarage.presentation.screens.tracker.components.BeerCard
import com.roxx.grigarage.presentation.screens.tracker.components.ShimmerLoadingEffect
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.LocalSpacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val beerList = viewModel.beers.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {}
    )

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    LaunchedEffect(key1 = true) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.small)) {
        if (beerList.loadState.refresh is LoadState.Loading) {
            ShimmerLoadingEffect()
        } else if (beerList.itemCount == 0 && beerList.loadState.append.endOfPaginationReached) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Beers list empty :(",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.large))
                ActionButton(
                    text = "explore beers",
                    onClick = { viewModel.onEvent(MainEvent.OnButtonClick) }
                )
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Beers list",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
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
                            onClick = { viewModel.onEvent(MainEvent.OnBeerClick(beer.id)) },
                            onLiked = { viewModel.onEvent(MainEvent.OnBeerLiked(beer)) }
                        )
                    }
                }
            }
        }
    }
}
