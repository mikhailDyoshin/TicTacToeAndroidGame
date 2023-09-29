package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.data.storage.models.Board
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetBoardSizeUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): BoardSize {
        return gameRepository.getBoardSize()
    }
}