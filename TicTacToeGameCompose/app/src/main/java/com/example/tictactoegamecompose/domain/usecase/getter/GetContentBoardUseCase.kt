package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.ContentBoardModel
import com.example.tictactoegamecompose.domain.repository.GameRepository

class GetContentBoardUseCase(private val gameRepository: GameRepository) {

    fun execute(): ContentBoardModel {
        return gameRepository.getContentBoard()
    }

}