package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.models.GameUpdateStateModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateGameStateUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(gameUpdateState: GameUpdateStateModel) {
        gameRepository.updateGameState(gameUpdateState)
    }

}