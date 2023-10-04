package com.example.tictactoegamecompose.presentation.settingsWindow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.presentation.settingsWindow.components.RadioButtonGroup

@Composable
fun SettingsWindow(
    state: SettingsWindowState,
    navigateToGame: () -> Unit,
    setGameMode: (gameMode: GameMode) -> Unit,
    setBoardSize: (boardSize: String) -> Unit,
    setNumberOfPlayers: (numberOfPlayers: String) -> Unit,
    setPlayerFigure: (figure: String) -> Unit,
    restartGame: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Game mode configuration
        RadioButtonGroup(
            selectedOption = state.gameMode.str,
            options = state.gameModeOptions,
            annotation = "Game mode",
            setConfiguration = { mode ->
                when (mode) {
                    GameMode.VS_PLAYER.str -> {
                        setGameMode(GameMode.VS_PLAYER)
                    }

                    GameMode.VS_COMPUTER.str -> {
                        setGameMode(GameMode.VS_COMPUTER)
                    }

                    else -> {
                        setGameMode(GameMode.VS_PLAYER)
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
                setBoardSize(boardSize)
            },
            disabledButtons = mutableListOf(),
        )

        // Number of players configuration
        when (state.gameMode) {
            GameMode.VS_PLAYER -> RadioButtonGroup(
                selectedOption = state.numberOfPlayers.number.toString(),
                options = state.numberOfPlayersOptions,
                annotation = "Players",
                setConfiguration = { playersNumber ->
                    setNumberOfPlayers(playersNumber)
                },
                disabledButtons = state.disabledNumberOfPlayersOptions,
            )

            GameMode.VS_COMPUTER -> RadioButtonGroup(
                selectedOption = state.playerFigure.str,
                options = state.turnOptions,
                annotation = "Play for",
                setConfiguration = { playerFigure ->
                    setPlayerFigure(playerFigure)
                },
                disabledButtons = mutableListOf(),
                images = state.figureImages,
                showImages = true
            )

        }

        // Play button
        Button(
            onClick = {
                restartGame()
                navigateToGame()
            },
        ) {
            Text(text = "Play", fontSize = 46.sp)
        }
    }

}

@Preview
@Composable
fun SettingWindowPreview() {
    SettingsWindow(
        state = SettingsWindowState(),
        navigateToGame = {},
        setGameMode = {},
        setBoardSize = {},
        setNumberOfPlayers = {},
        setPlayerFigure = {},
        restartGame = {},
    )
}
