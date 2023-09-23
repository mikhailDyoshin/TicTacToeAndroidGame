package com.example.tictactoegamecompose.domain.models

data class GetGameOverStatisticsModel(
    val score: ScoreModel,
    val winner: WinnerModel,
    val images: ShapesModel,
    val gameMode: String,
    val figureOfAI: String,
)