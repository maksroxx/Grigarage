package com.roxx.grigarage.presentation.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.roxx.grigarage.ui.theme.DarkYellow
import com.roxx.grigarage.ui.theme.LocalSpacing

@Composable
fun UnitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    unit: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = DarkYellow,
        fontSize = 70.sp
    ),
    keyboardType: KeyboardType
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            singleLine = true,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .alignBy(LastBaseline)
        )

        Spacer(modifier = Modifier.width(LocalSpacing.current.small))

        Text(
            text = unit,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}