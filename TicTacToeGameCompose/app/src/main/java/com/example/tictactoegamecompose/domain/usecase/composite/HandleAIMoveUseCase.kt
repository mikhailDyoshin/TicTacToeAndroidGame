package com.example.tictactoegamecompose.domain.usecase.composite

import com.example.tictactoegamecompose.domain.repository.GameRepository
import com.example.tictactoegamecompose.domain.usecase.getter.GetCurrentTurnUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameModeUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameStatusUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetLogicBoardStateUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetShapeOfAIUseCase
import com.example.tictactoegamecompose.domain.usecase.transformer.CalculateAINextMoveUseCase
import javax.inject.Inject

class HandleAIMoveUseCase @Inject constructor(gameRepository: GameRepository) {

    private val logicBoardState = GetLogicBoardStateUseCase(gameRepository).execute()

    private val calculateAINextMoveUseCase =
        CalculateAINextMoveUseCase(boardState = logicBoardState)

    private val handlePlayerMoveUseCase = HandlePlayerMoveUseCase(gameRepository = gameRepository)

    private val gameMode = GetGameModeUseCase(gameRepository = gameRepository).execute()

    private val currentTurn =
        GetCurrentTurnUseCase(gameRepository = gameRepository).execute().currentTurnShape

    private val shapeOfAI = GetShapeOfAIUseCase(gameRepository = gameRepository).execute()

    private val gameOver =
        GetGameStatusUseCase(gameRepository = gameRepository).execute().gameOverStatus

    fun execute() {

        if (gameMode == "VS computer" && currentTurn == shapeOfAI && !gameOver) {
            val clickedCellCoordinate = calculateAINextMoveUseCase.execute()

            handlePlayerMoveUseCase.execute(clickedCellCoordinate)
        }


    }

}