package com.example.tictactoegamecompose.data.storage.models

import com.example.tictactoegamecompose.R

data class Shapes(
    val shapes: List<String> = listOf("x", "o", "*", "+"),
    val shapeImages: Map<String, Int> = mapOf(
        "x" to R.drawable.cross,
        "o" to R.drawable.circle,
        "*" to R.drawable.triangle,
        "+" to R.drawable.rectangle,
    )
)