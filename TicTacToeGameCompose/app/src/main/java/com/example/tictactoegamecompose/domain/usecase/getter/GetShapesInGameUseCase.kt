package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.ShapesInGameModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetShapesInGameUseCase(private val gameRepository: GameRepository) {

    fun execute(): ShapesInGameModel {
        return gameRepository.getShapesInGame()
    }

}