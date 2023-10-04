package com.example.tictactoegamecompose.domain.repository

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.common.NumberOfPlayers
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

    fun getGameMode(): GameMode

    fun getShapeOfAI(): Figure

    fun getBoardSize(): BoardSize

    fun getGameOverStatistics(): GetGameOverStatisticsModel

    fun getGameSettings(): GameSettingsModel

    fun getClickedCells(): ClickedCellsModel


    // Update
    fun updateGameState(gameState: GameUpdateStateModel)

    fun updateGameStatus(gameStatus: GameStatusModel)

    fun updateGameMode(gameMode: GameMode)

    fun updateBoardSize(boardSize: BoardSize)

    fun updateNumberOfPlayers(numberOfPlayers: NumberOfPlayers)

    fun updatePlayerFigure(playerFigure: Figure)

    fun updateShapeOfAI(shapeOfAI: Figure)


}