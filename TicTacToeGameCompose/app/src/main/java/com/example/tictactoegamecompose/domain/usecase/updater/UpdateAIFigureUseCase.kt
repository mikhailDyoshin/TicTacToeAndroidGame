package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateAIFigureUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(shapeOfAI: String) {
        gameRepository.updateShapeOfAI(shapeOfAI = shapeOfAI)
    }
}