package com.example.tictactoegamecompose.presentation.settingsWindow

import com.example.tictactoegamecompose.R

data class SettingsWindowState(
    var gameMode: String = "VS player",
    var boardSize: Int = 3,
    var numberOfPlayers: Int = 2,
    var playerFigure: String = "x",
    val figureImages: List<Int> = listOf(R.drawable.cross, R.drawable.circle),
    val disabledNumberOfPlayersOptions: MutableList<String> = mutableListOf("2", "3", "4"),
    val gameModeOptions: List<String> = listOf("VS player", "VS computer"),
    val boardSizeOptions: List<String> = listOf("3x3", "5x5", "7x7"),
    val boardSizeOptionsMapStringToInt: Map<String, Int> = mapOf("3x3" to 3, "5x5" to 5, "7x7" to 7),
    val boardSizeOptionsMapIntToString: Map<Int, String> = mapOf(3 to "3x3", 5 to "5x5", 7 to "7x7"),
    val numberOfPlayersOptions: List<String> = listOf("2", "3", "4"),
    val turnOptions: List<String> = listOf("x", "o"),

    )