package com.example.tictactoegamecompose.data.storage.models

data class GameInitParameters(
    val boardSize: Int,
    val players: List<String>,
    val numberOfPlayers: Int,
)