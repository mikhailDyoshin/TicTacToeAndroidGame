package com.example.tictactoegamecompose.data.storage

import com.example.tictactoegamecompose.data.storage.models.Board
import com.example.tictactoegamecompose.data.storage.models.Cell
import com.example.tictactoegamecompose.data.storage.models.CurrentTurn
import com.example.tictactoegamecompose.data.storage.models.GameCurrentState
import com.example.tictactoegamecompose.data.storage.models.GameInitParameters
import com.example.tictactoegamecompose.data.storage.models.GameOver
import com.example.tictactoegamecompose.data.storage.models.GameSettings
import com.example.tictactoegamecompose.data.storage.models.GameUpdateState
import com.example.tictactoegamecompose.data.storage.models.GameUpdateStatus
import com.example.tictactoegamecompose.data.storage.models.Score
import com.example.tictactoegamecompose.data.storage.models.Shapes
import com.example.tictactoegamecompose.data.storage.models.ShapesInGame
import com.example.tictactoegamecompose.data.storage.models.Winner
import org.testng.annotations.Test

class OfflineGameStorage : GameStorage {

    private val gameSettings = GameSettings()

    init {
        createGame(
            gameInitParameters = GameInitParameters(
                boardSize = gameSettings.boardSize,
                players = gameSettings.shapesInGame.shapesInGame,
                numberOfPlayers = gameSettings.numberOfPlayers
            )
        )
    }

//    companion object {
//        // Static instance variable
//        @Volatile
//        private var instance: OfflineGameStorage? = null
//
//        // Method to get the single instance of the class
//        fun getInstance(): OfflineGameStorage {
//            return instance ?: synchronized(this) {
//                instance ?: OfflineGameStorage().also { instance = it }
//            }
//        }
//    }

    private lateinit var gameCurrentState: GameCurrentState



    override fun createGame(gameInitParameters: GameInitParameters) {

        gameCurrentState = GameCurrentState(
            board = Board(
                contentBoard = listOf(),
                logicBoard = listOf(),
                clickedCellsCoordinates = mutableListOf(),
                crossedCellsCoordinates = mutableListOf(),
            ),
            shapesInGame = ShapesInGame(shapesInGame = listOf()),
            currentTurn = CurrentTurn(
                currentTurnValue = 0,
                currentTurnShape = "",
                currentTurnImageID = 0,
            ),
            score = Score(score = mutableMapOf()),
            winner = Winner(winner = ""),
            gameOver = GameOver(gameOver = false),
        )

        val boardSize = gameInitParameters.boardSize
        val players = gameInitParameters.players
        val numberOfPlayers = gameInitParameters.numberOfPlayers

        createBoard(boardSize = boardSize)
        createScore(players = players)
        createShapesInGame(numberOfPlayers = numberOfPlayers)
        createCurrentTurn()
        createWinner()

    }

    override fun getGameState(): GameCurrentState {
        return gameCurrentState
    }

    override fun getSettings(): GameSettings {
        return gameSettings
    }

    override fun updateGameState(gameUpdateState: GameUpdateState) {
        val clickedCellCoordinates = gameUpdateState.clickedCellCoordinates
        val crossedCellsCoordinates = gameUpdateState.crossedCellsCoordinates
        val score = gameUpdateState.score
        val turn = gameUpdateState.turn


        updateClickedCell(clickedCellCoordinates = clickedCellCoordinates)
        updateCrossedCells(crossedCellsCoordinates = crossedCellsCoordinates)
        updateScore(score = score)

        updateTurn(turn = turn)

    }

    override fun updateGameStatus(gameUpdateStatus: GameUpdateStatus) {
        val winner = gameUpdateStatus.winner
        val gameOverStatus = gameUpdateStatus.gameOver

        updateWinner(winner = winner)
        updateGameOverStatus(gameOver = gameOverStatus)
    }

    override fun updateGameMode(gameMode: String) {
        gameSettings.gameMode = gameMode
    }

    override fun updateBoardSize(boardSize: Int) {
        gameSettings.boardSize = boardSize
    }

    override fun updateNumberOfPlayers(numberOfPlayers: Int) {
        gameSettings.numberOfPlayers = numberOfPlayers
    }

    override fun updatePlayerFigure(playerFigure: String) {
        gameSettings.shapeOfPlayer = playerFigure
    }

    override fun updateShapeOfAI(shapeOfAI: String) {
        gameSettings.shapeOfAI = shapeOfAI
    }

//    override fun updateSettings(settings: GameSettings) {
//        gameSettings.gameMode = settings.gameMode
//        gameSettings.boardSize = settings.boardSize
//        gameSettings.numberOfPlayers = settings.numberOfPlayers
//        gameSettings.shapeOfPlayer = settings.shapeOfPlayer
//        gameSettings.shapeOfAI = settings.shapeOfAI
//    }


