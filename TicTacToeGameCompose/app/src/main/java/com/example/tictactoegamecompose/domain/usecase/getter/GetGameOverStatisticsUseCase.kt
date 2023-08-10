package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.GetGameOverStatisticsModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetGameOverStatisticsUseCase(private val gameRepository: GameRepository) {

    fun execute(): GetGameOverStatisticsModel {
        return gameRepository.getGameOverStatistics()
    }

}