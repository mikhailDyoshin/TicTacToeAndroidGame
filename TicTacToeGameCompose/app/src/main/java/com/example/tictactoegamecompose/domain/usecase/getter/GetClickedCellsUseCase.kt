package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.ClickedCellsModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetClickedCellsUseCase(private val gameRepository: GameRepository) {

    fun execute(): ClickedCellsModel {
        return gameRepository.getClickedCells()
    }
}