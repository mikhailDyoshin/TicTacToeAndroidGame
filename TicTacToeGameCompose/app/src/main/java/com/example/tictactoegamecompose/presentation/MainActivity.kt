package com.example.tictactoegamecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tictactoegamecompose.presentation.gameOverWindow.GameOverWindowViewModel
import com.example.tictactoegamecompose.presentation.gameWindow.GameWindowViewModel
import com.example.tictactoegamecompose.presentation.myApp.MyApp
import com.example.tictactoegamecompose.presentation.settingsWindow.SettingsWindowViewModel
import com.example.tictactoegamecompose.presentation.ui.theme.TicTacToeGameComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeGameComposeTheme {

//                val navController = rememberNavController()
//
//                NavHost(navController = navController, startDestination = "game") {
//                    composable(route = "game") {
//                        val viewModel: GameWindowViewModel = viewModel()
//                    }
//                    composable(route = "settings") {
//                        val viewModel: SettingsWindowViewModel = viewModel()
//                    }
//                    composable(route = "gameOver") {
//                        val viewModel: GameOverWindowViewModel = viewModel()
//                    }
//                }


                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}