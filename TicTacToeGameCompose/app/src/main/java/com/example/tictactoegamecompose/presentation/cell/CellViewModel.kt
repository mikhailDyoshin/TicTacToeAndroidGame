package com.example.tictactoegamecompose.presentation.cell

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.domain.usecase.composite.HandlePlayerMoveUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetBoardSizeUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetContentBoardUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetCurrentTurnUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameModeUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetShapeOfAIUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CellViewModel @Inject constructor(
    private val getGameModeUseCase: GetGameModeUseCase,
    private val getCurrentTurnUseCase: GetCurrentTurnUseCase,
    private val getShapeOfAIUseCase: GetShapeOfAIUseCase,
    private val handlePlayerMoveUseCase: HandlePlayerMoveUseCase,
    private val getContentBoardUseCase: GetContentBoardUseCase,
    getBoardSizeUseCase: GetBoardSizeUseCase,
) : ViewModel() {

    private var _state: MutableState<List<MutableList<CellState>>>

    init {
        val boardSize = getBoardSizeUseCase.execute()

        _state = mutableStateOf(List(boardSize) {
            MutableList(boardSize) {
                CellState(
                    crossed = false,
                    imageID = 0
                )
            }
        })

        getCellState()

    }

    val state: State<List<MutableList<CellState>>> = _state

    fun changeGameState(buttonCoordinates: List<Int>) {

        val gameMode = getGameModeUseCase.execute()
        val currentTurnShape = getCurrentTurnUseCase.execute().currentTurnShape
        val shapeOfAI = getShapeOfAIUseCase.execute()

        if (gameMode == "VS computer") {
            if (currentTurnShape != shapeOfAI) {
                handlePlayerMoveUseCase.execute(buttonCoordinates)
            }
        } else {
            handlePlayerMoveUseCase.execute(buttonCoordinates)
        }

        getCellState()

    }

    fun getCellState() {
        getContentBoardUseCase.execute().board.mapIndexed { rowIndex, row -> row.mapIndexed {
                colIndex, cell ->
            _state.value[rowIndex][colIndex].crossed = cell.crossed
            _state.value[rowIndex][colIndex].imageID = cell.image
        } }
    }


}