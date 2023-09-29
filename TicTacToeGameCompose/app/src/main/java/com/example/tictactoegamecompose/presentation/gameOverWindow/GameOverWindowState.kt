package com.example.tictactoegamecompose.presentation.gameOverWindow

import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.domain.models.ScoreModel
import com.example.tictactoegamecompose.domain.models.ShapesModel
import com.example.tictactoegamecompose.domain.models.WinnerModel

data class GameOverWindowState(
    val score: ScoreModel = ScoreModel(score = mutableMapOf()),
    val winner: WinnerModel = WinnerModel(winner = null),
    val images: ShapesModel = ShapesModel(shapeStrings = listOf()),
    val gameMode: GameMode = GameMode.VS_PLAYER,
    val figureOfAI: Figure = Figure.CIRCLE,
)