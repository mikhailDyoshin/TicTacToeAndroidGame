package com.example.tictactoegamecompose.domain.models

import com.example.tictactoegamecompose.common.Figure

data class BoardStateModel(
    val board: List<MutableList<String>>,
    var currentTurn: Figure,
    val aiPlayerShape: Figure,
    val crossedCells: MutableList<List<Int>>,
    val comboNumber: Int,
)
