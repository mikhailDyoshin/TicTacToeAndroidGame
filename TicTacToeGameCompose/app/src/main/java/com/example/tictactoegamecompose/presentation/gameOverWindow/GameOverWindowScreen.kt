package com.example.tictactoegamecompose.presentation.gameOverWindow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.presentation.svgColorSetter

@Composable
fun GameOverWindowScreen(
    restartGame: () -> Unit,
    viewModel: GameOverWindowViewModel = viewModel(),
) {

    viewModel.getGameOverStatistics()

    // Style values
    val h1FontSize = 44
    val h2FontSize = 30
    val textFontSize = 24
    val buttonFontSize = 40
    val spaceBetween = 60

    val state = viewModel.state.value

    val score = state.score.score
    val winner = state.winner.winner
    val gameMode = state.gameMode
    val figureOfAI = state.figureOfAI

    val sortedScore = score.toList().sortedByDescending { (_, value) -> value }.toMap()

    val svgColor = svgColorSetter()


    // -------------------- Rendering Section --------------------
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        // Winner
        if (winner == null) {
            Text(text = "Tie", fontSize = h1FontSize.sp)
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = winner.imageID),
                    contentDescription = "Current turn",
                    modifier = Modifier
                        .size(72.dp)
                        .padding(top = 8.dp),
                    colorFilter = ColorFilter.tint(svgColor)
                )
                Text(text = "won!", fontSize = h1FontSize.sp)
            }

        }

        // Score
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = spaceBetween.dp)
        ) {
            Text(text = "Score", fontSize = h2FontSize.sp)
            for ((index, player) in sortedScore.keys.withIndex()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Text(
                        text = "${index + 1}.",
                        fontSize = textFontSize.sp
                    )
                    Image(
                        painter = painterResource(id = player.imageID),
                        contentDescription = "Current turn",
                        modifier = Modifier.padding(top = 3.dp),
                        colorFilter = ColorFilter.tint(svgColor)
                    )
                    if (gameMode == GameMode.VS_COMPUTER && player == figureOfAI) {
                        Text(
                            text = "(AI)",
                            fontSize = 11.sp,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }
                    Text(
                        text = ": ${sortedScore[player]}",
                        fontSize = textFontSize.sp
                    )
                }
            }
        }

        // Replay button
        Button(
            onClick = { restartGame() },
            modifier = Modifier.padding(top = spaceBetween.dp)
        ) {
            Text(
                text = "Replay", fontSize = buttonFontSize.sp,
            )
        }
    }
}

@Preview
@Composable
fun GameOverWindowPreview() {
    GameOverWindowScreen(
        restartGame = {},
    )
}