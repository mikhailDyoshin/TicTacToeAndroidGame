package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateGameModeUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(gameMode: String) {
        gameRepository.updateGameMode(gameMode)
    }

}