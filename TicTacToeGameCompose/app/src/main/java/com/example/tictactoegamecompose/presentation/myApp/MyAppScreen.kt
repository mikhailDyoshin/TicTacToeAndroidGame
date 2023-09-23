package com.example.tictactoegamecompose.presentation.myApp

import android.annotation.SuppressLint
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoegamecompose.presentation.settingsWindow.SettingsWindow
import com.example.tictactoegamecompose.presentation.gameOverWindow.GameOverWindowScreen
import com.example.tictactoegamecompose.presentation.gameWindow.GameWindowScreen

@SuppressLint("MutableCollectionMutableState")
@Composable
    fun MyApp(modifier: Modifier = Modifier, viewModel: MyAppViewModel = viewModel()) {

    var showSettingsWindow: Boolean by remember {
        mutableStateOf(false)
    }

    val showGameOverWindow = viewModel.state.value.myAppState.gameOverStatus

    Surface(modifier) {
        when {
            showSettingsWindow -> SettingsWindow(
                closeSettingsWindow = { showSettingsWindow = false },
            )

            showGameOverWindow -> GameOverWindowScreen(restartGame = { viewModel.resetGameStatus() })

            else ->
                GameWindowScreen(
                    openSettingWindow = { showSettingsWindow = true },
                    checkGameStatus = { viewModel.getGameStatus() },
                )
        }
    }
}