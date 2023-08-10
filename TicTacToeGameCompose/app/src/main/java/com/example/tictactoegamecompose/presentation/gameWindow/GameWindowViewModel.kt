package com.example.tictactoegamecompose.presentation.gameWindow


import com.example.tictactoegamecompose.data.repository.GameRepositoryImpl
import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
import com.example.tictactoegamecompose.domain.usecase.composite.HandleCreateGameUseCase


class GameWindowViewModel {

    private val gameStorage = OfflineGameStorage.getInstance()
    private val gameRepository = GameRepositoryImpl(gameStorage)

    fun restartGame() {
        HandleCreateGameUseCase(gameRepository = gameRepository).execute()
    }

}