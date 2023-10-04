package com.example.tictactoegamecompose.domain.usecase.getter

import com.example.tictactoegamecompose.domain.models.BoardStateForVM
import com.example.tictactoegamecompose.domain.repository.GameRepository
import javax.inject.Inject

class GetBoardStateUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute(): BoardStateForVM {

        return BoardStateForVM(
            currentTurn = gameRepository.getCurrentTurn(),
            currentScore = gameRepository.getScore(),
            boardSize = gameRepository.getBoardSize(),
            gameMode = gameRepository.getGameMode(),
            shapeOfAI = gameRepository.getShapeOfAI(),
            imagesIDs = gameRepository.getDrawableContent().shapeStrings,
        )
    }

}