package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.GetGameOverStatisticsModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetGameOverStatisticsUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): GetGameOverStatisticsModel {
        return gameRepository.getGameOverStatistics()
    }

}