package com.example.tictactoegamecompose.presentation.myApp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoegamecompose.domain.usecase.composite.HandleCreateGameUseCase
import com.example.tictactoegamecompose.domain.usecase.getter.GetGameStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyAppViewModel @Inject constructor(
    private val getGameStatusUseCase: GetGameStatusUseCase,
    private val handleCreateGameUseCase: HandleCreateGameUseCase
): ViewModel() {

    private val _state = mutableStateOf(MyAppState())

    val state: State<MyAppState> = _state

    fun getGameStatus() {

        val gameStatus = getGameStatusUseCase.execute()
        if (gameStatus.gameOverStatus) {
            _state.value = MyAppState(myAppState = gameStatus)
        }

    }

    fun resetGameStatus() {

        handleCreateGameUseCase.execute()
        val gameStatus = getGameStatusUseCase.execute()
        _state.value = MyAppState(myAppState = gameStatus)

    }

}