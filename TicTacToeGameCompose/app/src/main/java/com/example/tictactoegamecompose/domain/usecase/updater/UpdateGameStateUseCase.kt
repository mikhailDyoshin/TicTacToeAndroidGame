package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.models.GameUpdateStateModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class UpdateGameStateUseCase(private val gameRepository: GameRepository) {

    fun execute(gameUpdateState: GameUpdateStateModel) {
        gameRepository.updateGameState(gameUpdateState)
    }

}