    // Create
    private fun createBoard(boardSize: Int) {
        val board = gameCurrentState.board

        board.contentBoard = createContentBoardMatrix(boardSize = boardSize)
        board.logicBoard = createLogicBoardMatrix(boardSize = boardSize)
        board.clickedCellsCoordinates = mutableListOf()
        board.crossedCellsCoordinates = mutableListOf()
    }

    private fun createScore(players: List<String>) {
        val scoreObj = gameCurrentState.score
        scoreObj.score = players.associateWith { 0 }.toMutableMap()
    }

    private fun createShapesInGame(numberOfPlayers: Int) {
        val shapesInGameObj = gameCurrentState.shapesInGame
        shapesInGameObj.shapesInGame = Shapes().shapes.subList(0, numberOfPlayers)
    }

    private fun createCurrentTurn() {
        val currentTurnObj = gameCurrentState.currentTurn

        currentTurnObj.currentTurnValue = 0
        currentTurnObj.currentTurnShape = Shapes().shapes[0]
        currentTurnObj.currentTurnImageID = Shapes().shapeImages["x"]!!

    }

    private fun createWinner() {
        val winnerObj = gameCurrentState.winner

        winnerObj.winner = ""
    }

    private fun createContentBoardMatrix(boardSize: Int): List<MutableList<Cell>> {
        return List(boardSize) { MutableList(boardSize) { Cell(crossed = false, image = 0) } }
    }

    private fun createLogicBoardMatrix(boardSize: Int): List<MutableList<String>> {

        return List(boardSize) { MutableList(boardSize) { " " } }
    }

    // Update
    private fun updateClickedCell(clickedCellCoordinates: List<Int>) {

        val row = clickedCellCoordinates[0]
        val col = clickedCellCoordinates[1]

        val logicBoard = gameCurrentState.board.logicBoard
        val contentBoard = gameCurrentState.board.contentBoard

        logicBoard[row][col] = gameCurrentState.currentTurn.currentTurnShape
        contentBoard[row][col].image = gameCurrentState.currentTurn.currentTurnImageID


        gameCurrentState.board.clickedCellsCoordinates.add(clickedCellCoordinates)
    }

    private fun updateCrossedCells(crossedCellsCoordinates: MutableList<List<Int>>) {

        val contentBoardMatrix = gameCurrentState.board.contentBoard

        for (pair in crossedCellsCoordinates.toList()) {
            val row = pair[0]
            val col = pair[1]
            contentBoardMatrix[row][col].crossed = true
//            gameCurrentState.board.crossedCellsCoordinates.add(pair)
        }

    }

    private fun updateScore(score: Score) {
        val scoreObj = gameCurrentState.score
        scoreObj.score = score.score
    }

    private fun updateTurn(turn: CurrentTurn) {
        val currentTurnObj = gameCurrentState.currentTurn

        currentTurnObj.currentTurnValue = turn.currentTurnValue
        currentTurnObj.currentTurnShape = turn.currentTurnShape
        currentTurnObj.currentTurnImageID = turn.currentTurnImageID
    }

    private fun updateWinner(winner: Winner) {
        val winnerObj = gameCurrentState.winner
        winnerObj.winner = winner.winner
    }

    private fun updateGameOverStatus(gameOver: GameOver) {
        val gameOverObj = gameCurrentState.gameOver
        gameOverObj.gameOver = gameOver.gameOver
    }

}

class OfflineGameStorageTest {

    private val gameStorage = OfflineGameStorage()

    private val initGameParams = GameInitParameters(
        boardSize = 3,
        players = listOf("x", "o"),
        numberOfPlayers = 2,
    )

    private val shapes = Shapes()

    private val initGameState = GameCurrentState(
        board = Board(
            logicBoard = listOf(
                mutableListOf(" ", " ", " "),
                mutableListOf(" ", " ", " "),
                mutableListOf(" ", " ", " "),
            ),
            contentBoard = listOf(
                mutableListOf(Cell(), Cell(), Cell()),
                mutableListOf(Cell(), Cell(), Cell()),
                mutableListOf(Cell(), Cell(), Cell()),
            ),
            clickedCellsCoordinates = mutableListOf(),
            crossedCellsCoordinates = mutableListOf(),
        ),

        shapesInGame = ShapesInGame(shapesInGame = initGameParams.players),

        currentTurn = CurrentTurn(
            currentTurnValue = 0,
            currentTurnShape = shapes.shapes[0],
            currentTurnImageID = shapes.shapeImages["x"]!!
        ),

        score = Score(score = mutableMapOf("x" to 0, "o" to 0)),

        winner = Winner(winner = ""),
        gameOver = GameOver(gameOver = false),
    )

