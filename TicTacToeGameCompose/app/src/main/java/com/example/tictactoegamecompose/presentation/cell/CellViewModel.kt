package com.example.tictactoegamecompose.presentation.cell

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.domain.usecase.composite.HandlePlayerMoveUseCase
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
) : ViewModel() {

    private val _state = mutableStateOf(CellState())

    val state: State<CellState> = _state


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

        getCellState(row = row, col = col)

    }

    fun getCellState(row: Int, col: Int) {
        val cell = getContentBoardUseCase.execute().board[row][col]
        _state.value = CellState(imageID = cell.image, crossed = cell.crossed)
    }

}
