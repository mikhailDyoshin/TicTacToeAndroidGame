package com.example.tictactoegamecompose.domain.models

data class SwitchTurnModel(
    val currentTurn: CurrentTurnModel,
    val numberOfPlayers: NumberOfPlayersModel,
    val shapes: ShapesModel,
)