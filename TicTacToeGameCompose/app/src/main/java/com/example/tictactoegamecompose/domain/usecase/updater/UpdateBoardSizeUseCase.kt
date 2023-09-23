package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateBoardSizeUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(boardSize: Int) {
        gameRepository.updateBoardSize(boardSize)
    }
}