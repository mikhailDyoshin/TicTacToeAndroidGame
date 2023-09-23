package com.example.tictactoegamecompose.presentation.myApp

import com.example.tictactoegamecompose.domain.models.GameOverModel

data class MyAppState(val myAppState: GameOverModel = GameOverModel(gameOverStatus = false))