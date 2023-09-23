package com.example.tictactoegamecompose.presentation.board

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.domain.usecase.composite.HandleAIMoveUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetBoardStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val getBoardStateUseCase: GetBoardStateUseCase,
    private val handleAIMoveUseCase: HandleAIMoveUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(BoardState())

    val state: State<BoardState> = _state

    init {
        getState()
    }


    fun getState() {

        val boardState = getBoardStateUseCase.execute()

        _state.value = BoardState(
            currentTurnImageID = boardState.currentTurn.currentTurnImageID,
            currentScore = boardState.currentScore,
            boardSize = boardState.boardSize,
            gameMode = boardState.gameMode,
            shapeOfAI = boardState.shapeOfAI,
            imagesIDs = boardState.imagesIDs,
        )
    }

    fun simulateMoveOfAI() {

        handleAIMoveUseCase.execute()

    }

}