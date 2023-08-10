package com.example.tictactoegamecompose.domain.repository

import com.example.tictactoegamecompose.domain.models.BoardStateModel
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
import com.example.tictactoegamecompose.domain.models.ShapesModel
import com.example.tictactoegamecompose.domain.models.ShapesInGameModel
import com.example.tictactoegamecompose.domain.models.WinnerModel


interface GameRepository {

    // Create
    fun createGame(gameInitData: GameInitDataModel)


    // Read
    fun getLogicBoardState(): BoardStateModel

    fun getContentBoard(): ContentBoardModel

    fun getScore(): ScoreModel

    fun getWinner(): WinnerModel

    fun getGameStatus(): GameOverModel

    fun getShapesInGame(): ShapesInGameModel

    fun getCurrentTurn(): CurrentTurnModel

    fun getGameState()

    fun getSettings()

    fun getDrawableContent(): ShapesModel

    fun getNumberOfPlayers(): NumberOfPlayersModel

    fun getLogicBoard(): LogicBoardModel

    fun getGameMode(): String

    fun getShapeOfAI(): String

    fun getBoardSize(): Int

    fun getGameOverStatistics(): GetGameOverStatisticsModel

    fun getGameSettings(): GameSettingsModel

    fun getClickedCells(): ClickedCellsModel


    // Update
    fun updateGameState(gameState: GameUpdateStateModel)

    fun updateGameStatus(gameStatus: GameStatusModel)

//    fun updateSettings(settings: UpdateSettingsModel)

    fun updateGameMode(gameMode: String)

    fun updateBoardSize(boardSize: Int)

    fun updateNumberOfPlayers(numberOfPlayers: Int)

    fun updatePlayerFigure(playerFigure: String)

    fun updateShapeOfAI(shapeOfAI: String)


}