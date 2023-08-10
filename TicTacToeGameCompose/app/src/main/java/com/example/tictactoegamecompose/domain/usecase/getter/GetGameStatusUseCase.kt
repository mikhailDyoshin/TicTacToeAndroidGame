package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.GameOverModel
import com.example.tictactoegamecompose.domain.repository.GameRepository


class GetGameStatusUseCase(private val gameRepository: GameRepository) {

    fun execute(): GameOverModel {
        return gameRepository.getGameStatus()
    }

}