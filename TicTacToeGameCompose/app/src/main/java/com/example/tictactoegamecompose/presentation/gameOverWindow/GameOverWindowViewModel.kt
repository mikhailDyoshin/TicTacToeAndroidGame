package com.example.tictactoegamecompose.presentation.gameOverWindow

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameOverStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameOverWindowViewModel @Inject constructor(
    private val getGameOverStatisticsUseCase: GetGameOverStatisticsUseCase
): ViewModel() {

    private val _state = mutableStateOf(GameOverWindowState())

    val state: State<GameOverWindowState> = _state

    fun getGameOverStatistics() {

        val gameOverStats = getGameOverStatisticsUseCase.execute()

        _state.value = GameOverWindowState(
            score = gameOverStats.score,
            winner = gameOverStats.winner,
            images = gameOverStats.images,
            gameMode = gameOverStats.gameMode,
            figureOfAI = gameOverStats.figureOfAI,
        )

    }

}