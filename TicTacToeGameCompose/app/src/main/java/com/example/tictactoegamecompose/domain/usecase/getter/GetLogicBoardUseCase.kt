package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.LogicBoardModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetLogicBoardUseCase(private val gameRepository: GameRepository) {
    fun execute(): LogicBoardModel {
        return gameRepository.getLogicBoard()
    }
}