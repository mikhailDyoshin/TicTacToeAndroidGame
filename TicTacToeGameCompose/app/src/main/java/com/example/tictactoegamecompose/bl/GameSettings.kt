package com.example.tictactoegamecompose.bl

class GameSettings {

    private var gameMode = "VS player"
    private var boardSize = 3
    private var numberOfPlayers = 2
    private var shapeOfPlayer = "x"
    private var shapeOfAI = "o"

    // Game configurations interface

    fun selectGameConfigurations(settings: Map<String, String>) {
        setGameMode(settings["mode"]!!)
        setBoardSize(settings["size"]!!)
        setNumberOfPlayers(settings["players"]!!)
        setShapesForPlayerAndAI(settings["playerFigure"]!!)
    }

    private fun setGameMode(mode: String) {
        gameMode = mode
    }

    private fun setBoardSize(size: String) {
        val boardSizeOptions: Map<String, Int> = mapOf("3x3" to 3, "5x5" to 5, "7x7" to 7)
        boardSize = boardSizeOptions[size]!!
    }

    private fun setNumberOfPlayers(number: String) {
        numberOfPlayers = number.toInt()
    }

    private fun setShapesForPlayerAndAI(playerShape: String) {
        shapeOfPlayer = playerShape
        shapeOfAI = when (playerShape) {
            "x" -> "o"
            "o" -> "x"
            else -> "o"
        }
    }

    fun getGameMode(): String {
        return gameMode
    }

    fun getBoardSize(): Int {
        return boardSize
    }

    fun getNumberOfPlayers(): Int {
        return numberOfPlayers
    }

    fun getShapeOfPlayer(): String {
        return shapeOfPlayer
    }

    fun getShapeOfAI(): String {
        return shapeOfAI
    }
}
