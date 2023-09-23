package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetBoardSizeUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): Int {
        return gameRepository.getBoardSize()
    }
}