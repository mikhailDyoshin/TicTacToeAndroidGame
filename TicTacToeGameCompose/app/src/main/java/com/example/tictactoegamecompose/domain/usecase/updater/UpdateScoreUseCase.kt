package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.models.BoardStateModel
import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import com.example.tictactoegamecompose.domain.usecase.getter.GetScoreUseCase
import com.example.tictactoegamecompose.domain.usecase.transformer.CheckForComboUseCase
import javax.inject.Inject

class UpdateScoreUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(boardState: BoardStateModel): ScoreModel {

        val comboTracker = CheckForComboUseCase()
        val score = GetScoreUseCase(gameRepository).execute()
        val currentTurn = boardState.currentTurn

        if (comboTracker.execute(boardState)) {
            score.score[currentTurn] = score.score[currentTurn]!! + 1
        }

        return score
    }

}