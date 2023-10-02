package com.example.tictactoegamecompose.presentation.previewStates

import com.example.tictactoegamecompose.R
import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.domain.models.ShapesModel
import com.example.tictactoegamecompose.domain.models.WinnerModel
import com.example.tictactoegamecompose.presentation.board.BoardState
import com.example.tictactoegamecompose.presentation.cell.CellState
import com.example.tictactoegamecompose.presentation.gameOverWindow.GameOverWindowState
import com.example.tictactoegamecompose.presentation.gameWindow.GameState

object PreviewStates {

    val BOARD_STATE = BoardState(
        currentTurnImageID = R.drawable.cross,
        currentScore = ScoreModel(
            score = mutableMapOf(
                Figure.CROSS to 0,
                Figure.CIRCLE to 1,
                Figure.TRIANGLE to 1,
                Figure.RECTANGLE to 1
            )
        ),
        boardSize = BoardSize.BIG,
        gameMode = GameMode.VS_PLAYER,
        shapeOfAI = Figure.CIRCLE,
        imagesIDs = Figure.values().map { it },
        contentBoard = listOf(
            listOf(
                CellState(imageID = R.drawable.circle, crossed = true),
                CellState(imageID = R.drawable.circle, crossed = true),
                CellState(imageID = R.drawable.circle, crossed = true),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
            ),
            listOf(
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
            ),
            listOf(
                CellState(imageID = R.drawable.cross, crossed = false),
                CellState(imageID = R.drawable.cross, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = R.drawable.cross, crossed = false),
                CellState(imageID = R.drawable.triangle, crossed = true),
                CellState(imageID = R.drawable.triangle, crossed = true),
                CellState(imageID = R.drawable.triangle, crossed = true),
            ),
            listOf(
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = R.drawable.rectangle, crossed = true),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
            ),
            listOf(
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = R.drawable.rectangle, crossed = true),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
            ),
            listOf(
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = R.drawable.rectangle, crossed = true),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
            ),
            listOf(
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
                CellState(imageID = 0, crossed = false),
            ),

            )

    )

    val GAME_STATE = GameState(
        gameOver = false,
        boardState = BOARD_STATE,
    )

    val GAVE_OVER_STATE = GameOverWindowState(
        score = ScoreModel(
            score = mutableMapOf(
                Figure.CROSS to 3,
                Figure.CIRCLE to 2,
                Figure.TRIANGLE to 1,
                Figure.RECTANGLE to 0
            )
        ),
        winner = WinnerModel(winner = Figure.CROSS),
        images = ShapesModel(
            shapeStrings = listOf(
                Figure.CROSS,
                Figure.CIRCLE,
                Figure.TRIANGLE,
                Figure.RECTANGLE
            )
        ),
        gameMode = GameMode.VS_COMPUTER,
        figureOfAI = Figure.CIRCLE,
    )
}