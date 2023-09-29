package com.example.tictactoegamecompose.domain.usecase.transformer

import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.domain.models.WinnerModel

class RevealWinnerUseCase {

    fun execute(score: ScoreModel): WinnerModel {
        return WinnerModel(winner = score.score.maxByOrNull { it.value }?.key)
    }

}
