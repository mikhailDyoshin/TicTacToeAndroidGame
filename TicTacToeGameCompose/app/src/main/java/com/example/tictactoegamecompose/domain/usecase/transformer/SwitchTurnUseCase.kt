package com.example.tictactoegamecompose.domain.usecase.transformer

import com.example.tictactoegamecompose.domain.models.SwitchTurnModel
import com.example.tictactoegamecompose.domain.models.CurrentTurnModel

class SwitchTurnUseCase {

    fun execute(model: SwitchTurnModel): CurrentTurnModel {
        var turnsCounter = model.currentTurn.currentTurnValue
        val numberOfPlayers = model.numberOfPlayers.numberOfPlayers

        turnsCounter = (turnsCounter + 1) % numberOfPlayers

        val nextShape = model.shapes.shapeStrings[turnsCounter]
        val nextImage = model.shapes.shapeImages[nextShape]!!

        return CurrentTurnModel(
            currentTurnValue = turnsCounter,
            currentTurnShape = nextShape,
            currentTurnImageID = nextImage,
        )
    }

}