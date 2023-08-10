package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository

class UpdateAIFigureUseCase(private val gameRepository: GameRepository) {

    fun execute(shapeOfAI: String) {
        gameRepository.updateShapeOfAI(shapeOfAI = shapeOfAI)
    }
}