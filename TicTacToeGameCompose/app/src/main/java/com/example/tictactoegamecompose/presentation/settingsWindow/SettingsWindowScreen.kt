package com.example.tictactoegamecompose.presentation.settingsWindow

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.tictactoegamecompose.presentation.settingsWindow.components.RadioButtonGroup

@SuppressLint("MutableCollectionMutableState", "UnrememberedMutableState")
@Composable
fun SettingsWindow(
    closeSettingsWindow: () -> Unit,
    viewModel: SettingsWindowViewModel = SettingsWindowViewModel(),
) {

    val state = viewModel.state.value
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Game mode configuration
        RadioButtonGroup(
            selectedOption = state.gameMode,
            options = state.gameModeOptions,
            annotation = "Game mode",
            setConfiguration = { mode ->
                viewModel.setGameMode(gameMode = mode)
            },
            disabledButtons = mutableListOf(),
        )

        // Board size configuration
        RadioButtonGroup(
            selectedOption = state.boardSizeOptionsMapIntToString[state.boardSize]!!,
            options = state.boardSizeOptions,
            annotation = "Board size",
            setConfiguration = { boardSize ->
                viewModel.setBoardSize(boardSize = boardSize)
            },
            disabledButtons = mutableListOf(),
        )

        when (state.gameMode) {
            "VS player" -> RadioButtonGroup(
                selectedOption = state.numberOfPlayers.toString(),
                options = state.numberOfPlayersOptions,
                annotation = "Players",
                setConfiguration = { playersNumber ->
                    viewModel.setNumberOfPlayers(playersNumber)
                },
                disabledButtons = state.disabledNumberOfPlayersOptions,
            )

            "VS computer" -> RadioButtonGroup(
                selectedOption = state.playerFigure,
                options = state.turnOptions,
                annotation = "Play for",
                setConfiguration = { playerFigure ->
                    viewModel.setPlayerFigure(playerFigure)
                },
                disabledButtons = mutableListOf(),
                images = state.figureImages,
                showImages = true
            )

            else -> RadioButtonGroup(
                selectedOption = state.numberOfPlayers.toString(),
                options = state.numberOfPlayersOptions,
                annotation = "Players",
                setConfiguration = { playersNumber ->
                    viewModel.setNumberOfPlayers(playersNumber)
                },
                disabledButtons = state.disabledNumberOfPlayersOptions,
            )
        }
    }


    ConstraintLayout {

        val button = createRef()

        Button(onClick = {
            viewModel.restartGame()
            closeSettingsWindow()
        },
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom, margin = 120.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Text(text = "Play", fontSize = 46.sp)
        }
    }
}

@Preview
@Composable
fun SettingWindowPreview() {
    SettingsWindow(closeSettingsWindow = {})
}
