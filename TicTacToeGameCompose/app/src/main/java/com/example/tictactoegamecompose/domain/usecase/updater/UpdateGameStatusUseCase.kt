package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.models.GameStatusModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class UpdateGameStatusUseCase(private val gameRepository: GameRepository) {

    fun execute(gameStatus: GameStatusModel) {
        gameRepository.updateGameStatus(gameStatus = gameStatus)
    }

}