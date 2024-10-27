package com.roxx.grigarage.presentation.screens.tracker.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier,
    shimmerWidth: Float = 0.2f,
    baseColor: Color = Color.LightGray.copy(alpha = 0.7F),
    highlightColor: Color = Color.White.copy(alpha = 0.7F)
) {
    val transition = rememberInfiniteTransition(label = "inf")
    val shimmerAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "shim animation"
    )

    val configuration = LocalConfiguration.current
    val screenWidthPx = with(LocalDensity.current) { configuration.screenWidthDp.dp.toPx() }
    val shimmerWidthPx = shimmerWidth * screenWidthPx

    val brush = Brush.linearGradient(
        colors = listOf(baseColor, highlightColor, baseColor),
        start = Offset(x = shimmerAnim.value * shimmerWidthPx, y = 0f),
        end = Offset(x = shimmerAnim.value * shimmerWidthPx + shimmerWidthPx, y = 0f)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush)
    )
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