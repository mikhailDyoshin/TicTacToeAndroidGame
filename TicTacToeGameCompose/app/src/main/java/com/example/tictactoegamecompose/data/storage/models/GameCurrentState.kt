package com.example.tictactoegamecompose.data.storage.models

data class GameCurrentState(
    val board: Board,
    val shapesInGame: ShapesInGame,
    val currentTurn: CurrentTurn,
    val score: Score,
    val winner: Winner,
    val gameOver: GameOver,
)