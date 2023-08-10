package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository

class UpdateNumberOfPlayersUseCase(private val gameRepository: GameRepository) {

    fun execute(numberOfPlayers: Int) {
        gameRepository.updateNumberOfPlayers(numberOfPlayers)
    }
}