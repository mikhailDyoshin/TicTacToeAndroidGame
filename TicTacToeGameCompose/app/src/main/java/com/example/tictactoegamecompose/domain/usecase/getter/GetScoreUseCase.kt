package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetScoreUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): ScoreModel {
        return gameRepository.getScore()
    }

}