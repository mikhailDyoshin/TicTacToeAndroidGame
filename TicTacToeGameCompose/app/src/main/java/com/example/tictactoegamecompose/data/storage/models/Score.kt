package com.example.tictactoegamecompose.data.storage.models

import com.example.tictactoegamecompose.common.Figure

data class Score(var score: MutableMap<Figure, Int>)