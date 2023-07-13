package com.example.tictactoegamecompose

import MyApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.tictactoegamecompose.ui.theme.TicTacToeGameComposeTheme


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