    private val gameUpdateState = GameUpdateState(
        clickedCellCoordinates = listOf(0, 0),
        crossedCellsCoordinates = mutableListOf(listOf(0, 0), listOf(1, 1), listOf(2, 2)),
        score = Score(score = mutableMapOf("x" to 1, "o" to 0)),
        turn = CurrentTurn(
            currentTurnValue = 1,
            currentTurnShape = shapes.shapes[1],
            currentTurnImageID = shapes.shapeImages["o"]!!
        ),

    )

    private val gameUpdateStatus = GameUpdateStatus(
        winner = Winner(winner = "x"),
        gameOver = GameOver(gameOver = true),
    )

    private val updatedGameState = GameCurrentState(
        board = Board(
            logicBoard = listOf(
                mutableListOf("x", " ", " "),
                mutableListOf(" ", " ", " "),
                mutableListOf(" ", " ", " "),
            ),
            contentBoard = listOf(
                mutableListOf(
                    Cell(crossed = true, image = shapes.shapeImages["x"]!!),
                    Cell(), Cell()
                ),
                mutableListOf(Cell(), Cell(crossed = true), Cell()),
                mutableListOf(Cell(), Cell(), Cell(crossed = true)),
            ),
            clickedCellsCoordinates = mutableListOf(gameUpdateState.clickedCellCoordinates),
            crossedCellsCoordinates = gameUpdateState.crossedCellsCoordinates,
        ),

        shapesInGame = initGameState.shapesInGame,

        currentTurn = gameUpdateState.turn,

        score = gameUpdateState.score,

        winner = gameUpdateStatus.winner,

        gameOver = gameUpdateStatus.gameOver,
    )

    private val newSettings = GameSettings(
        gameMode = "VS computer",
        boardSize = 7,
        numberOfPlayers = 3,
        shapeOfPlayer = "o",
        shapeOfAI = "x",
    )

    @Test
    fun createGameTest() {

        gameStorage.createGame(gameInitParameters = initGameParams)

        val gameCurrentState = gameStorage.getGameState()

        checkCurrentState(state = gameCurrentState, sample = initGameState)

    }

    @Test
    fun updateGameStateTest() {

        gameStorage.createGame(gameInitParameters = initGameParams)

        gameStorage.updateGameState(gameUpdateState = gameUpdateState)

        gameStorage.updateGameStatus(gameUpdateStatus = gameUpdateStatus)

        val gameCurrentState = gameStorage.getGameState()

        checkCurrentState(state = gameCurrentState, sample = updatedGameState)

    }

    @Test
    fun updateSettingsTest() {

        gameStorage.createGame(gameInitParameters = initGameParams)

//        gameStorage.updateSettings(settings = newSettings)

        val gameSettings = gameStorage.getSettings()

        assert(gameSettings.gameMode == newSettings.gameMode)
        assert(gameSettings.boardSize == newSettings.boardSize)
        assert(gameSettings.numberOfPlayers == newSettings.numberOfPlayers)
        assert(gameSettings.shapeOfPlayer == newSettings.shapeOfPlayer)
        assert(gameSettings.shapeOfAI == newSettings.shapeOfAI)

    }

    private fun checkCurrentState(state: GameCurrentState, sample: GameCurrentState) {

        val board = sample.board
        val turn = sample.currentTurn

        checkContentBoard(state, board.contentBoard)
        assert(state.board.logicBoard == board.logicBoard)
        assert(state.board.clickedCellsCoordinates == board.clickedCellsCoordinates)
        assert(state.board.crossedCellsCoordinates == board.crossedCellsCoordinates)

        // Check shapes in game
        assert(state.shapesInGame.shapesInGame == sample.shapesInGame.shapesInGame)

        // Check current turn
        assert(state.currentTurn.currentTurnValue == turn.currentTurnValue)
        assert(state.currentTurn.currentTurnShape == turn.currentTurnShape)
        assert(state.currentTurn.currentTurnImageID == turn.currentTurnImageID)

        // Check score
        assert(state.score.score == sample.score.score)

        // Check winner
        assert(state.winner.winner == sample.winner.winner)

        // Check gameOver
        assert(state.gameOver.gameOver == sample.gameOver.gameOver)
    }

    private fun checkContentBoard(
        gameCurrentState: GameCurrentState,
        sample: List<MutableList<Cell>>
    ) {
        for ((rowIndex, row) in sample.withIndex()) {
            for ((colIndex, cell) in row.withIndex()) {
                assert(
                    gameCurrentState.board.contentBoard[rowIndex][colIndex].crossed == cell.crossed
                )
                assert(
                    gameCurrentState.board.contentBoard[rowIndex][colIndex].image == cell.image
                )
            }
        }
    }
}