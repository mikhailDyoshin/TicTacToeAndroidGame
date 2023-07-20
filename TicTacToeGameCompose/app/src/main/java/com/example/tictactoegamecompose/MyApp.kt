package com.example.tictactoegamecompose

import android.annotation.SuppressLint
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.tictactoegamecompose.bl.Game

@SuppressLint("MutableCollectionMutableState")
@Composable
fun MyApp(modifier: Modifier = Modifier) {

    val game = remember { Game() }

    var showSettingsWindow: Boolean by remember {
        mutableStateOf(false)
    }

    val showGameOverWindow = game.gameOver

    Surface(modifier) {
        when {
            showSettingsWindow -> SettingsWindow(
                game = game,
                closeSettingsWindow = { showSettingsWindow = false },
            )

            showGameOverWindow -> GameOverWindow(game = game)

            else ->
                GameWindow(
                    game = game,
                    openSettingWindow = { showSettingsWindow = true },
                )
        }
    }
}