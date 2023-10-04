package com.example.tictactoegamecompose.domain.usecase.composite

import com.example.tictactoegamecompose.domain.models.FigureOutShapesInGameModel
import com.example.tictactoegamecompose.domain.models.GameInitDataModel
import com.example.tictactoegamecompose.domain.repository.GameRepository
import com.example.tictactoegamecompose.domain.usecase.getter.GetBoardSizeUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetNumberOfPlayersUseCase
import com.example.tictactoegamecompose.domain.usecase.transformer.FigureOutShapesInGameUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.CreateGameUseCase
import javax.inject.Inject

class HandleCreateGameUseCase @Inject constructor(private val gameRepository: GameRepository) {

    fun execute() {
        val numberOfPlayers = GetNumberOfPlayersUseCase(gameRepository = gameRepository).execute()
        val shapes = gameRepository.getDrawableContent()

        val shapesInGame = FigureOutShapesInGameUseCase().execute(
            model = FigureOutShapesInGameModel(
                numberOfPlayers = numberOfPlayers,
                shapes = shapes
            )
        )

        val boardSize = GetBoardSizeUseCase(gameRepository = gameRepository).execute()

        CreateGameUseCase(gameRepository = gameRepository).execute(
            gameInitData = GameInitDataModel(
                boardSize = boardSize,
                players = shapesInGame.shapesInGame,
                numberOfPlayers = numberOfPlayers.numberOfPlayers,
            )
        )
    }

}