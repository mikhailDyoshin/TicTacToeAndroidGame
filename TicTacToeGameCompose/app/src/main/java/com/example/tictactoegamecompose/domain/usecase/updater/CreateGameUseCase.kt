package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.models.GameInitDataModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class CreateGameUseCase(private val gameRepository: GameRepository) {

    fun execute(gameInitData: GameInitDataModel) {
        gameRepository.createGame(gameInitData = gameInitData)
    }

}