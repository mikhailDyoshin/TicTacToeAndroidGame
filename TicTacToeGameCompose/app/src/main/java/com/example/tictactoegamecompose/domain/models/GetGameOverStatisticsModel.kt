package com.example.tictactoegamecompose.domain.models

import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode

data class GetGameOverStatisticsModel(
    val score: ScoreModel,
    val winner: WinnerModel,
    val shapes: ShapesModel,
    val gameMode: GameMode,
    val figureOfAI: Figure,
)