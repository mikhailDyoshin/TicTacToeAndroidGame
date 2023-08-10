package com.example.tictactoegamecompose.domain.usecase.transformer

import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.domain.models.TieModel

class CheckForTieUseCase {

    fun execute(score: ScoreModel): TieModel {

        val scorePoints = score.score.values.toList()

        val firstPlayerPoints = scorePoints[0]

        val tieStatus = TieModel(tieStatus = false)

        for (points in scorePoints) {
            if (points != firstPlayerPoints) {
                return tieStatus
            }
        }

        tieStatus.tieStatus = true
        return tieStatus
    }

}