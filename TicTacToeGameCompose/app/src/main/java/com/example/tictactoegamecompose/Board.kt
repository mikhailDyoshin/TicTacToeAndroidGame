import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoegamecompose.Minimax
import com.example.tictactoegamecompose.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoegamecompose.svgColorSetter


@SuppressLint("MutableCollectionMutableState")
@Composable
fun Board(
    size: Int,
    numberOfPlayers: Int,
    clearBoard: Boolean,
    resetClearBoard: () -> Unit,
    AIturn: String,
    gameMode: String,
    showGameOverWindow: (winner: String, score: MutableMap<String, Int>, images: Map<String, Int>) -> Unit
) {

    var thereIsAWinner: Boolean by remember {
        mutableStateOf(false)
    }

    // The counter is used to identify which player goes now
    var turnsCounter: Int by remember {
        mutableStateOf(0)
    }

    // List of figures for each player
    val shapes: List<String> = listOf("x", "o", "*", "+")

    // Since we are able to configure how many players can play the game
    // we need to track the number of players and their shapes.
    // The variable below is used to do it
    val shapesInGame: List<String> = shapes.subList(0, numberOfPlayers)

    // Graphical assets for players
    val images: Map<String, Int> = mapOf(
        "x" to R.drawable.cross,
        "o" to R.drawable.circle,
        "*" to R.drawable.triangle,
        "+" to R.drawable.rectangle,
    )

    // The variable indicates what shape plays now
    var currentTurn: String by remember {
        mutableStateOf("x")
    }

    // The variable indicates what shape was played a turn before now
    var previousTurn: String by remember {
        mutableStateOf("o")
    }

    // The matrix works like a clone of the board
    // tracking what cells were used and what figures they store
    val boardMatrix: List<MutableList<String>> by remember {
        mutableStateOf(createBoardMatrix(size))
    }

    // The list stores coordinates of cells that were crossed cause they formed a combo
    val crossedCells: MutableList<List<Int>> by remember {
        mutableStateOf(mutableListOf())
    }

    // The value tracks the score
    val score: MutableMap<String, Int> by remember {
        mutableStateOf(createScore(shapesInGame))
    }

    // This value defines how many figures should user place in combo to score
    val comboNumber = 3

    // The list stores coordinates of used cells
    val clickedButtonsCoordinates: MutableList<List<Int>> by remember {
        mutableStateOf(mutableListOf())
    }

    // ------------ Switching turns ------------
    if (turnsCounter >= numberOfPlayers) {
        turnsCounter = 0
    }
    currentTurn = shapesInGame[turnsCounter]

    previousTurn = if (turnsCounter - 1 < 0) {
        shapesInGame.last()
    } else {
        shapesInGame[turnsCounter - 1]
    }


    // ------------ AI section ------------

    var cellsClickedByAIorNot: MutableMap<List<Int>, Boolean> by remember {
        mutableStateOf(createMapToTrackClickedCellsOwner(boardSize = size))
    }

    if (gameMode == "VS computer") {
        val notFullBoard = !checkFullBoard(boardMatrix)
        if (currentTurn == AIturn && !thereIsAWinner && notFullBoard) {

            // AI decides what move it should do next
            val bestMove = Minimax(
                boardMatrix,
                "x",
                AIturn,
                crossedCells
            ).bestMove["index"]!!

            cellsClickedByAIorNot[bestMove] = true
        }
    }

    // ------------- Game-over section -------------
    var winner: String by remember {
        mutableStateOf("")
    }

    if (checkForWinner(boardMatrix, score)) {
        winner = getWinner(score)
        showGameOverWindow(winner, score, images)
    } else if (checkFullBoard(boardMatrix)) {
        showGameOverWindow(winner, score, images)
    }


    // ------------ Reset all game records when clearing the board ------------
    if (clearBoard) {
        clickedButtonsCoordinates.clear()
        clearBoardMatrix(boardMatrix)
        clearCrossedCells(crossedCells)
        clearScore(score)
        turnsCounter = 0
        currentTurn = "x"
        cellsClickedByAIorNot = flipTrueValues(cellsClickedByAIorNot)
        winner = ""
        resetClearBoard()
    }

    val svgColor = svgColorSetter()

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
                painter = painterResource(id = images[currentTurn]!!),
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
                Row ( verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = images[player]!!),
                        contentDescription = "Current turn",
                        modifier = Modifier.padding(top = 2.dp),
                        colorFilter = ColorFilter.tint(svgColor),
                    )
                    if (gameMode == "VS computer" && player == AIturn) {
                        Text(text = "(AI)", fontSize = 11.sp, modifier = Modifier.padding(bottom = 9.dp))
                    }
                    Text(text = ": ${score[player]}", fontSize = 20.sp)
                }
            }
        }

        // Board
        Column {
            for (row in 0 until size) {
                Row {
                    for (col in 0 until size) {
                        Cell(
                            addCoordinates = { coordinates ->
                                if (coordinates !in clickedButtonsCoordinates) {
                                    clickedButtonsCoordinates.add(coordinates)
                                    boardMatrix[coordinates[0]][coordinates[1]] = currentTurn
                                }
                            },
                            currentTurn = currentTurn,
                            changeTurn = { turnsCounter++ },
                            clearBoard = clearBoard,
                            buttonIndex = listOf(row, col),
                            checkForCombo = {
                                checkForCombo(
                                    boardMatrix,
                                    currentTurn,
                                    crossedCells,
                                    comboNumber,
                                    score
                                )
                            },
                            wasClickedByAI = cellsClickedByAIorNot[listOf(row, col)]!!,
                            checkForWinner = {
                                thereIsAWinner = checkForWinner(boardMatrix, score)
                            },
                            crossed = listOf(row, col) in crossedCells,
                            images = images,
                            gameMode = gameMode,
                            AIturn = AIturn,
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
    Board(
        size = 7,
        numberOfPlayers = 4,
        clearBoard = false,
        resetClearBoard = {},
        AIturn = "o",
        gameMode = "VS computer",
        showGameOverWindow = {winner, score, images ->  }
    )
}