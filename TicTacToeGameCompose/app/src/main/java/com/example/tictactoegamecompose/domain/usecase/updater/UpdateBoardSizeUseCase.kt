package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateBoardSizeUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(boardSize: BoardSize) {
        gameRepository.updateBoardSize(boardSize)
    }
}