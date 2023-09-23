package com.example.tictactoegamecompose.di

import com.example.tictactoegamecompose.data.repository.GameRepositoryImpl
import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
import com.example.tictactoegamecompose.domain.repository.GameRepository
import com.example.tictactoegamecompose.domain.usecase.composite.HandleAIMoveUseCase
import com.example.tictactoegamecompose.domain.usecase.composite.HandleCreateGameUseCase
import com.example.tictactoegamecompose.domain.usecase.composite.HandlePlayerMoveUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetBoardSizeUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetBoardStateUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetContentBoardUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetCurrentTurnUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameModeUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameOverStatisticsUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameStatusUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetSettingsUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetShapeOfAIUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateAIFigureUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateBoardSizeUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateGameModeUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdateNumberOfPlayersUseCase
import com.example.tictactoegamecompose.domain.usecase.updater.UpdatePlayerFigureUseCAse
import com.example.tictactoegamecompose.presentation.board.BoardViewModel
import com.example.tictactoegamecompose.presentation.cell.CellViewModel
import com.example.tictactoegamecompose.presentation.gameOverWindow.GameOverWindowViewModel
import com.example.tictactoegamecompose.presentation.gameWindow.GameWindowViewModel
import com.example.tictactoegamecompose.presentation.myApp.MyAppViewModel
import com.example.tictactoegamecompose.presentation.settingsWindow.SettingsWindowViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOfflineGameStorage(): OfflineGameStorage {
        return OfflineGameStorage()
    }


    @Provides
    @Singleton
    fun provideGameRepository(storage: OfflineGameStorage): GameRepository {
        return GameRepositoryImpl(storage)
    }

    @Provides
    @Singleton
    fun provideBoardViewModel(
        getBoardStateUseCase: GetBoardStateUseCase,
        handleAIMoveUseCase: HandleAIMoveUseCase
    ): BoardViewModel {
        return BoardViewModel(getBoardStateUseCase, handleAIMoveUseCase)
    }

    @Provides
    @Singleton
    fun provideGameOverWindowViewModel(
        getGameOverStatisticsUseCase: GetGameOverStatisticsUseCase
    ): GameOverWindowViewModel {
        return GameOverWindowViewModel(getGameOverStatisticsUseCase)
    }

    @Provides
    @Singleton
    fun provideGameWindowViewModel(
        handleCreateGameUseCase: HandleCreateGameUseCase
    ): GameWindowViewModel {
        return GameWindowViewModel(handleCreateGameUseCase)
    }

    @Provides
    @Singleton
    fun provideMyAppViewModel(
        getGameStatusUseCase: GetGameStatusUseCase,
        handleCreateGameUseCase: HandleCreateGameUseCase,
    ): MyAppViewModel {
        return MyAppViewModel(getGameStatusUseCase, handleCreateGameUseCase)
    }

    @Provides
    @Singleton
    fun provideSettingsWindowViewModel(
        getSettingsUseCase: GetSettingsUseCase,
        updateNumberOfPlayersUseCase: UpdateNumberOfPlayersUseCase,
        updateGameModeUseCase: UpdateGameModeUseCase,
        updateBoardSizeUseCase: UpdateBoardSizeUseCase,
        updatePlayerFigureUseCAse: UpdatePlayerFigureUseCAse,
        updateAIFigureUseCase: UpdateAIFigureUseCase,
        handleCreateGameUseCase: HandleCreateGameUseCase,
    ): SettingsWindowViewModel {
        return SettingsWindowViewModel(
            getSettingsUseCase,
            updateNumberOfPlayersUseCase,
            updateGameModeUseCase,
            updateBoardSizeUseCase,
            updatePlayerFigureUseCAse,
            updateAIFigureUseCase,
            handleCreateGameUseCase,
        )
    }

    @Provides
    @Singleton
    fun provideCellViewModel(
        getGameModeUseCase: GetGameModeUseCase,
        getCurrentTurnUseCase: GetCurrentTurnUseCase,
        getShapeOfAIUseCase: GetShapeOfAIUseCase,
        handlePlayerMoveUseCase: HandlePlayerMoveUseCase,
        getContentBoardUseCase: GetContentBoardUseCase,
        getBoardSizeUseCase: GetBoardSizeUseCase
    ): CellViewModel {
        return CellViewModel(
            getGameModeUseCase,
            getCurrentTurnUseCase,
            getShapeOfAIUseCase,
            handlePlayerMoveUseCase,
            getContentBoardUseCase,
            getBoardSizeUseCase
        )
    }


}