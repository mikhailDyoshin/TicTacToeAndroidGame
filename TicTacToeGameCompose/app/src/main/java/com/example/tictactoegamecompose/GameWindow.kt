import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.tictactoegamecompose.R
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun GameWindow(
    size: Int,
    players: Int,
    openSettingWindow: () -> Unit,
    AIfigure: String,
    gameMode: String,
    gameOverWindow: (winner: String, score: MutableMap<String, Int>, images: Map<String, Int>) -> Unit
) {

    var clearBoard: Boolean by remember {
        mutableStateOf(false)
    }

    ConstraintLayout {

        val settingsButton = createRef()
        val restartButton = createRef()


        IconButton(onClick = { openSettingWindow() },
            modifier = Modifier.constrainAs(settingsButton) {
                top.linkTo(parent.top, margin = 5.dp)
                start.linkTo(parent.start, margin = 5.dp)
            }) {
            Image(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings",
                modifier = Modifier.size(32.dp)
            )
        }

        IconButton(onClick = { clearBoard = true },
            modifier = Modifier.constrainAs(restartButton) {
                top.linkTo(parent.top, margin = 5.dp)
                end.linkTo(parent.end, margin = 5.dp)
            }) {
            Image(
                painter = painterResource(id = R.drawable.restart),
                contentDescription = "Restart",
                modifier = Modifier.size(32.dp)
            )
        }

    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Board(
            size = size,
            numberOfPlayers = players,
            clearBoard = clearBoard,
            resetClearBoard = { clearBoard = false },
            AIturn = AIfigure,
            gameMode = gameMode,
            showGameOverWindow = { winner, score, images -> gameOverWindow(winner, score, images) }
        )
    }


}
