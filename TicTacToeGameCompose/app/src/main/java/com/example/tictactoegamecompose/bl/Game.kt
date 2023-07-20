package com.example.tictactoegamecompose.bl

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.tictactoegamecompose.R


@SuppressLint("MutableCollectionMutableState")
class Game {

    // ----- Game configurations -----
    val gameSettings = GameSettings()

    private var gameMode = gameSettings.getGameMode()

    private var boardSize = gameSettings.getBoardSize()

    private var shapeOfPlayer = gameSettings.getShapeOfPlayer()

    private var shapeOfAI = gameSettings.getShapeOfAI()

    private var numberOfPlayers = gameSettings.getNumberOfPlayers()

    // This value defines how many figures a user should form in combo to score
    private val comboNumber = 3

    private var thereIsAWinner: Boolean = false

    // The counter is used to identify which player goes now
    private var turnsCounter by mutableStateOf(0)

    // List of figures for each player
    private val shapes: List<String> = listOf("x", "o", "*", "+")

    // Since we are able to configure how many players can play the game
    // we need to track the number of players and their shapes.
    // The variable below is used to do this.
    private var shapesInGame: List<String> = defineShapesInGame()

    // Graphical assets for players
    val images: Map<String, Int> = mapOf(
        "x" to R.drawable.cross,
        "o" to R.drawable.circle,
        "*" to R.drawable.triangle,
        "+" to R.drawable.rectangle,
    )

    // The variable indicates what shape plays now
    private var currentTurn = "x"

    // Game over flag
    var gameOver by mutableStateOf(false)

    // The matrix works like a clone of the board
    // and is responsible for content displayed inside cells (image and colors)
    var contentBoardMatrix: List<MutableList<CellModel>> by mutableStateOf(
        createContentBoardMatrix(boardSize)
    )

    // The value tracks the score
    var score: MutableMap<String, Int> by mutableStateOf(createScore(shapesInGame))

    var currentImage by mutableStateOf(images[currentTurn]!!)

    var winner by mutableStateOf("")

    // The list stores coordinates of cells that were crossed since they formed a combo
    private val crossedCells: MutableList<List<Int>> = mutableListOf()

    // The matrix works like a clone of the board
    // tracking what cells were used and what shapes they store
    private var boardMatrix: List<MutableList<String>> = createBoardMatrix(boardSize)

    // The list stores coordinates of used cells
    private val clickedCellsCoordinates: MutableList<List<Int>> = mutableListOf()

    // Trackers
    private val comboTracker = ComboTracker()

    private val winTracker = WinTracker()

    fun makePlayerMove(coordinates: List<Int>) {
        if (gameMode == "VS computer" ) {
            if (currentTurn != shapeOfAI) {
                changeStateOfTheGame(coordinates)
            }
        } else {
            changeStateOfTheGame(coordinates)
        }
    }

    private fun changeStateOfTheGame(coordinates: List<Int>) {
        addClickedCellCoordinates(coordinates)
        changeScore()
        markCrossedCells()
        checkForWin()
        changeTurn()
    }

    fun getBoardSize(): Int {
        return boardSize
    }

    fun getGameMode(): String {
        return gameMode
    }

    fun getShapeOfAI(): String {
        return shapeOfAI
    }

    fun restartGame() {
        clickedCellsCoordinates.clear()
        boardMatrix = createBoardMatrix(boardSize)
        contentBoardMatrix = createContentBoardMatrix(boardSize)
        clearCrossedCells(crossedCells)
        shapesInGame = defineShapesInGame()
        score = createScore(shapesInGame)
        turnsCounter = 0
        setCurrentTurn()
        setCurrentShapeImage()
        winner = ""
        gameOver = false
        if (gameMode == "VS computer") {
            simulateMoveOfAI()
        }
    }

    fun simulateMoveOfAI() {
        val notFullBoard = !winTracker.checkFullBoard(boardMatrix)
        if (gameMode == "VS computer" && currentTurn == shapeOfAI && !thereIsAWinner && notFullBoard) {
            // AI decides what move it should do next
            val bestMoveOfAI = Minimax(
                boardMatrix,
                shapeOfPlayer,
                shapeOfAI,
                crossedCells,
                comboNumber,
            ).bestMove["index"]!!

            changeStateOfTheGame(bestMoveOfAI)

        }
    }

    fun setGameConfigurations() {
        gameMode = gameSettings.getGameMode()
        boardSize = gameSettings.getBoardSize()
        numberOfPlayers = gameSettings.getNumberOfPlayers()
        shapeOfPlayer = gameSettings.getShapeOfPlayer()
        shapeOfAI = gameSettings.getShapeOfAI()
    }

    private fun defineShapesInGame(): List<String> {
        return shapes.subList(0, numberOfPlayers)
    }

    private fun setCurrentTurn() {
        currentTurn = shapesInGame[turnsCounter]
    }

    private fun setCurrentShapeImage() {
        currentImage = images[currentTurn]!!
    }

    private fun addClickedCellCoordinates(coordinates: List<Int>) {
        if (coordinates !in clickedCellsCoordinates) {
            clickedCellsCoordinates.add(coordinates)
            putCurrentShapeOnBoard(coordinates)
            putCurrentImageOnBoard(coordinates)
        }
    }

    private fun putCurrentShapeOnBoard(coordinates: List<Int>) {
        boardMatrix[coordinates[0]][coordinates[1]] = currentTurn
    }

    private fun putCurrentImageOnBoard(coordinates: List<Int>) {
        contentBoardMatrix[coordinates[0]][coordinates[1]].image = currentImage
    }

    private fun markCrossedCells() {
        for (cellCoordinates in crossedCells) {
            contentBoardMatrix[cellCoordinates[0]][cellCoordinates[1]].crossed = true
        }
    }

    private fun changeTurn() {
        turnsCounter = (turnsCounter + 1) % numberOfPlayers
        setCurrentTurn()
        setCurrentShapeImage()
    }

    private fun changeScore() {
        if (comboTracker.checkForCombo(boardMatrix, currentTurn, crossedCells, comboNumber)) {
            score[currentTurn] = score[currentTurn]!! + 1
        }
    }

    private fun checkForWin() {
        if (winTracker.checkForWinner(boardMatrix, score)) {
            winner = winTracker.getWinner(score)
            gameOver = true
        } else if (winTracker.checkFullBoard(boardMatrix)) {
            gameOver = true
        }
    }

    // ------------------------------- Handy functions ---------------------------------------------

    private fun createBoardMatrix(boardSize: Int): List<MutableList<String>> {

        return List(boardSize) { MutableList(boardSize) { " " } }
    }

    private fun createContentBoardMatrix(boardSize: Int): List<MutableList<CellModel>> {
        return List(boardSize) { MutableList(boardSize) { CellModel(crossed=false , image=0) } }
    }

    private fun createScore(keys: List<String>): MutableMap<String, Int> {
        return keys.associateWith { 0 }.toMutableMap()
    }

    private fun clearCrossedCells(crossedCells: MutableList<List<Int>>) {
        crossedCells.clear()
    }

}
