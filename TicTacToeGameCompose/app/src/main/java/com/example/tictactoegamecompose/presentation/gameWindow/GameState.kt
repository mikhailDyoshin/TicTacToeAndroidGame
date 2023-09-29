package com.example.tictactoegamecompose.presentation.gameWindow

import com.example.tictactoegamecompose.domain.models.GameOverModel
import com.example.tictactoegamecompose.presentation.board.BoardState

data class GameState(var gameOver: Boolean = false, var boardState: BoardState = BoardState())