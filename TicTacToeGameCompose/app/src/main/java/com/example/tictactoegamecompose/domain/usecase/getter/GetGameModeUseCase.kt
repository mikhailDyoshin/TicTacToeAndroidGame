package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetGameModeUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): String {
        return gameRepository.getGameMode()
    }
}