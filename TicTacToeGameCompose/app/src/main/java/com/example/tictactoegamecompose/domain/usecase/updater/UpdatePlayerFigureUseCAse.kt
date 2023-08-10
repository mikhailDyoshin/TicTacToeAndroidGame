package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.repository.GameRepository

class UpdatePlayerFigureUseCAse(private val gameRepository: GameRepository) {

    fun execute(playerFigure: String) {
        gameRepository.updatePlayerFigure(playerFigure)
    }
}