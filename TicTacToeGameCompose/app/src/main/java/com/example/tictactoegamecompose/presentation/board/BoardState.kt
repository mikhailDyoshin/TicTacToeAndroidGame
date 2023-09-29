package com.example.tictactoegamecompose.presentation.board

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.presentation.cell.CellState

data class BoardState(
    val currentTurnImageID: Int = 0,
    val currentScore: ScoreModel = ScoreModel(score = mutableMapOf()),
    val boardSize: BoardSize = BoardSize.SMALL,
    val gameMode: GameMode = GameMode.VS_PLAYER,
    val shapeOfAI: Figure = Figure.CIRCLE,
    val imagesIDs: List<Figure> = listOf(),
    val contentBoard: List<List<CellState>> = listOf(),
)