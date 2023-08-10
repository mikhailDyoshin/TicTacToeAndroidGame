package com.example.tictactoegamecompose.data.storage.models

class GameUpdateState(
    val clickedCellCoordinates: List<Int>,
    val crossedCellsCoordinates: MutableList<List<Int>>,
    val score: Score,
    val turn: CurrentTurn,
)