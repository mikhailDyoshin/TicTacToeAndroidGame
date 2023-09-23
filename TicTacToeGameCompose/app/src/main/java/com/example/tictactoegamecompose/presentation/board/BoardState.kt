package com.example.tictactoegamecompose.presentation.board

import com.example.tictactoegamecompose.domain.models.ScoreModel

data class BoardState(
    val currentTurnImageID: Int = 0,
    val currentScore: ScoreModel = ScoreModel(score = mutableMapOf()),
    val boardSize: Int = 0,
    val gameMode: String = "",
    val shapeOfAI: String = "",
    val imagesIDs: Map<String, Int> = mutableMapOf(),
)