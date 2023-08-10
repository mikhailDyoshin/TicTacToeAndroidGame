package com.example.tictactoegamecompose.domain.usecase.transformer

import com.example.tictactoegamecompose.domain.models.BoardStateModel

class CheckForComboUseCase {

    /**
     * The function is called after each turn
     * and it checks for any combo in the board.
     * If any combo is found
     * the function will change the score
     * depending on the current turn.
     */
    fun execute(
        boardState: BoardStateModel
    ): Boolean {

        val board = boardState.board
        val shape = boardState.currentTurn
        val crossedCells = boardState.crossedCells
        val comboNumber = boardState.comboNumber

        val allCombos: List<MutableList<List<Int>>> = listOf(
            checkRows(board, shape, crossedCells, comboNumber),
            checkCols(board, shape, crossedCells, comboNumber),
            checkNegDiag(board, shape, crossedCells, comboNumber),
            checkPosDiag(board, shape, crossedCells, comboNumber),
        )

        for (combo: MutableList<List<Int>> in allCombos) {
            if (combo.count() == comboNumber) {
                addCrossedCells(crossedCells, combo)
                return true
            }
        }
        return false
    }

    /**
     * The function checks each row of the board
     * and returns a list of cells' coordinates
     * that belong to a combo
     * if any combo is found.
     */
    private fun checkRows(
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>,
        comboNumber: Int
    ): MutableList<List<Int>> {

        var combo: MutableList<List<Int>>

        val boardSize = board.count()

        for (i in 0 until boardSize) {

            combo = mutableListOf()
            for (j in 0 until boardSize) {
                if (foundCombo(i, j, board, shape, crossedCells, combo, comboNumber)) {
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
        crossedCells: MutableList<List<Int>>,
        comboNumber: Int,
    ): MutableList<List<Int>> {
        var combo: MutableList<List<Int>>

        val boardSize = board.count()

        for (j in 0 until boardSize) {
            combo = mutableListOf()
            for (i in 0 until boardSize) {
                if (foundCombo(i, j, board, shape, crossedCells, combo, comboNumber)) {
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
        crossedCells: MutableList<List<Int>>,
        comboNumber: Int,
    ): MutableList<List<Int>> {

        var combo: MutableList<List<Int>> = mutableListOf()

        val boardSize = board.count()

        // Check all diagonals below the main diagonal of the board including the main diagonal
        for (k in 0..boardSize - comboNumber) {
            combo = mutableListOf()
            for ((j, i) in (k until boardSize).withIndex()) {
                if (foundCombo(i, j, board, shape, crossedCells, combo, comboNumber)) {
                    return combo
                }
            }

        }

        // Check all diagonals above the main diagonal of the board
        for (k in 1..boardSize - comboNumber) {
            combo = mutableListOf()
            for ((i, j) in (k until boardSize).withIndex()) {
                if (foundCombo(i, j, board, shape, crossedCells, combo, comboNumber)) {
                    return combo
                }
            }
        }

        return combo

    }

    /**
     * The function checks each positive diagonal ([0,2], [1,1], [2,0] for example) of the board
     * and returns a list of cells' coordinates
     * that belong to a combo
     * if any combo is found.
     */
    private fun checkPosDiag(
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>,
        comboNumber: Int,
    ): MutableList<List<Int>> {

        var combo: MutableList<List<Int>> = mutableListOf()

        val boardSize = board.count()

        // Above the main positive diagonal, including the one
        for (k in boardSize - 1 downTo boardSize - comboNumber - 2) {

            combo = mutableListOf()
            for ((i, j) in (k downTo 0).withIndex()) {
                if (foundCombo(i, j, board, shape, crossedCells, combo, comboNumber)) {
                    return combo
                }
            }
        }

        // Below the main positive diagonal
        var offset = 1
        for (k in 1..boardSize - comboNumber) {
            combo = mutableListOf()
            for ((i, j) in (boardSize - 1 downTo k).withIndex()) {
                if (foundCombo(i + offset, j, board, shape, crossedCells, combo, comboNumber)) {
                    return combo
                }
            }
            offset++
        }

        return combo
    }

    /**
     * The function indicates whether a combo was found or not.
     */
    private fun foundCombo(
        i: Int,
        j: Int,
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>,
        combo: MutableList<List<Int>>,
        comboNumber: Int
    ): Boolean {

        formComboCells(i, j, board, shape, crossedCells, combo)

        return combo.count() == comboNumber
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
     * The function adds coordinates of new crossed cells
     * to the list where all crossed cells are stored.
     */
    private fun addCrossedCells(
        crossedCellsList: MutableList<List<Int>>,
        newCells: MutableList<List<Int>>
    ) {
        for (cell: List<Int> in newCells) {
            crossedCellsList.add(cell)
        }
    }
}