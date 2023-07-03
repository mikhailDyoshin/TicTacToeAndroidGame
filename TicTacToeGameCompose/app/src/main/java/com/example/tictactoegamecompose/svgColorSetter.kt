package com.example.tictactoegamecompose

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.colorResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun svgColorSetter(): Color {
    return if (MaterialTheme.colorScheme.primary.luminance() > 0.5) {
        colorResource(id = R.color.svgColorLight)
    } else {
        colorResource(id = R.color.svgColorDark)
    }
}