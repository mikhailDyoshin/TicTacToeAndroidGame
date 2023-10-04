package com.example.tictactoegamecompose.presentation.myAppNavHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tictactoegamecompose.presentation.gameOverWindow.GameOverWindowScreen
import com.example.tictactoegamecompose.presentation.gameOverWindow.GameOverWindowViewModel
import com.example.tictactoegamecompose.presentation.gameWindow.GameWindowScreen
import com.example.tictactoegamecompose.presentation.gameWindow.GameWindowViewModel
import com.example.tictactoegamecompose.presentation.settingsWindow.SettingsWindow
import com.example.tictactoegamecompose.presentation.settingsWindow.SettingsWindowViewModel

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "game"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = "game") {
            val gameViewModel: GameWindowViewModel = hiltViewModel()
            gameViewModel.getState()
            val gameState = gameViewModel.state.value
            GameWindowScreen(
                state = gameState,
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
                setPlayerFigure = { figure -> settingsViewModel.setPlayerFigure(figure) },
                restartGame = { settingsViewModel.restartGame() }
            )
        }
        composable(route = "gameOver") {
            val gameOverViewModel: GameOverWindowViewModel = hiltViewModel()
            gameOverViewModel.getGameOverStatistics()
            val state = gameOverViewModel.state.value
            GameOverWindowScreen(
                state = state,
                navigateToSettings = { navController.navigate(route = "settings") },
            )
        }
    }
}