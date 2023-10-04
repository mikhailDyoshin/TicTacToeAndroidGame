package com.example.tictactoegamecompose.data.storage

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.common.NumberOfPlayers
import com.example.tictactoegamecompose.data.storage.models.GameCurrentState
import com.example.tictactoegamecompose.data.storage.models.GameInitParameters
import com.example.tictactoegamecompose.data.storage.models.GameSettings
import com.example.tictactoegamecompose.data.storage.models.GameUpdateState
import com.example.tictactoegamecompose.data.storage.models.GameUpdateStatus

interface GameStorage {

    // Create
    fun createGame(gameInitParameters: GameInitParameters)


    // Read
    fun getGameState(): GameCurrentState

    fun getSettings(): GameSettings


    // Update
    fun updateGameState(gameUpdateState: GameUpdateState)

    fun updateGameStatus(gameUpdateStatus: GameUpdateStatus)

    fun updateGameMode(gameMode: GameMode)

    fun updateBoardSize(boardSize: BoardSize)

    fun updateNumberOfPlayers(numberOfPlayers: NumberOfPlayers)

    fun updatePlayerFigure(playerFigure: Figure)

    fun updateShapeOfAI(shapeOfAI: Figure)


}