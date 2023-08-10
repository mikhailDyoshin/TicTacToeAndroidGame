package com.example.tictactoegamecompose.presentation.cell

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.data.repository.GameRepositoryImpl
import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
import com.example.tictactoegamecompose.domain.usecase.composite.HandlePlayerMoveUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetContentBoardUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetCurrentTurnUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameModeUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetShapeOfAIUseCase

class CellViewModel(private val buttonCoordinates: List<Int>) : ViewModel() {

    private val _state = mutableStateOf(CellState())

    val state: State<CellState> = _state

    private val gameStorage = OfflineGameStorage.getInstance()
    private val gameRepository = GameRepositoryImpl(gameStorage)

    init {
        getCellState()
    }


    fun changeGameState() {

        val gameMode = GetGameModeUseCase(gameRepository = gameRepository).execute()
        val currentTurnShape =
            GetCurrentTurnUseCase(gameRepository = gameRepository).execute().currentTurnShape
        val shapeOfAI = GetShapeOfAIUseCase(gameRepository = gameRepository).execute()

        if (gameMode == "VS computer") {
            if (currentTurnShape != shapeOfAI) {
                HandlePlayerMoveUseCase(gameRepository).execute(buttonCoordinates)
            }
        } else {
            HandlePlayerMoveUseCase(gameRepository).execute(buttonCoordinates)
        }

    }

    fun getCellState() {
        val row = buttonCoordinates[0]
        val col = buttonCoordinates[1]
        val cell = GetContentBoardUseCase(gameRepository = gameRepository).execute().board[row][col]
        _state.value = CellState(imageId = cell.image, crossed = cell.crossed)
    }


}