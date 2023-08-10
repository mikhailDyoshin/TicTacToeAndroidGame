package com.example.tictactoegamecompose.domain.models

class GetGameOverStatisticsModel(
    val score: ScoreModel,
    val winner: WinnerModel,
    val images: ShapesModel,
    val gameMode: String,
    val figureOfAI: String,
)