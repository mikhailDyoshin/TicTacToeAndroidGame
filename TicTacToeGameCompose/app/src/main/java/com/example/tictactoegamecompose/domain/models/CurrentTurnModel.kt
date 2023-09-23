package com.example.tictactoegamecompose.domain.models

data class CurrentTurnModel(
    var currentTurnValue: Int,
    var currentTurnShape: String,
    var currentTurnImageID: Int,
)