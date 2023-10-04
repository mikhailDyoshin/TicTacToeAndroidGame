package com.example.tictactoegamecompose.presentation.settingsWindow

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.common.BoardSize
import com.example.tictactoegamecompose.common.Figure
import com.example.tictactoegamecompose.common.GameMode
import com.example.tictactoegamecompose.common.NumberOfPlayers
import com.example.tictactoegamecompose.domain.usecase.composite.HandleCreateGameUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetSettingsUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateAIFigureUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateBoardSizeUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateGameModeUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateNumberOfPlayersUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdatePlayerFigureUseCAse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsWindowViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val updateNumberOfPlayersUseCase: UpdateNumberOfPlayersUseCase,
    private val updateGameModeUseCase: UpdateGameModeUseCase,
    private val updateBoardSizeUseCase: UpdateBoardSizeUseCase,
    private val updatePlayerFigureUseCAse: UpdatePlayerFigureUseCAse,
    private val updateAIFigureUseCase: UpdateAIFigureUseCase,
    private val handleCreateGameUseCase: HandleCreateGameUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SettingsWindowState())

    val state: State<SettingsWindowState> = _state

    init {
        getSettings()
    }

    private fun getSettings() {
        val currentSettings = getSettingsUseCase.execute()

        val disabledButtons: MutableList<String>

        val numberOfPlayers = currentSettings.numberOfPlayers

        when {
            currentSettings.gameMode == GameMode.VS_COMPUTER -> {
                disabledButtons = mutableListOf("2", "3", "4")
                resetNumberOfPlayers()
            }

            currentSettings.boardSize == BoardSize.SMALL -> {
                disabledButtons = mutableListOf("3", "4")
                resetNumberOfPlayers()
            }

            currentSettings.boardSize == BoardSize.MIDDLE -> {
                disabledButtons = mutableListOf("4")
                if (numberOfPlayers == NumberOfPlayers.FOUR) {
                    resetNumberOfPlayers()
                } else {
                    // Do nothing
                }
            }

            else -> disabledButtons = mutableListOf()
        }

        val numberOfPlayersUpdated = getSettingsUseCase.execute().numberOfPlayers


        _state.value = SettingsWindowState(
            gameMode = currentSettings.gameMode,
            boardSize = currentSettings.boardSize,
            numberOfPlayers = numberOfPlayersUpdated,
            playerFigure = currentSettings.playerFigure,
            disabledNumberOfPlayersOptions = disabledButtons,
        )

    }

    private fun resetNumberOfPlayers() {
        updateNumberOfPlayersUseCase.execute(numberOfPlayers = NumberOfPlayers.TWO)
    }

    fun setGameMode(gameMode: GameMode) {
        updateGameModeUseCase.execute(gameMode = gameMode)
        getSettings()
    }

    fun setBoardSize(boardSizeStr: String) {
        val boardSize = _state.value.boardSizeOptionsMapStringToInt[boardSizeStr]!!
        updateBoardSizeUseCase.execute(boardSize = boardSize)
        getSettings()
    }

    fun setNumberOfPlayers(numberOfPlayers: String) {
        val numberOfPlayersInt = NumberOfPlayers.values().filter { it.number == numberOfPlayers.toInt() }
        updateNumberOfPlayersUseCase.execute(numberOfPlayers = numberOfPlayersInt[0])
        getSettings()
    }

    fun setPlayerFigure(playerFigureStr: String) {


        val shapeOfAI = when (playerFigureStr) {
            "x" -> Figure.CIRCLE
            "o" -> Figure.CROSS
            else -> Figure.CIRCLE
        }

        val playerFigure = when (playerFigureStr) {
            "x" -> Figure.CROSS
            "o" -> Figure.CIRCLE
            else -> Figure.CROSS
        }

        updatePlayerFigureUseCAse.execute(playerFigure = playerFigure)
        updateAIFigureUseCase.execute(shapeOfAI = shapeOfAI)
        getSettings()
    }

    fun restartGame() {
        handleCreateGameUseCase.execute()
    }

}