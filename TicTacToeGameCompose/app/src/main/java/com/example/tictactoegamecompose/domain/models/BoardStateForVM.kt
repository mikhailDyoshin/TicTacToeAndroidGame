package com.example.tictactoegamecompose.domain.models

data class BoardStateForVM(
    val currentTurn: CurrentTurnModel,
    val currentScore: ScoreModel,
    val boardSize: Int,
    val gameMode: String,
    val shapeOfAI: String,
    val imagesIDs: Map<String, Int>,
)