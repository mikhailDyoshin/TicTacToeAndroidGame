package com.example.tictactoegamecompose.domain.models

import com.example.tictactoegamecompose.common.Figure

data class CurrentTurnModel(
    var currentTurnValue: Int,
    var currentTurnShape: Figure,
)