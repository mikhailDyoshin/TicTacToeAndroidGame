package com.example.tictactoegamecompose.presentation.board

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
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.presentation.cell.CellScreen
import com.example.tictactoegamecompose.presentation.previewStates.PreviewStates
import com.example.tictactoegamecompose.presentation.svgColorSetter


@Composable
fun BoardScreen(
    state: BoardState,
    simulateMoveOfAI: () -> Unit,
    updateState: () -> Unit,
    changeGameState: (row: Int, col: Int) -> Unit,
) {


    val currentShapeImage = state.currentTurnImageID

    val score = state.currentScore.score

    val size = state.boardSize.size

    val contentBoard = state.contentBoard

    val svgColor = svgColorSetter()

    LaunchedEffect(state) {
        if (state.gameMode == GameMode.VS_COMPUTER) {
            if (state.currentTurnImageID == state.shapeOfAI.imageID) {
                simulateMoveOfAI()
                updateState()
            }

        }
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
            for (player: Figure in score.keys) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = player.imageID),
                        contentDescription = "Current turn",
                        modifier = Modifier.padding(top = 2.dp),
                        colorFilter = ColorFilter.tint(svgColor),
                    )
                    if (state.gameMode == GameMode.VS_COMPUTER && player == state.shapeOfAI) {
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
                        CellScreen(row = row,
                            col = col,
                            cellState = contentBoard[row][col],
                            updateBoard = { updateState() },
                            changeGameState = { y, x -> changeGameState(y, x) })

                    }
                }
            }
        }

    }

}

@Preview
@Composable
fun BoardPreview() {
    BoardScreen(state = PreviewStates.BOARD_STATE,
        simulateMoveOfAI = {},
        updateState = {},
        changeGameState = { _, _ -> })
}