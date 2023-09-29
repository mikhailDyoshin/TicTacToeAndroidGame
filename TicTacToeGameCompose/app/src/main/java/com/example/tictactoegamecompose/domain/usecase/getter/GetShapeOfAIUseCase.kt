package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetShapeOfAIUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): Figure {
        return gameRepository.getShapeOfAI()
    }

}