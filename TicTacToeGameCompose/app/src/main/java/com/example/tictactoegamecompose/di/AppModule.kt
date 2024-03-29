package com.example.tictactoegamecompose.di

import com.example.tictactoegamecompose.data.repository.GameRepositoryImpl
import com.example.tictactoegamecompose.data.storage.OfflineGameStorage
import com.example.tictactoegamecompose.domain.repository.GameRepository
import com.example.tictactoegamecompose.domain.usecase.composite.HandleAIMoveUseCase
import com.example.tictactoegamecompose.domain.usecase.composite.HandleCreateGameUseCase
import com.example.tictactoegamecompose.domain.usecase.composite.HandlePlayerMoveUseCase
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
import com.example.tictactoegamecompose.presentation.gameOverWindow.GameOverWindowViewModel
import com.example.tictactoegamecompose.presentation.gameWindow.GameWindowViewModel
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
    fun provideGameOverWindowViewModel(
        getGameOverStatisticsUseCase: GetGameOverStatisticsUseCase,
    ): GameOverWindowViewModel {
        return GameOverWindowViewModel(
            getGameOverStatisticsUseCase,
        )
    }

    @Provides
    @Singleton
    fun provideGameWindowViewModel(
        handleCreateGameUseCase: HandleCreateGameUseCase,
        getBoardStateUseCase: GetBoardStateUseCase,
        handleAIMoveUseCase: HandleAIMoveUseCase,
        getGameModeUseCase: GetGameModeUseCase,
        getCurrentTurnUseCase: GetCurrentTurnUseCase,
        getShapeOfAIUseCase: GetShapeOfAIUseCase,
        handlePlayerMoveUseCase: HandlePlayerMoveUseCase,
        getContentBoardUseCase: GetContentBoardUseCase,
        getGameStatusUseCase: GetGameStatusUseCase,
    ): GameWindowViewModel {
        return GameWindowViewModel(
            handleCreateGameUseCase,
            getBoardStateUseCase,
            handleAIMoveUseCase,
            getGameModeUseCase,
            getCurrentTurnUseCase,
            getShapeOfAIUseCase,
            handlePlayerMoveUseCase,
            getContentBoardUseCase,
            getGameStatusUseCase
        )
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

}

