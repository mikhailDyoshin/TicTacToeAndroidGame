package com.example.tictactoegamecompose.presentation.board

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.domain.usecase.composite.HandleAIMoveUseCase
import com.example.tictactoegamecompose.domain.usecase.composite.HandlePlayerMoveUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetBoardStateUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetContentBoardUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetCurrentTurnUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameModeUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetShapeOfAIUseCase
import com.example.tictactoegamecompose.presentation.cell.CellState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val getBoardStateUseCase: GetBoardStateUseCase,
    private val handleAIMoveUseCase: HandleAIMoveUseCase,
    private val getGameModeUseCase: GetGameModeUseCase,
    private val getCurrentTurnUseCase: GetCurrentTurnUseCase,
    private val getShapeOfAIUseCase: GetShapeOfAIUseCase,
    private val handlePlayerMoveUseCase: HandlePlayerMoveUseCase,
    private val getContentBoardUseCase: GetContentBoardUseCase,
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
            contentBoard = getContentBoard(),
        )
    }

    fun changeGameState(row: Int, col: Int) {

        val gameMode = getGameModeUseCase.execute()
        val currentTurnShape = getCurrentTurnUseCase.execute().currentTurnShape
        val shapeOfAI = getShapeOfAIUseCase.execute()

        if (gameMode == "VS computer") {
            if (currentTurnShape != shapeOfAI) {
                handlePlayerMoveUseCase.execute(listOf(row, col))
            }
        } else {
            handlePlayerMoveUseCase.execute(listOf(row, col))
        }

    }

    private fun getContentBoard(): List<List<CellState>> {
        val board = getContentBoardUseCase.execute().board

        return board.map { row ->
            row.map { cell ->
                CellState(
                    imageID = cell.image,
                    crossed = cell.crossed
                )
            }
        }
    }

    fun simulateMoveOfAI() {

        handleAIMoveUseCase.execute()

    }

}