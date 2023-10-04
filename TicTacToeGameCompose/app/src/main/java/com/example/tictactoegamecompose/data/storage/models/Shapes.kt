package com.example.tictactoegamecompose.data.storage.models

import com.example.tictactoegamecompose.R
import com.example.tictactoegamecompose.common.Figure

data class Shapes(
    val shapes: List<Figure> = Figure.values().map { it },
)