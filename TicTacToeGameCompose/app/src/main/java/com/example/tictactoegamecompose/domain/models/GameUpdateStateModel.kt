package com.example.tictactoegamecompose.domain.models


data class GameUpdateStateModel(
    val clickedCellCoordinates: List<Int>,
    val crossedCellsCoordinates: MutableList<List<Int>>,
    val score: ScoreModel,
    val turn: CurrentTurnModel,
)