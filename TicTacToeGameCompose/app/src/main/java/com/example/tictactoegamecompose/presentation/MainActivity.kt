package com.example.tictactoegamecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.tictactoegamecompose.presentation.myApp.MyApp
import com.example.tictactoegamecompose.presentation.ui.theme.TicTacToeGameComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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