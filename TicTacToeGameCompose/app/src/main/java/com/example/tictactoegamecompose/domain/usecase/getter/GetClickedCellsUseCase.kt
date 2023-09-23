package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.ClickedCellsModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetClickedCellsUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): ClickedCellsModel {
        return gameRepository.getClickedCells()
    }
}