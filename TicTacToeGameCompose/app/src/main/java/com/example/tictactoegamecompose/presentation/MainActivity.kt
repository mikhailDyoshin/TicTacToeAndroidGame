package com.example.tictactoegamecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tictactoegamecompose.presentation.gameOverWindow.GameOverWindowScreen
import com.example.tictactoegamecompose.presentation.gameOverWindow.GameOverWindowViewModel
import com.example.tictactoegamecompose.presentation.gameWindow.GameWindowScreen
import com.example.tictactoegamecompose.presentation.gameWindow.GameWindowViewModel
import com.example.tictactoegamecompose.presentation.settingsWindow.SettingsWindow
import com.example.tictactoegamecompose.presentation.settingsWindow.SettingsWindowViewModel
import com.example.tictactoegamecompose.presentation.ui.theme.TicTacToeGameComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeGameComposeTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "game") {
                    composable(route = "game") {
                        val gameViewModel: GameWindowViewModel = hiltViewModel()

                        GameWindowScreen(
                            state = gameViewModel.state.value,
                            navigateToGameOverWindow = { navController.navigate(route = "gameOver") },
                            navigateToSettingsWindow = { navController.navigate(route = "settings") },
                            restartGame = { gameViewModel.restartGame() },
                            updateState = { gameViewModel.getState() },
                            simulateMoveOfAI = { gameViewModel.simulateMoveOfAI() },
                            changeGameState = { row, col ->
                                gameViewModel.changeGameState(
                                    row = row,
                                    col = col
                                )
                            },
                        )
                    }
                    composable(route = "settings") {
                        val settingsViewModel: SettingsWindowViewModel = hiltViewModel()
                        SettingsWindow(
                            state = settingsViewModel.state.value,
                            navigateToGame = { navController.navigate(route = "game") },
                            setGameMode = { gameMode -> settingsViewModel.setGameMode(gameMode) },
                            setBoardSize = { boardSize -> settingsViewModel.setBoardSize(boardSize) },
                            setNumberOfPlayers = { numberOfPlayers ->
                                settingsViewModel.setNumberOfPlayers(
                                    numberOfPlayers
                                )
                            },
                            setPlayerFigure = { figure -> settingsViewModel.setPlayerFigure(figure) }
                        )
                    }
                    composable(route = "gameOver") {
                        val gameOverViewModel: GameOverWindowViewModel = hiltViewModel()
                        val state = gameOverViewModel.state.value
                        GameOverWindowScreen(
                            state = state,
                            navigateToGame = { navController.navigate(route = "game") }) {

                        }
                    }
                }
            }
        }
    }
}