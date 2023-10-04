package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateGameModeUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(gameMode: GameMode) {
        gameRepository.updateGameMode(gameMode)
    }

}