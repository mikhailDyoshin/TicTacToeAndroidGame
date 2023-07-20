package com.example.tictactoegamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.colorResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.tictactoegamecompose.ui.theme.TicTacToeGameComposeTheme

@Composable
fun svgColorSetter(): Color {
    return if (MaterialTheme.colorScheme.primary.luminance() > 0.5) {
        colorResource(id = R.color.svgColorLight)
    } else {
        colorResource(id = R.color.svgColorDark)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeGameComposeTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}