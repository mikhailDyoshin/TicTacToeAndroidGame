package com.example.tictactoegamecompose.domain.models

data class GameInitDataModel(
    val boardSize: Int,
    val players: List<String>,
    val numberOfPlayers: Int,
)