package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class UpdatePlayerFigureUseCAse @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(playerFigure: String) {
        gameRepository.updatePlayerFigure(playerFigure)
    }
}