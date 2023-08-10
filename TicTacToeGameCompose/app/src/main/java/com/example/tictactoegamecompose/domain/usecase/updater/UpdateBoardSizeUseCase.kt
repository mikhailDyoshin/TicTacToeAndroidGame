package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository

class UpdateBoardSizeUseCase(private val gameRepository: GameRepository) {

    fun execute(boardSize: Int) {
        gameRepository.updateBoardSize(boardSize)
    }
}