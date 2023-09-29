package com.example.tictactoegamecompose.data.storage.models

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.NumberOfPlayers

data class GameInitParameters(
    val boardSize: BoardSize,
    val players: List<Figure>,
    val numberOfPlayers: NumberOfPlayers,
)