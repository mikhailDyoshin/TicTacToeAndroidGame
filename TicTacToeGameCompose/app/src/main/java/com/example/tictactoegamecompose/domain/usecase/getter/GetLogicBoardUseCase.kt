package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.LogicBoardModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetLogicBoardUseCase @Inject constructor(private val gameRepository: GameRepository) {
    fun execute(): LogicBoardModel {
        return gameRepository.getLogicBoard()
    }
}