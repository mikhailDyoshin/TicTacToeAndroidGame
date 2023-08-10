package com.example.tictactoegamecompose.presentation.gameOverWindow

import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.domain.models.ShapesModel
import com.example.tictactoegamecompose.domain.models.WinnerModel

class GameOverWindowState(
    val score: ScoreModel = ScoreModel(score = mutableMapOf()),
    val winner: WinnerModel = WinnerModel(winner = ""),
    val images: ShapesModel = ShapesModel(shapeStrings = listOf(), shapeImages = mapOf()),
    val gameMode: String = "",
    val figureOfAI: String = "",
)