package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.ContentBoardModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetContentBoardUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): ContentBoardModel {
        return gameRepository.getContentBoard()
    }

}