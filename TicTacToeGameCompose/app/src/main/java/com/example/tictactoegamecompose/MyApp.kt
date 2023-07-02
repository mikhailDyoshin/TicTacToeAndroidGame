import android.annotation.SuppressLint
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@SuppressLint("MutableCollectionMutableState")
@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var showSettingsWindow: Boolean by remember {
        mutableStateOf(false)
    }

    var mode: String by remember {
        mutableStateOf("VS player")
    }

    var boardSize: Int by remember {
        mutableStateOf(3)
    }

    var playersNumber: Int by remember {
        mutableStateOf(2)
    }

    var figureOfAI: String by remember {
        mutableStateOf("o")
    }

    var winnerState: String by remember {
        mutableStateOf("")
    }

    var scoreState: MutableMap<String, Int> by remember {
        mutableStateOf(mutableMapOf())
    }

    var showGameOverWindow: Boolean by remember {
        mutableStateOf(false)
    }

    var imageAssets: Map<String, Int> by remember {
        mutableStateOf(mapOf())
    }

    Surface(modifier) {
        when {
            showSettingsWindow -> SettingsWindow(
                saveSettings = { settings ->
                    mode = settings["mode"]!!
                    boardSize = settings["size"]!!.first().digitToInt()
                    playersNumber = settings["players"]!!.first().digitToInt()
                    if (settings["playersFigure"] == "x") {
                        figureOfAI = "o"
                    } else if (settings["playerFigure"] == "o") {
                        figureOfAI = "x"
                    }
                },
                closeSettingsWindow = { showSettingsWindow = false },
            )

            showGameOverWindow -> GameOverWindow(
                winner = winnerState,
                score = scoreState,
                images = imageAssets,
                restartGame = { showGameOverWindow = false },
                figureOfAI = figureOfAI,
                gameMode = mode,
            )

            else -> GameWindow(
                size = boardSize,
                players = playersNumber,
                openSettingWindow = { showSettingsWindow = true },
                AIfigure = figureOfAI,
                gameMode = mode,
                gameOverWindow = { winner, score, images ->
                    winnerState = winner
                    scoreState = score
                    showGameOverWindow = true
                    imageAssets = images
                }
            )
        }

    }
}