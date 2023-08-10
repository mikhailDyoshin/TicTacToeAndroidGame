package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.CurrentTurnModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetCurrentTurnUseCase(private val gameRepository: GameRepository) {

    fun execute(): CurrentTurnModel {
        return gameRepository.getCurrentTurn()
    }

}