package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetGameModeUseCase(private val gameRepository: GameRepository) {

    fun execute(): String {
        return gameRepository.getGameMode()
    }
}