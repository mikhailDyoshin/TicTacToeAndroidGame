package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository

class UpdateGameModeUseCase(private val gameRepository: GameRepository) {

    fun execute(gameMode: String) {
        gameRepository.updateGameMode(gameMode)
    }

}