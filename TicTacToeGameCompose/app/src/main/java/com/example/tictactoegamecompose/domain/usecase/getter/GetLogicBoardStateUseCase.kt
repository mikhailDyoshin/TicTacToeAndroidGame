package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.BoardStateModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetLogicBoardStateUseCase(private val gameRepository: GameRepository) {

    fun execute(): BoardStateModel{
        return gameRepository.getLogicBoardState()
    }

}