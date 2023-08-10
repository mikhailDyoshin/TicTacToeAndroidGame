package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.WinnerModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetWinnerUseCase(private val gameRepository: GameRepository) {

    fun execute(): WinnerModel {
        return gameRepository.getWinner()
    }

}