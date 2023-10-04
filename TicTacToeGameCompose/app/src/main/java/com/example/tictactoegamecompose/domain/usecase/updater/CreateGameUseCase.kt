package com.example.tictactoegamecompose.domain.usecase.updater

import com.example.tictactoegamecompose.domain.models.GameInitDataModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class CreateGameUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(gameInitData: GameInitDataModel) {
        gameRepository.createGame(gameInitData = gameInitData)
    }

}