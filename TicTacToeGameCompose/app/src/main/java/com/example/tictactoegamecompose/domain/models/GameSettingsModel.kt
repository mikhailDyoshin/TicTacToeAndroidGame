package com.example.tictactoegamecompose.domain.models

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.common.NumberOfPlayers

data class GameSettingsModel(
    val gameMode: GameMode,
    val boardSize: BoardSize,
    val numberOfPlayers: NumberOfPlayers,
    val playerFigure: Figure,
)