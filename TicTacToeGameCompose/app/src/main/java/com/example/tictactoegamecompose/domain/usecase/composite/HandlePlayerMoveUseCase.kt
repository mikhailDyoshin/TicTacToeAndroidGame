package com.example.tictactoegamecompose.domain.usecase.composite

import com.example.tictactoegamecompose.domain.models.CheckForGameOverModel
import com.example.tictactoegamecompose.domain.models.GameStatusModel
import com.example.tictactoegamecompose.domain.models.GameUpdateStateModel
import com.example.tictactoegamecompose.domain.models.SwitchTurnModel
import com.example.tictactoegamecompose.domain.models.WinnerModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import com.example.tictactoegamecompose.domain.usecase.getter.GetClickedCellsUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetCurrentTurnUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetLogicBoardStateUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetLogicBoardUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetScoreUseCase
import com.example.tictactoegamecompose.domain.usecase.transformer.CheckForGameOverUseCase
import com.example.tictactoegamecompose.domain.usecase.transformer.CheckForTieUseCase
import com.example.tictactoegamecompose.domain.usecase.transformer.RevealWinnerUseCase
import com.example.tictactoegamecompose.domain.usecase.transformer.SwitchTurnUseCase
import com.example.tictactoegamecompose.domain.usecase.transformer.UpdateScoreUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateGameStateUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateGameStatusUseCase

class HandlePlayerMoveUseCase(private val gameRepository: GameRepository) {

    fun execute(cellCoordinates: List<Int>) {

        val clickedCellsList = GetClickedCellsUseCase(gameRepository = gameRepository)
            .execute()
            .clickedCellsList

        if (cellCoordinates !in clickedCellsList) {

            val logicBoardState = GetLogicBoardStateUseCase(gameRepository).execute()

            val crossedCellsCoordinates = logicBoardState.crossedCells

            val score = GetScoreUseCase(gameRepository).execute()

            val currentTurn = GetCurrentTurnUseCase(gameRepository).execute()

            val newTurn = SwitchTurnUseCase().execute(
                model = SwitchTurnModel(
                    currentTurn = gameRepository.getCurrentTurn(),
                    numberOfPlayers = gameRepository.getNumberOfPlayers(),
                    shapes = gameRepository.getDrawableContent()
                )
            )

            UpdateGameStateUseCase(gameRepository).execute(
                gameUpdateState = GameUpdateStateModel(
                    clickedCellCoordinates = cellCoordinates,
                    crossedCellsCoordinates = crossedCellsCoordinates,
                    score = score,
                    turn = currentTurn,
                )
            )

            val logicBoard = GetLogicBoardUseCase(gameRepository).execute()

            val logicBoardStateUpdated = GetLogicBoardStateUseCase(gameRepository).execute()
            logicBoardStateUpdated.currentTurn = currentTurn.currentTurnShape

            val updatedScore = UpdateScoreUseCase(gameRepository).execute(logicBoardStateUpdated)

            UpdateGameStateUseCase(gameRepository).execute(
                gameUpdateState = GameUpdateStateModel(
                    clickedCellCoordinates = cellCoordinates,
                    crossedCellsCoordinates = crossedCellsCoordinates,
                    score = updatedScore,
                    turn = newTurn,
                )
            )

            val gameOver = CheckForGameOverUseCase().execute(
                boardAndScore = CheckForGameOverModel(
                    board = logicBoard.board,
                    score = updatedScore
                )
            )


            val winner = if (gameOver.gameOverStatus) {
                if (CheckForTieUseCase().execute(score = updatedScore).tieStatus) {
                    WinnerModel(winner = "")
                } else {
                    RevealWinnerUseCase().execute(score = updatedScore)
                }
            } else {
                WinnerModel(winner = "")
            }


            UpdateGameStatusUseCase(gameRepository).execute(
                gameStatus = GameStatusModel(
                    winner = winner,
                    gameOver = gameOver
                )
            )
        }

    }

}