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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.presentation.settingsWindow.components.RadioButtonGroup

@SuppressLint("MutableCollectionMutableState", "UnrememberedMutableState")
@Composable
fun SettingsWindow(
    closeSettingsWindow: () -> Unit,
    viewModel: SettingsWindowViewModel = viewModel(),
) {

    val state = viewModel.state.value
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Game mode configuration
        RadioButtonGroup(
            selectedOption = state.gameMode.str,
            options = state.gameModeOptions,
            annotation = "Game mode",
            setConfiguration = { mode ->
                when (mode) {
                    "VS player" -> {
                        viewModel.setGameMode(gameMode = GameMode.VS_PLAYER)
                    }
                    "VS computer" -> {
                        viewModel.setGameMode(gameMode = GameMode.VS_COMPUTER)
                    }
                    else -> {
                        viewModel.setGameMode(gameMode = GameMode.VS_PLAYER)
                    }
            }

            },
            disabledButtons = mutableListOf(),
        )

        // Board size configuration
        RadioButtonGroup(
            selectedOption = state.boardSizeOptionsMapIntToString[state.boardSize.size]!!,
            options = state.boardSizeOptions,
            annotation = "Board size",
            setConfiguration = { boardSize ->
                viewModel.setBoardSize(boardSizeStr = boardSize)
            },
            disabledButtons = mutableListOf(),
        )

        when (state.gameMode) {
            GameMode.VS_PLAYER -> RadioButtonGroup(
                selectedOption = state.numberOfPlayers.number.toString(),
                options = state.numberOfPlayersOptions,
                annotation = "Players",
                setConfiguration = { playersNumber ->
                    viewModel.setNumberOfPlayers(playersNumber)
                },
                disabledButtons = state.disabledNumberOfPlayersOptions,
            )

            GameMode.VS_COMPUTER -> RadioButtonGroup(
                selectedOption = state.playerFigure.str,
                options = state.turnOptions,
                annotation = "Play for",
                setConfiguration = { playerFigure ->
                    viewModel.setPlayerFigure(playerFigure)
                },
                disabledButtons = mutableListOf(),
                images = state.figureImages,
                showImages = true
            )

//            else -> RadioButtonGroup(
//                selectedOption = state.numberOfPlayers.toString(),
//                options = state.numberOfPlayersOptions,
//                annotation = "Players",
//                setConfiguration = { playersNumber ->
//                    viewModel.setNumberOfPlayers(playersNumber)
//                },
//                disabledButtons = state.disabledNumberOfPlayersOptions,
//            )
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
