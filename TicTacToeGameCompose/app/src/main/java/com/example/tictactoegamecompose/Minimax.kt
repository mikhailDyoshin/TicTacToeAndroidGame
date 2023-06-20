package com.example.tictactoegamecompose

class Minimax constructor(board: List<MutableList<String>>, huPlayerShape: String, aiPlayerShape: String, crossedCells: MutableList<List<Int>>){

    private val huPlayer = huPlayerShape

    private val aiPlayer = aiPlayerShape

    private val crossedCellsList = crossedCells

    private var depth = 0

    private val maxDepth = 2

    val bestMove = minimax(board, aiPlayer)

    private fun emptyCellsCoordinates(board: List<MutableList<String>>): MutableList<List<Int>> {

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

    private fun minimax(board: List<MutableList<String>>, player: String): MutableMap<String, List<Int>> {

        val availableCells = emptyCellsCoordinates(board)

        val moves: MutableList<MutableMap<String, List<Int>>> = mutableListOf()


        if (checkForCombo(board, huPlayer, crossedCellsList)) {
            depth--
            return mutableMapOf("score" to listOf(-10))
        } else if (checkForCombo(board, aiPlayer, crossedCellsList)) {
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

    private fun checkRows(
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>
    ): MutableList<List<Int>> {

        val boardSize = board.count()

        var combo: MutableList<List<Int>>

        for (i in 0 until boardSize) {

            combo = mutableListOf()
            for (j in 0 until boardSize) {
                if (comboWasFound(i, j, board, shape, crossedCells, combo)) {
                    return combo
                }
            }
        }

        return mutableListOf()
    }

    /**
     * The function checks each column of the board
     * and returns a list of cells' coordinates that belong to a combo
     * if any combo is found.
     */
    private fun checkCols(
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>
    ): MutableList<List<Int>> {
        var combo: MutableList<List<Int>>

        val boardSize = board.count()

        for (j in 0 until boardSize) {
            combo = mutableListOf()
            for (i in 0 until boardSize) {
                if (comboWasFound(i, j, board, shape, crossedCells, combo)) {
                    return combo
                }
            }
        }

        return mutableListOf()
    }

    /**
     * The function checks each negative diagonal ([0,0], [1,1], [2,2] for example) of the board
     * and returns a list of cells' coordinates
     * that belong to a combo
     * if any combo is found.
     */
    private fun checkNegDiag(
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>
    ): MutableList<List<Int>> {

        var combo: MutableList<List<Int>> = mutableListOf()

        val boardSize = board.count()

        val comboNumber = 3

        // Check all diagonals below the main diagonal of the board including the main diagonal
        for (k in 0..boardSize - comboNumber) {
            combo = mutableListOf()
            for ((j, i) in (k until boardSize).withIndex()) {
                if (comboWasFound(i, j, board, shape, crossedCells, combo)) {
                    return combo
                }
            }

        }

        // Check all diagonals above the main diagonal of the board
        for (k in 1..boardSize - comboNumber) {
            combo = mutableListOf()
            for ((i, j) in (k until boardSize).withIndex()) {
                if (comboWasFound(i, j, board, shape, crossedCells, combo)) {
                    return combo
                }
            }
        }

        return combo

    }

    /**
     * The function checks each negative diagonal ([0,2], [1,1], [2,0] for example) of the board
     * and returns a list of cells' coordinates
     * that belong to a combo
     * if any combo is found.
     */
    private fun checkPosDiag(
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>
    ): MutableList<List<Int>> {

        var combo: MutableList<List<Int>> = mutableListOf()

        val boardSize = board.count()

        val comboNumber = 3

        // Above the main positive diagonal, including the one
        for (k in boardSize - 1 downTo boardSize - comboNumber) {

            combo = mutableListOf()
            for ((i, j) in (k downTo 0).withIndex()) {
                if (comboWasFound(i, j, board, shape, crossedCells, combo)) {
                    return combo
                }
            }
        }

        // Below the main positive diagonal
        var offset = 1
        for (k in 1..boardSize - comboNumber) {
            combo = mutableListOf()
            for ((i, j) in (boardSize - 1 downTo k).withIndex()) {
                if (comboWasFound(i + offset, j, board, shape, crossedCells, combo)) {
                    return combo
                }
            }
            offset++
        }

        return combo
    }

    /**
     * The function is called after each turn,
     * and it checks for any combo in the board.
     * If any combo is found
     * the function will change the score
     * depending on the current turn.
     */
    private fun checkForCombo(
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>
    ): Boolean {

        val comboNumber = 3

        val allCombos: List<MutableList<List<Int>>> = listOf(
            checkRows(board, shape, crossedCells),
            checkCols(board, shape, crossedCells),
            checkNegDiag(board, shape, crossedCells),
            checkPosDiag(board, shape, crossedCells),
        )

        for (combo: MutableList<List<Int>> in allCombos) {
            if (combo.count() == comboNumber) {
                return true
            }
        }

        return false
    }

    /**
     * The function forms a list of cells' coordinates that belong to a combo.
     */
    private fun formComboCells(
        i: Int,
        j: Int,
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>,
        combo: MutableList<List<Int>>
    ) {
        if (board[i][j] == shape && mutableListOf(i, j) !in crossedCells) {
            combo.add(mutableListOf(i, j))
        } else {
            combo.clear()
        }
    }

    /**
     * The function indicates whether a combo was found or not.
     */
    private fun comboWasFound(
        i: Int,
        j: Int,
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>,
        combo: MutableList<List<Int>>
    ): Boolean {

        val comboNumber = 3

        formComboCells(i, j, board, shape, crossedCells, combo)

        return combo.count() == comboNumber
    }
}