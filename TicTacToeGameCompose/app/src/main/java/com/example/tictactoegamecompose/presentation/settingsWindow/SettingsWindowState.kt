package com.example.tictactoegamecompose.presentation.settingsWindow

import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.common.NumberOfPlayers

data class SettingsWindowState(
    var gameMode: GameMode = GameMode.VS_PLAYER,
    var boardSize: BoardSize = BoardSize.SMALL,
    var numberOfPlayers: NumberOfPlayers = NumberOfPlayers.TWO,
    var playerFigure: Figure = Figure.CROSS,
    val figureImages: List<Int> = listOf(Figure.CROSS.imageID, Figure.CIRCLE.imageID),
    val disabledNumberOfPlayersOptions: MutableList<String> = mutableListOf("2", "3", "4"),
    val gameModeOptions: List<String> = GameMode.values().map { it.str },
    val boardSizeOptions: List<String> = listOf("3x3", "5x5", "7x7"),
    val boardSizeOptionsMapStringToInt: Map<String, BoardSize> = mapOf(
        "3x3" to BoardSize.SMALL, "5x5" to BoardSize.MIDDLE, "7x7" to BoardSize.BIG
    ),
    val boardSizeOptionsMapIntToString: Map<Int, String> = mapOf(
        3 to "3x3", 5 to "5x5", 7 to "7x7"
    ),
    val numberOfPlayersOptions: List<String> = NumberOfPlayers.values().map { it.number.toString() },
    val turnOptions: List<String> = listOf("x", "o"),

    )