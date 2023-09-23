package com.example.tictactoegamecompose.data.storage.models

data class GameSettings(
    var gameMode: String = "VS player",
    var boardSize: Int = 3,
    var numberOfPlayers: Int = 2,
    var shapeOfPlayer: String = "x",
    var shapeOfAI: String = "o",
    var shapesInGame: ShapesInGame = ShapesInGame(shapesInGame = listOf("x", "o")),
    val comboNumber: Int = 3,
)