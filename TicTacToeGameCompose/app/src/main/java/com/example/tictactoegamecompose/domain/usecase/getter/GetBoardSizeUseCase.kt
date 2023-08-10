package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetBoardSizeUseCase(private val gameRepository: GameRepository) {

    fun execute(): Int {
        return gameRepository.getBoardSize()
    }
}