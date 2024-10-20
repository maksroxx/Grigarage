package com.roxx.grigarage.presentation.screens.onboarding.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roxx.grigarage.R
import com.roxx.grigarage.ui.theme.LightYellow

@Composable
fun SwipeWidget(
    modifier: Modifier = Modifier,
    onReady: () -> Unit
) {
    var circlePosition by remember { mutableStateOf(0f) }
    var textVisibility by remember { mutableStateOf(true) }
    var readyState by remember { mutableStateOf(false) }

    if (readyState) {
        onReady()
    }

    BoxWithConstraints(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(Color.Black)
            .padding(1.dp)
            .wrapContentSize()
            .fillMaxWidth()
            .clip(RoundedCornerShape(100.dp))
            .background(LightYellow)
            .padding(4.dp)
    ) {
        if (textVisibility) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Lets Started",
                fontSize = 20.sp,
                color = Color.White
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 40.dp)
                    .size(28.dp),
                painter = painterResource(R.drawable.chevrons_right),
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.9f),
            )
        }

        val boxWidthDp = maxWidth - 64.dp

        Box(
            modifier = Modifier
                .offset(x = circlePosition.dp)
                .size(64.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(if (!readyState) Color.White else Color.Transparent)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart = { textVisibility = false },
                        onDragCancel = {
                            textVisibility = true
                            circlePosition = 0f
                        },
                        onDragEnd = {
                            if (circlePosition < boxWidthDp.value) {
                                circlePosition = 0f
                                readyState = false
                                textVisibility = true
                            } else if (circlePosition == boxWidthDp.value) {
                                readyState = true
                            }
                        },
                        onHorizontalDrag = { _, dragAmount ->
                            if (!readyState) {
                                circlePosition =
                                    (circlePosition + dragAmount / density).coerceIn(
                                        0f,
                                        boxWidthDp.value
                                    )
                            }
                        }
                    )
                }
        ) {
            if (!readyState) {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SwipeButtonPrev() {
    SwipeWidget(
        modifier = Modifier,
        {}
    )
}