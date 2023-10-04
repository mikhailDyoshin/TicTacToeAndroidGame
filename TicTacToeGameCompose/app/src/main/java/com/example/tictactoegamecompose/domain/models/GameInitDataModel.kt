package com.example.tictactoegamecompose.domain.models

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.NumberOfPlayers

data class GameInitDataModel(
    val boardSize: BoardSize,
    val players: List<Figure>,
    val numberOfPlayers: NumberOfPlayers,
)