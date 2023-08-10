package com.example.tictactoegamecompose.presentation.settingsWindow

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.tictactoegamecompose.data.repository.GameRepositoryImpl
import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
import com.example.tictactoegamecompose.domain.usecase.composite.HandleCreateGameUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetSettingsUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateAIFigureUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateBoardSizeUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateGameModeUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateNumberOfPlayersUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdatePlayerFigureUseCAse

class SettingsWindowViewModel {

    private val _state = mutableStateOf(SettingsWindowState())

    val state: State<SettingsWindowState> = _state

    private val gameStorage = OfflineGameStorage.getInstance()
    private val gameRepository = GameRepositoryImpl(gameStorage)

    init {
        getSettings()
    }

    private fun getSettings() {
        val currentSettings = GetSettingsUseCase(gameRepository = gameRepository).execute()

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

        val numberOfPlayersUpdated =
            GetSettingsUseCase(gameRepository = gameRepository).execute().numberOfPlayers


        _state.value = SettingsWindowState(
            gameMode = currentSettings.gameMode,
            boardSize = currentSettings.boardSize,
            numberOfPlayers = numberOfPlayersUpdated,
            playerFigure = currentSettings.playerFigure,
            disabledNumberOfPlayersOptions = disabledButtons,
        )

    }

    private fun resetNumberOfPlayers() {
        UpdateNumberOfPlayersUseCase(gameRepository = gameRepository)
            .execute(numberOfPlayers = 2)
    }

    fun setGameMode(gameMode: String) {
        UpdateGameModeUseCase(gameRepository = gameRepository).execute(gameMode = gameMode)
        getSettings()
    }

    fun setBoardSize(boardSize: String) {
        val boardSizeInt = _state.value.boardSizeOptionsMapStringToInt[boardSize]!!
        UpdateBoardSizeUseCase(gameRepository = gameRepository).execute(boardSize = boardSizeInt)
        getSettings()
    }

    fun setNumberOfPlayers(numberOfPlayers: String) {
        val numberOfPlayersInt = numberOfPlayers.toInt()
        UpdateNumberOfPlayersUseCase(gameRepository = gameRepository)
            .execute(numberOfPlayers = numberOfPlayersInt)
        getSettings()
    }

    fun setPlayerFigure(playerFigure: String) {


        val shapeOfAI = when (playerFigure) {
            "x" -> "o"
            "o" -> "x"
            else -> "o"
        }

        UpdatePlayerFigureUseCAse(gameRepository = gameRepository)
            .execute(playerFigure = playerFigure)
        UpdateAIFigureUseCase(gameRepository = gameRepository).execute(shapeOfAI = shapeOfAI)
        getSettings()
    }

    fun restartGame() {
        HandleCreateGameUseCase(gameRepository = gameRepository).execute()
    }

}