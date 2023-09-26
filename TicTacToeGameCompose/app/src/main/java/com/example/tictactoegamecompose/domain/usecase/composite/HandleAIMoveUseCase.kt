package com.example.tictactoegamecompose.domain.usecase.composite

import com.example.tictactoegamecompose.domain.repository.GameRepository
import com.example.tictactoegamecompose.domain.usecase.getter.GetCurrentTurnUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameModeUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameStatusUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetLogicBoardStateUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetShapeOfAIUseCase
import com.example.tictactoegamecompose.domain.usecase.transformer.CalculateAINextMoveUseCase
import javax.inject.Inject

class HandleAIMoveUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute() {

            val gameMode = GetGameModeUseCase(gameRepository = gameRepository).execute()

            val currentTurn =
                GetCurrentTurnUseCase(gameRepository = gameRepository).execute().currentTurnShape

            val shapeOfAI = GetShapeOfAIUseCase(gameRepository = gameRepository).execute()

            val gameOver =
                GetGameStatusUseCase(gameRepository = gameRepository).execute().gameOverStatus

        if (gameMode == "VS computer" && currentTurn == shapeOfAI && !gameOver) {

            val logicBoardState = GetLogicBoardStateUseCase(gameRepository).execute()

            val calculateAINextMoveUseCase =
                CalculateAINextMoveUseCase(boardState = logicBoardState)

            val handlePlayerMoveUseCase = HandlePlayerMoveUseCase(gameRepository = gameRepository)

            val clickedCellCoordinate = calculateAINextMoveUseCase.execute()

            handlePlayerMoveUseCase.execute(clickedCellCoordinate)
        }


    }

}