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
import com.example.tictactoegamecompose.R

@SuppressLint("MutableCollectionMutableState", "UnrememberedMutableState")
@Composable
fun SettingsWindow(
    closeSettingsWindow: () -> Unit,
    saveSettings: (MutableMap<String, String>) -> Unit,
) {


    val defaultSettings = remember {
        mutableStateMapOf(
            "mode" to "VS player",
            "size" to "3x3",
            "players" to "2",
            "playerFigure" to "x"
        )
    }

    val settings = remember {
        mutableStateMapOf(
            "mode" to "VS player",
            "size" to "3x3",
            "players" to "2",
            "playerFigure" to "x"
        )
    }

    // Autosaving settings
    if (settings != defaultSettings) {
        saveSettings(settings)
    }

    var disabledButtons: MutableList<String> by remember {
        mutableStateOf(mutableListOf())
    }

    val figureImages: List<Int> = listOf(R.drawable.cross, R.drawable.circle)


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
            setConfiguration = { mode -> settings["mode"] = mode },
            disabledButtons = mutableListOf(),
        )

        // Board size configuration
        RadioButtonGroup(
            options = listOf("3x3", "5x5", "7x7"),
            annotation = "Board size",
            setConfiguration = { boardSize -> settings["size"] = boardSize },
            disabledButtons = mutableListOf(),
        )

        if (settings["mode"] == "VS player") {
            // Number of players configuration
            RadioButtonGroup(
                options = listOf("2", "3", "4"),
                annotation = "Players",
                setConfiguration = { playersNumber -> settings["players"] = playersNumber },
                disabledButtons = disabledButtons,
            )
        } else if (settings["mode"] == "VS computer") {
            // Player's figure configuration when game mode is set to "VS computer"
            RadioButtonGroup(
                options = listOf("x", "o"),
                annotation = "Play for",
                setConfiguration = { playerFigure -> settings["playerFigure"] = playerFigure },
                disabledButtons = disabledButtons,
                images = figureImages,
                showImages = true
            )
        }

    }


    ConstraintLayout {

        val button = createRef()

        Button(onClick = {
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
    SettingsWindow(closeSettingsWindow = {}, saveSettings = {})
}
