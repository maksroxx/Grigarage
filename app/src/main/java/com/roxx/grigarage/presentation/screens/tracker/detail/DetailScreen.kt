package com.roxx.grigarage.presentation.screens.tracker.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun DetailScreen(
    onNavigateUp: () -> Unit,
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val beer = viewModel.beer.collectAsState().value
    val spacing = LocalSpacing.current
    LaunchedEffect(key1 = id) {
        viewModel.onEvent(DetailEvent.LoadBeer(id))
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { viewModel.onEvent(DetailEvent.GoBack) }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }

            IconButton(
                onClick = { viewModel.onEvent(DetailEvent.DeleteBeer) },
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black
                )
            }
        }

        beer?.let {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(spacing.large))

                // Beer Image
                Image(
                    bitmap = it.photoUri,
                    contentDescription = "Beer Image",
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(spacing.medium))

                // Beer Name and Brand
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(text = it.brand, style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.height(spacing.small))

                Text(
                    text = "Type: ${it.type}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(spacing.small))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Alcohol percentage: ${it.alcoholPercentage}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Volume: ${it.volume}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // Beer Notes
                Text(
                    text = "Notes: ${it.notes}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(spacing.small))

                // Count row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { viewModel.onEvent(DetailEvent.Decrement(it.id)) }) {
                        Icon(Icons.Default.Remove, contentDescription = "Decrease count")
                    }

                    Text(
                        text = it.drinkCount.toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(onClick = { viewModel.onEvent(DetailEvent.Increment(it.id)) }) {
                        Icon(Icons.Default.Add, contentDescription = "Increase count")
                    }
                }
            }
        } ?: run {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}