package com.roxx.grigarage.presentation.screens.tracker.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel
import com.roxx.grigarage.presentation.screens.tracker.components.RatingWidget
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun BeerCard(
    beer: BeerUiModel,
    onClick:() -> Unit
) {
    Box(
        modifier = Modifier
            .height(400.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = 20.dp)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                bitmap = beer.photoUri,
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.5f),
            shape = RoundedCornerShape(
                bottomStart = LocalSpacing.current.medium,
                bottomEnd = LocalSpacing.current.medium
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(LocalSpacing.current.medium)
            ) {
                Text(
                    text = beer.name,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = beer.notes,
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 20.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .padding(top = LocalSpacing.current.small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = LocalSpacing.current.small),
                        rating = beer.rating
                    )
                    Text(
                        text = beer.rating.toString(),
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}