package com.example.tictactoegamecompose.data.repository

import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
import com.example.tictactoegamecompose.data.storage.models.CurrentTurn
import com.example.tictactoegamecompose.data.storage.models.GameInitParameters
import com.example.tictactoegamecompose.data.storage.models.GameOver
import com.example.tictactoegamecompose.data.storage.models.GameUpdateState
import com.example.tictactoegamecompose.data.storage.models.GameUpdateStatus
import com.example.tictactoegamecompose.data.storage.models.Score
import com.example.tictactoegamecompose.data.storage.models.Shapes
import com.example.tictactoegamecompose.data.storage.models.Winner
import com.example.tictactoegamecompose.domain.models.BoardStateModel
import com.example.tictactoegamecompose.domain.models.CellModel
import com.example.tictactoegamecompose.domain.models.ClickedCellsModel
import com.example.tictactoegamecompose.domain.models.ContentBoardModel
import com.example.tictactoegamecompose.domain.models.CurrentTurnModel
import com.example.tictactoegamecompose.domain.models.GameInitDataModel
import com.example.tictactoegamecompose.domain.models.GameOverModel
import com.example.tictactoegamecompose.domain.models.GameSettingsModel
import com.example.tictactoegamecompose.domain.models.GameStatusModel
import com.example.tictactoegamecompose.domain.models.GameUpdateStateModel
import com.example.tictactoegamecompose.domain.models.GetGameOverStatisticsModel
import com.example.tictactoegamecompose.domain.models.LogicBoardModel
import com.example.tictactoegamecompose.domain.models.NumberOfPlayersModel
import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.domain.models.ShapesInGameModel
import com.example.tictactoegamecompose.domain.models.ShapesModel
import com.example.tictactoegamecompose.domain.models.WinnerModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val gameStorage: OfflineGameStorage) :
    GameRepository {


    override fun createGame(gameInitData: GameInitDataModel) {
        val gameInitParameters = GameInitParameters(
            boardSize = gameInitData.boardSize,
            players = gameInitData.players,
            numberOfPlayers = gameInitData.numberOfPlayers,
        )
        gameStorage.createGame(gameInitParameters = gameInitParameters)
    }

    override fun getLogicBoardState(): BoardStateModel {

        val gameCurrentState = gameStorage.getGameState()
        val gameSettings = gameStorage.getSettings()

        val logicBoard = gameCurrentState.board.logicBoard
        val crossedCellsCoordinates = gameCurrentState.board.crossedCellsCoordinates
        val humanPlayerShape = gameSettings.shapeOfPlayer
        val aiPlayerShape = gameSettings.shapeOfAI
        val comboNumber = gameSettings.comboNumber

        return BoardStateModel(
            board = logicBoard,
            crossedCells = crossedCellsCoordinates,
            currentTurn = humanPlayerShape,
            aiPlayerShape = aiPlayerShape,
            comboNumber = comboNumber,
        )
    }

    override fun getContentBoard(): ContentBoardModel {
        val contentBoardData = gameStorage.getGameState().board.contentBoard

        val contentBoardDomain: MutableList<MutableList<CellModel>> = mutableListOf()

        val tempRow = mutableListOf<CellModel>()

        for (row in contentBoardData) {
            tempRow.clear()
            for (cell in row) {
                tempRow.add(CellModel(crossed = cell.crossed, image = cell.image))
            }
            contentBoardDomain.add(tempRow.toMutableList())
        }

        return ContentBoardModel(board = contentBoardDomain)
    }

    override fun getScore(): ScoreModel {
        val score = gameStorage.getGameState().score

        return ScoreModel(score = score.score)
    }

    override fun getWinner(): WinnerModel {
        val winner = gameStorage.getGameState().winner

        return WinnerModel(winner = winner.winner)
    }

    override fun getGameStatus(): GameOverModel {
        val gameOver = gameStorage.getGameState().gameOver

        return GameOverModel(gameOverStatus = gameOver.gameOver)
    }

    override fun getShapesInGame(): ShapesInGameModel {
        val shapesInGame = gameStorage.getGameState().shapesInGame

        return ShapesInGameModel(shapesInGame = shapesInGame.shapesInGame)
    }

    override fun getCurrentTurn(): CurrentTurnModel {
        val currentTurn = gameStorage.getGameState().currentTurn

        return CurrentTurnModel(
            currentTurnValue = currentTurn.currentTurnValue,
            currentTurnShape = currentTurn.currentTurnShape,
            currentTurnImageID = currentTurn.currentTurnImageID,
        )
    }

    override fun getGameState() {
        TODO("Not yet implemented")
    }

    override fun getSettings() {
        TODO("Not yet implemented")
    }

    override fun getDrawableContent(): ShapesModel {
        return ShapesModel(
            shapeStrings = Shapes().shapes,
            shapeImages = Shapes().shapeImages
        )
    }

    override fun getNumberOfPlayers(): NumberOfPlayersModel {

        return NumberOfPlayersModel(numberOfPlayers = gameStorage.getSettings().numberOfPlayers)

    }

    override fun getLogicBoard(): LogicBoardModel {
        return LogicBoardModel(board = gameStorage.getGameState().board.logicBoard)
    }

    override fun getGameMode(): String {
        return gameStorage.getSettings().gameMode
    }

    override fun getShapeOfAI(): String {
        return gameStorage.getSettings().shapeOfAI
    }

    override fun getBoardSize(): Int {
        return gameStorage.getSettings().boardSize
    }

    override fun getGameOverStatistics(): GetGameOverStatisticsModel {
        return GetGameOverStatisticsModel(
            score = ScoreModel(score = gameStorage.getGameState().score.score),
            winner = WinnerModel(winner = gameStorage.getGameState().winner.winner),
            images = ShapesModel(
                shapeImages = Shapes().shapeImages,
                shapeStrings = Shapes().shapes
            ),
            gameMode = gameStorage.getSettings().gameMode,
            figureOfAI = gameStorage.getSettings().shapeOfAI,
        )
    }

    override fun getGameSettings(): GameSettingsModel {

        val currentSettings = gameStorage.getSettings()

        return GameSettingsModel(
            gameMode = currentSettings.gameMode,
            boardSize = currentSettings.boardSize,
            numberOfPlayers = currentSettings.numberOfPlayers,
            playerFigure = currentSettings.shapeOfPlayer,
        )
    }

    override fun getClickedCells(): ClickedCellsModel {
        return ClickedCellsModel(
            clickedCellsList = gameStorage.getGameState().board.clickedCellsCoordinates
        )
    }

    override fun updateGameState(gameState: GameUpdateStateModel) {

        val newScore = Score(score = gameState.score.score)

        val newTurn = CurrentTurn(
            currentTurnValue = gameState.turn.currentTurnValue,
            currentTurnShape = gameState.turn.currentTurnShape,
            currentTurnImageID = gameState.turn.currentTurnImageID,
        )

        val newState = GameUpdateState(
            clickedCellCoordinates = gameState.clickedCellCoordinates,
            crossedCellsCoordinates = gameState.crossedCellsCoordinates,
            score = newScore,
            turn = newTurn,
        )

        gameStorage.updateGameState(gameUpdateState = newState)
    }

    override fun updateGameStatus(gameStatus: GameStatusModel) {
        gameStorage.updateGameStatus(
            gameUpdateStatus = GameUpdateStatus(
                winner = Winner(winner = gameStatus.winner.winner),
                gameOver = GameOver(gameOver = gameStatus.gameOver.gameOverStatus)
            )
        )
    }

    override fun updateGameMode(gameMode: String) {
        gameStorage.updateGameMode(gameMode = gameMode)
    }

    override fun updateBoardSize(boardSize: Int) {
        gameStorage.updateBoardSize(boardSize = boardSize)
    }

    override fun updateNumberOfPlayers(numberOfPlayers: Int) {
        gameStorage.updateNumberOfPlayers(numberOfPlayers = numberOfPlayers)
    }

    override fun updatePlayerFigure(playerFigure: String) {
        gameStorage.updatePlayerFigure(playerFigure = playerFigure)
    }

    override fun updateShapeOfAI(shapeOfAI: String) {
        gameStorage.updateShapeOfAI(shapeOfAI = shapeOfAI)
    }
}