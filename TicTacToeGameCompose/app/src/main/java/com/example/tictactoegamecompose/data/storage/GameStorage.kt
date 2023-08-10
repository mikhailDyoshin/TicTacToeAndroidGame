package com.example.tictactoegamecompose.data.storage

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

    fun updateGameMode(gameMode: String)

    fun updateBoardSize(boardSize: Int)

    fun updateNumberOfPlayers(numberOfPlayers: Int)

    fun updatePlayerFigure(playerFigure: String)

    fun updateShapeOfAI(shapeOfAI: String)


}