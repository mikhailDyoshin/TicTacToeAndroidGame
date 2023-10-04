package com.example.tictactoegamecompose.domain.usecase.transformer

import com.example.tictactoegamecompose.domain.models.SwitchTurnModel
import com.example.tictactoegamecompose.domain.models.CurrentTurnModel

class SwitchTurnUseCase {

    fun execute(model: SwitchTurnModel): CurrentTurnModel {
        var turnsCounter = model.currentTurn.currentTurnValue
        val numberOfPlayers = model.numberOfPlayers.numberOfPlayers

        turnsCounter = (turnsCounter + 1) % numberOfPlayers.number

        val nextShape = model.shapes.shapeStrings[turnsCounter]

        return CurrentTurnModel(
            currentTurnValue = turnsCounter,
            currentTurnShape = nextShape,
        )
    }

}