package com.example.tictactoegamecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tictactoegamecompose.presentation.myAppNavHost.MyAppNavHost
import com.example.tictactoegamecompose.presentation.ui.theme.TicTacToeGameComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeGameComposeTheme {
                    MyAppNavHost()
            }
        }
    }
}