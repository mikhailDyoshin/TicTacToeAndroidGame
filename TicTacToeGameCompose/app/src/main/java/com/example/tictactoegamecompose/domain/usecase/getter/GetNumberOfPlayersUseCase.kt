package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.NumberOfPlayersModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetNumberOfPlayersUseCase(private val gameRepository: GameRepository) {

    fun execute(): NumberOfPlayersModel {
        return gameRepository.getNumberOfPlayers()
    }

}