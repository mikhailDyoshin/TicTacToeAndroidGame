package com.example.tictactoegamecompose.data.storage.models

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.common.NumberOfPlayers

data class GameSettings(
    var gameMode: GameMode = GameMode.VS_PLAYER,
    var boardSize: BoardSize = BoardSize.SMALL,
    var numberOfPlayers: NumberOfPlayers = NumberOfPlayers.TWO,
    var shapeOfPlayer: Figure = Figure.CROSS,
    var shapeOfAI: Figure = Figure.CIRCLE,
    var shapesInGame: List<Figure> = listOf(Figure.CROSS, Figure.CIRCLE),
    val comboNumber: Int = 3,
)