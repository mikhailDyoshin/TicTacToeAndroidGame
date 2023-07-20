package com.example.tictactoegamecompose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.tictactoegamecompose.bl.Game

@SuppressLint("MutableCollectionMutableState", "UnrememberedMutableState")
@Composable
fun SettingsWindow(
    closeSettingsWindow: () -> Unit,
    game: Game,
) {

    val settings = remember {
        mutableStateMapOf(
            "mode" to "VS player",
            "size" to "3x3",
            "players" to "2",
            "playerFigure" to "x"
        )
    }

    var disabledButtons: MutableList<String> by remember {
        mutableStateOf(mutableListOf())
    }

    val figureImages: List<Int> = listOf(R.drawable.cross, R.drawable.circle)

    val boardSizeOptions: List<String> = listOf("3x3", "5x5", "7x7")

    when {
        settings["mode"] == "VS computer" -> {
            disabledButtons = mutableListOf("2", "3", "4")
            settings["players"] = "2"
        }

        settings["size"] == "3x3" -> {
            disabledButtons = mutableListOf("3", "4")
            settings["players"] = "2"
        }

        settings["size"] == "5x5" -> {
            disabledButtons = mutableListOf("4")
            if (settings["players"] == "4") settings["players"] = "2"
        }

        else -> disabledButtons = mutableListOf()
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Game mode configuration
        RadioButtonGroup(
            options = listOf("VS player", "VS computer"),
            annotation = "Game mode",
            setConfiguration = { mode ->
                settings["mode"] = mode
            },
            disabledButtons = mutableListOf(),
        )

        RadioButtonGroup(
            options = boardSizeOptions,
            annotation = "Board size",
            setConfiguration = { boardSize ->
                settings["size"] = boardSize
            },
            disabledButtons = mutableListOf(),
        )

        when (settings["mode"]) {
            "VS player" -> RadioButtonGroup(
                options = listOf("2", "3", "4"),
                annotation = "Players",
                setConfiguration = { playersNumber ->
                    settings["players"] = playersNumber
                },
                disabledButtons = disabledButtons,
            )

            "VS computer" -> RadioButtonGroup(
                options = listOf("x", "o"),
                annotation = "Play for",
                setConfiguration = { playerFigure ->
                    settings["playerFigure"] = playerFigure
                },
                disabledButtons = mutableListOf(),
                images = figureImages,
                showImages = true
            )

            else -> RadioButtonGroup(
                options = listOf("2", "3", "4"),
                annotation = "Players",
                setConfiguration = { playersNumber ->
                    settings["players"] = playersNumber
                },
                disabledButtons = disabledButtons,
            )
        }
    }


    ConstraintLayout {

        val button = createRef()

        Button(onClick = {
            game.gameSettings.selectGameConfigurations(settings)
            game.setGameConfigurations()
            game.restartGame()
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
    SettingsWindow(closeSettingsWindow = {}, game = Game())
}
