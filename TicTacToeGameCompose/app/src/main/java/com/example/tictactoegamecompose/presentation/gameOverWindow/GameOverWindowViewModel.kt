package com.example.tictactoegamecompose.presentation.gameOverWindow

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.tictactoegamecompose.data.repository.GameRepositoryImpl
import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameOverStatisticsUseCase


class GameOverWindowViewModel {

    private val _state = mutableStateOf(GameOverWindowState())

    val state: State<GameOverWindowState> = _state

    private val gameStorage = OfflineGameStorage.getInstance()
    private val gameRepository = GameRepositoryImpl(gameStorage)

    init {
        getGameOverStatistics()
    }

    private fun getGameOverStatistics() {

        val gameOverStats = GetGameOverStatisticsUseCase(gameRepository = gameRepository).execute()
        _state.value = GameOverWindowState(
            score = gameOverStats.score,
            winner = gameOverStats.winner,
            images = gameOverStats.images,
            gameMode = gameOverStats.gameMode,
            figureOfAI = gameOverStats.figureOfAI,
        )

    }

}