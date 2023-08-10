package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetShapeOfAIUseCase(private val gameRepository: GameRepository) {

    fun execute(): String {
        return gameRepository.getShapeOfAI()
    }

}