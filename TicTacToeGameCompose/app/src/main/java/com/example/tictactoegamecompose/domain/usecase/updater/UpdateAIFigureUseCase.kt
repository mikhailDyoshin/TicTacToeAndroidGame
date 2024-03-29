package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateAIFigureUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(shapeOfAI: Figure) {
        gameRepository.updateShapeOfAI(shapeOfAI = shapeOfAI)
    }
}