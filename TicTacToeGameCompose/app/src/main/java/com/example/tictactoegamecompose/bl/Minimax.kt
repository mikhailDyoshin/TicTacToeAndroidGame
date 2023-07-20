package com.example.tictactoegamecompose.bl

class Minimax constructor(
    board: List<MutableList<String>>,
    huPlayerShape: String,
    aiPlayerShape: String,
    crossedCells: MutableList<List<Int>>,
    comboNumber: Int,
) {

    private val huPlayer = huPlayerShape

    private val aiPlayer = aiPlayerShape

    private val crossedCellsList = crossedCells.toMutableList()

    private val comboNumberValue = comboNumber

    private var depth = 0

    private val maxDepth = 2

    private val comboTracker = ComboTracker()

    val bestMove = minimax(board, aiPlayer)

    private fun emptyCellsCoordinates(
        board: List<MutableList<String>>
    ): MutableList<List<Int>> {

        val emptyCellsCoordinatesList = mutableListOf<List<Int>>()

        for ((rowIndex, row) in board.withIndex()) {
            for ((colIndex, col) in row.withIndex()) {
                if (col == " ") {
                    emptyCellsCoordinatesList.add(listOf(rowIndex, colIndex))
                }
            }
        }

        return emptyCellsCoordinatesList
    }

    private fun minimax(
        board: List<MutableList<String>>,
        player: String
    ): MutableMap<String, List<Int>> {

        val availableCells = emptyCellsCoordinates(board)

        val moves: MutableList<MutableMap<String, List<Int>>> = mutableListOf()


        if (comboTracker.checkForCombo(board, huPlayer, crossedCellsList, comboNumberValue)) {
            depth--
            return mutableMapOf("score" to listOf(-10))
        } else if (comboTracker.checkForCombo(
                board,
                aiPlayer,
                crossedCellsList,
                comboNumberValue
            )
        ) {
            depth--
            return mutableMapOf("score" to listOf(10))
        } else if (availableCells.isEmpty()) {
            depth--
            return mutableMapOf("score" to listOf(0))
        }

        if (depth > maxDepth) {
            depth--
            return mutableMapOf("score" to listOf(0))
        }


        // loop through available spots
        for ((i, _) in availableCells.withIndex()) {
            //create an object for each and store the index of that spot that was stored as a number in the object's index key
            val move: MutableMap<String, List<Int>> = mutableMapOf()

            depth++

            move["index"] = availableCells[i]

            // set the empty spot to the current player
            board[availableCells[i][0]][availableCells[i][1]] = player

            //if collect the score resulted from calling minimax on the opponent of the current player
            if (player == aiPlayer) {
                val result = minimax(board, huPlayer)
                move["score"] = result["score"]!!
            } else {
                val result = minimax(board, aiPlayer)
                move["score"] = result["score"]!!
            }

            //reset the spot to empty
            board[availableCells[i][0]][availableCells[i][1]] = " "

            // push the object to the array
            moves.add(move)
        }

        var bestMove = 0
        if (player == aiPlayer) {
            var bestScore = -10000
            for ((i, _) in moves.withIndex()) {
                if (moves[i]["score"]!![0] > bestScore) {
                    bestScore = moves[i]["score"]!![0]
                    bestMove = i
                }
            }
        } else {

// else loop over the moves and choose the move with the lowest score
            var bestScore = 10000
            for ((i, _) in moves.withIndex()) {
                if (moves[i]["score"]!![0] < bestScore) {
                    bestScore = moves[i]["score"]!![0]
                    bestMove = i
                }
            }
        }

        depth--
        return moves[bestMove]
    }

}