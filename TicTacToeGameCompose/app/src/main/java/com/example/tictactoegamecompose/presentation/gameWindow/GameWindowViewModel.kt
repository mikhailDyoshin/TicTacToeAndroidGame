package com.example.tictactoegamecompose.presentation.gameWindow


import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.domain.usecase.composite.HandleCreateGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameWindowViewModel @Inject constructor(private val handleCreateGameUseCase: HandleCreateGameUseCase) :
    ViewModel() {

    fun restartGame() {
        handleCreateGameUseCase.execute()
    }

}