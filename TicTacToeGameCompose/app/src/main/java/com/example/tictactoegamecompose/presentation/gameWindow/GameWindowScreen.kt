package com.example.tictactoegamecompose.presentation.gameWindow

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoegamecompose.R
import com.example.tictactoegamecompose.presentation.board.BoardScreen
import com.example.tictactoegamecompose.presentation.previewStates.PreviewStates
import com.example.tictactoegamecompose.presentation.svgColorSetter


@Composable
fun GameWindowScreen(
    state: GameState,
    navigateToGameOverWindow: () -> Unit,
    navigateToSettingsWindow: () -> Unit,
    restartGame: () -> Unit,
    updateState: () -> Unit,
    simulateMoveOfAI: () -> Unit,
    changeGameState: (row: Int, col: Int) -> Unit,
) {

    val svgColor = svgColorSetter()

    LaunchedEffect(state) {
        if (state.gameOver) {
            navigateToGameOverWindow()
        }
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Open settings button
            IconButton(
                onClick = {
                    navigateToSettingsWindow()
                },
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Settings",
                    modifier = Modifier.size(32.dp),
                    colorFilter = ColorFilter.tint(svgColor)
                )
            }

            // Restart button
            IconButton(
                onClick = {
                    restartGame()
                    updateState()
                },
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.restart),
                    contentDescription = "Restart",
                    modifier = Modifier.size(32.dp),
                    colorFilter = ColorFilter.tint(svgColor)
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 80.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoardScreen(
                state = state.boardState,
                simulateMoveOfAI = { simulateMoveOfAI() },
                updateState = { updateState() },
                changeGameState = { row, col -> changeGameState(row, col) }
            )
        }
    }

}

@Preview
@Composable
fun GameWindowPreview() {
    GameWindowScreen(
        state = PreviewStates.GAME_STATE,
        navigateToSettingsWindow = {},
        navigateToGameOverWindow = {},
        restartGame = {},
        updateState = {},
        simulateMoveOfAI = {},
        changeGameState = { _, _ -> }
    )
}
