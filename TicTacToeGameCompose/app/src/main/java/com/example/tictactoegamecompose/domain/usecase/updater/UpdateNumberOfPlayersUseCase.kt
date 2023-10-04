package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.common.NumberOfPlayers
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateNumberOfPlayersUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(numberOfPlayers: NumberOfPlayers) {
        gameRepository.updateNumberOfPlayers(numberOfPlayers)
    }
}