package com.example.tictactoegamecompose.domain.models

import com.example.tictactoegamecompose.common.Figure

data class ScoreModel(val score: MutableMap<Figure, Int>)