package com.example.tictactoegamecompose.presentation.settingsWindow

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.data.repository.GameRepositoryImpl
import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
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
            currentSettings.gameMode == "VS computer" -> {
                disabledButtons = mutableListOf("2", "3", "4")
                resetNumberOfPlayers()
            }

            currentSettings.boardSize == 3 -> {
                disabledButtons = mutableListOf("3", "4")
                resetNumberOfPlayers()
            }

            currentSettings.boardSize == 5 -> {
                disabledButtons = mutableListOf("4")
                if (numberOfPlayers == 4) {
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
        updateNumberOfPlayersUseCase.execute(numberOfPlayers = 2)
    }

    fun setGameMode(gameMode: String) {
        updateGameModeUseCase.execute(gameMode = gameMode)
        getSettings()
    }

    fun setBoardSize(boardSize: String) {
        val boardSizeInt = _state.value.boardSizeOptionsMapStringToInt[boardSize]!!
        updateBoardSizeUseCase.execute(boardSize = boardSizeInt)
        getSettings()
    }

    fun setNumberOfPlayers(numberOfPlayers: String) {
        val numberOfPlayersInt = numberOfPlayers.toInt()
        updateNumberOfPlayersUseCase.execute(numberOfPlayers = numberOfPlayersInt)
        getSettings()
    }

    fun setPlayerFigure(playerFigure: String) {


        val shapeOfAI = when (playerFigure) {
            "x" -> "o"
            "o" -> "x"
            else -> "o"
        }

        updatePlayerFigureUseCAse.execute(playerFigure = playerFigure)
        updateAIFigureUseCase.execute(shapeOfAI = shapeOfAI)
        getSettings()
    }

    fun restartGame() {
        handleCreateGameUseCase.execute()
    }

}