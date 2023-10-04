package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.NumberOfPlayersModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetNumberOfPlayersUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): NumberOfPlayersModel {
        return gameRepository.getNumberOfPlayers()
    }

}