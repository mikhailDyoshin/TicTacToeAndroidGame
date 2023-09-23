package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdateNumberOfPlayersUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(numberOfPlayers: Int) {
        gameRepository.updateNumberOfPlayers(numberOfPlayers)
    }
}