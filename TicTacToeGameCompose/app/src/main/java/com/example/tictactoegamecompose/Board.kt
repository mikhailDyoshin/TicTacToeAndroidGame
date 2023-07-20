package com.example.tictactoegamecompose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoegamecompose.bl.Game


@SuppressLint("MutableCollectionMutableState")
@Composable
fun Board(game: Game) {

    val currentShapeImage = game.currentImage

    val score = game.score

    val size = game.getBoardSize()

    val svgColor = svgColorSetter()

    LaunchedEffect(currentShapeImage) {
        game.simulateMoveOfAI()
    }

    // -------------------- Rendering section --------------------

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Current turn
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Turn:", fontSize = 40.sp)

            Image(
                painter = painterResource(id = currentShapeImage),
                contentDescription = "Current turn",
                colorFilter = ColorFilter.tint(svgColor),
                modifier = Modifier
                    .size(56.dp)
                    .padding(top = 8.dp)
            )
        }

        // Score
        Text(text = "Score", fontSize = 30.sp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (player: String in score.keys) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = game.images[player]!!),
                        contentDescription = "Current turn",
                        modifier = Modifier.padding(top = 2.dp),
                        colorFilter = ColorFilter.tint(svgColor),
                    )
                    if (game.getGameMode() == "VS computer" && player == game.getShapeOfAI()) {
                        Text(
                            text = "(AI)",
                            fontSize = 11.sp,
                            modifier = Modifier.padding(bottom = 9.dp)
                        )
                    }
                    Text(text = ": ${score[player]}", fontSize = 20.sp)
                }
            }
        }

        Column {
            for (row in 0 until size) {
                Row {
                    for (col in 0 until size) {
                        Cell(
                            buttonCoordinates = listOf(row, col),
                            game = game,
                        )

                    }
                }
            }
        }

    }

}

@Preview
@Composable
fun BoardPreview() {
    Board(game = Game())
}