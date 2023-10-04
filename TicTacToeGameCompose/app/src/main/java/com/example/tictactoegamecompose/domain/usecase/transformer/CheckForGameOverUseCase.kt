package com.example.tictactoegamecompose.domain.usecase.transformer

import com.example.tictactoegamecompose.domain.models.CheckForGameOverModel
import com.example.tictactoegamecompose.domain.models.GameOverModel

class CheckForGameOverUseCase {

    private val fullBoardTracker = CheckFullBoardUseCase()

    fun execute(
        boardAndScore: CheckForGameOverModel
    ): GameOverModel {

        val board = boardAndScore.board
        val score = boardAndScore.score.score

        if (board.count() == 3) {
            if (1 in score.values || fullBoardTracker.execute(board)) {
                return GameOverModel(gameOverStatus = true)
            }

        } else {
            if (fullBoardTracker.execute(board)) {
                return GameOverModel(gameOverStatus = true)
            }
        }

        return GameOverModel(gameOverStatus = false)
    }
}