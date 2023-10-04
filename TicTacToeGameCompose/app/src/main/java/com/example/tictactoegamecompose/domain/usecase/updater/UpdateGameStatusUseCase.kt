package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.models.GameStatusModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateGameStatusUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(gameStatus: GameStatusModel) {
        gameRepository.updateGameStatus(gameStatus = gameStatus)
    }

}