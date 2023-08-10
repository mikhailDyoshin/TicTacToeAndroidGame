package com.example.tictactoegamecompose.presentation.board

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.data.repository.GameRepositoryImpl
import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
import com.example.tictactoegamecompose.domain.usecase.composite.HandleAIMoveUseCase


class BoardViewModel: ViewModel() {

    private val _state = mutableStateOf(BoardState())

    val state: State<BoardState> = _state

    private val gameStorage = OfflineGameStorage.getInstance()
    private val gameRepository = GameRepositoryImpl(gameStorage)

    init {
        getState()
    }


    fun getState() {

        val currentTurn = gameRepository.getCurrentTurn()
        val currentScore = gameRepository.getScore()
        val boardSize = gameRepository.getBoardSize()
        val gameMode = gameRepository.getGameMode()
        val shapeOfAI = gameRepository.getShapeOfAI()
        val imagesIDs = gameRepository.getDrawableContent().shapeImages

        _state.value = BoardState(
            currentTurnImageID = currentTurn.currentTurnImageID,
            currentScore = currentScore,
            boardSize = boardSize,
            gameMode = gameMode,
            shapeOfAI = shapeOfAI,
            imagesIDs = imagesIDs,
        )
    }

    fun simulateMoveOfAI() {

        HandleAIMoveUseCase(gameRepository = gameRepository).execute()

    }

}