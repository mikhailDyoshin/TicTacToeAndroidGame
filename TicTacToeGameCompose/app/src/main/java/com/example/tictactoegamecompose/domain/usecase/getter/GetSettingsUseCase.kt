package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.GameSettingsModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): GameSettingsModel {
        return gameRepository.getGameSettings()
    }

}