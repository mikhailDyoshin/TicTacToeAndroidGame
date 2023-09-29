package com.example.tictactoegamecompose.domain.usecase.transformer

import com.example.tictactoegamecompose.domain.models.FigureOutShapesInGameModel
import com.example.tictactoegamecompose.domain.models.ShapesInGameModel

class FigureOutShapesInGameUseCase {

    fun execute(model: FigureOutShapesInGameModel): ShapesInGameModel {

        val numberOfPlayers = model.numberOfPlayers.numberOfPlayers
        val shapesString = model.shapes.shapeStrings

        val shapesInGame =  shapesString.subList(0, numberOfPlayers.number)

        return ShapesInGameModel(shapesInGame = shapesInGame)
    }

}