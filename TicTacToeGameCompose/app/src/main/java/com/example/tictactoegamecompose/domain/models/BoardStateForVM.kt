package com.example.tictactoegamecompose.domain.models

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode

data class BoardStateForVM(
    val currentTurn: CurrentTurnModel,
    val currentScore: ScoreModel,
    val boardSize: BoardSize,
    val gameMode: GameMode,
    val shapeOfAI: Figure,
    val imagesIDs: List<Figure>,
)