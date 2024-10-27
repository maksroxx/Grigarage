package com.roxx.grigarage.presentation.screens.tracker.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.ui.theme.LightYellow
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun ProfileScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val user = viewModel.user.value
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    if (showDialog) {
        WeeklyBeerIntakeDialog(
            onDismiss = {
                showDialog = false
            },
            onConfirm = {
                viewModel.updatedBeer()
                showDialog = false
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(spacing.medium)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Welcome ${user?.name}",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.medium),
                horizontalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(LightYellow)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = spacing.small)
                            .align(Alignment.TopCenter),
                        text = "Total beer count"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(y = 6.dp),
                        text = user?.totalBeerCount.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 52.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(LightYellow)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = spacing.small)
                            .align(Alignment.TopCenter),
                        text = "Total bottle count"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(y = 6.dp),
                        text = user?.totalBottleCount.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 52.sp
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.medium)
                    .clip(RoundedCornerShape(20.dp))
                    .background(LightYellow)
                    .padding(vertical = spacing.small),
                verticalArrangement = Arrangement.spacedBy(spacing.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Most popular beer"
                )
                Text(
                    text = user?.mostPopularBeer.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.medium),
                horizontalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(LightYellow)
                        .clickable {
                            showDialog = true
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = spacing.small)
                            .align(Alignment.TopCenter),
                        text = "Weekly bottle goal"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(y = 6.dp),
                        text = user?.weeklyGoal.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 52.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(LightYellow)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = spacing.small)
                            .align(Alignment.TopCenter),
                        text = "Weekly bottle count"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(y = 6.dp),
                        text = user?.currentWeekBeerCount.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 52.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.medium)
                    .clip(RoundedCornerShape(20.dp))
                    .background(LightYellow)
                    .padding(spacing.small)
                    .clickable {
                        viewModel.onClick()
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "See favorite beer",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.KeyboardDoubleArrowRight,
                    contentDescription = "go to favorite beers"
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen(onNavigate = {})
}