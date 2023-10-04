package com.example.tictactoegamecompose.domain.models

data class CheckForGameOverModel(val board: List<MutableList<String>>, val score: ScoreModel)