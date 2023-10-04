package com.example.tictactoegamecompose.data.storage.models


data class Board(
    var contentBoard: List<MutableList<Cell>>,
    var logicBoard: List<MutableList<String>>,
    var crossedCellsCoordinates: MutableList<List<Int>>,
    var clickedCellsCoordinates: MutableList<List<Int>>,
)