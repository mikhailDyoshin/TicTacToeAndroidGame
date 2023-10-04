package com.example.tictactoegamecompose.data.storage.models

import com.example.tictactoegamecompose.common.Figure

data class CurrentTurn(
    var currentTurnValue: Int,
    var currentTurnShape: Figure,
    )