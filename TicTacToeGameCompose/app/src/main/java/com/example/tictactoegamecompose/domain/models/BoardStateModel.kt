package com.example.tictactoegamecompose.domain.models

data class BoardStateModel(
    val board: List<MutableList<String>>,
    var currentTurn: String,
    val aiPlayerShape: String,
    val crossedCells: MutableList<List<Int>>,
    val comboNumber: Int,
)
