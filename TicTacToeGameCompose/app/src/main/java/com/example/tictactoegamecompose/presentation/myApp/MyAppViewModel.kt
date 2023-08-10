package com.example.tictactoegamecompose.presentation.myApp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.tictactoegamecompose.data.repository.GameRepositoryImpl
import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
import com.example.tictactoegamecompose.domain.usecase.composite.HandleCreateGameUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameStatusUseCase

class MyAppViewModel {

    private val _state = mutableStateOf(MyAppState())

    val state: State<MyAppState> = _state

    private val gameStorage = OfflineGameStorage.getInstance()
    private val gameRepository = GameRepositoryImpl(gameStorage)

    init {
        getGameStatus()
    }

    fun getGameStatus() {

        val gameStatus = GetGameStatusUseCase(gameRepository = gameRepository).execute()
        if (gameStatus.gameOverStatus) {
            _state.value = MyAppState(myAppState = gameStatus)
        }

    }

    fun resetGameStatus() {

        HandleCreateGameUseCase(gameRepository = gameRepository).execute()
        val gameStatus = GetGameStatusUseCase(gameRepository = gameRepository).execute()
        _state.value = MyAppState(myAppState = gameStatus)

    }

}