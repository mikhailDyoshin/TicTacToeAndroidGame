package com.example.tictactoegamecompose.presentation.board

import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.presentation.cell.CellState

data class BoardState(
    val currentTurnImageID: Int = 0,
    val currentScore: ScoreModel = ScoreModel(score = mutableMapOf()),
    val boardSize: Int = 0,
    val gameMode: String = "",
    val shapeOfAI: String = "",
    val imagesIDs: Map<String, Int> = mutableMapOf(),
    val contentBoard: List<List<CellState>> = listOf(),
)