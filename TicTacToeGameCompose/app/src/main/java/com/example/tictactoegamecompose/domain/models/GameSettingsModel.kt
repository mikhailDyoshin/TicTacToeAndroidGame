package com.example.tictactoegamecompose.domain.models

data class GameSettingsModel(
    val gameMode: String,
    val boardSize: Int,
    val numberOfPlayers: Int,
    val playerFigure: String,
)