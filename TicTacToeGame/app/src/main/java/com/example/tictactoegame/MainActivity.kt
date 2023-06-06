package com.example.tictactoegame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var currentTurn: String = "o"

    private val board: List<MutableList<String>> = listOf(
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " "),
    )

    private val score: MutableMap<String, Int> = mutableMapOf("x" to 0, "o" to 0)

    private val boardSize = 3

    private val comboNumber = 3

    private val crossedCells: MutableList<List<Int>> = mutableListOf()

    private val cellsMap: MutableMap<ImageButton, List<Int>> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeCurrentTurnText()

        changeScoreText()

        val cell1: ImageButton = findViewById(R.id.a1)
        val cell2: ImageButton = findViewById(R.id.a2)
        val cell3: ImageButton = findViewById(R.id.a3)
        val cell4: ImageButton = findViewById(R.id.b1)
        val cell5: ImageButton = findViewById(R.id.b2)
        val cell6: ImageButton = findViewById(R.id.b3)
        val cell7: ImageButton = findViewById(R.id.c1)
        val cell8: ImageButton = findViewById(R.id.c2)
        val cell9: ImageButton = findViewById(R.id.c3)

        val restartButton: Button = findViewById(R.id.restart_button)

        // Form the map of cells and their coordinates
        // according to which they are placed on the board.
        val cells: Map<ImageButton, List<Int>> = mapOf(
            cell1 to listOf(0, 0),
            cell2 to listOf(0, 1),
            cell3 to listOf(0, 2),
            cell4 to listOf(1, 0),
            cell5 to listOf(1, 1),
            cell6 to listOf(1, 2),
            cell7 to listOf(2, 0),
            cell8 to listOf(2, 1),
            cell9 to listOf(2, 2),
        )

        mapCells(cells)

        cell1.setOnClickListener { changeState(cell1, cells[cell1]!!) }
        cell2.setOnClickListener { changeState(cell2, cells[cell2]!!) }
        cell3.setOnClickListener { changeState(cell3, cells[cell3]!!) }
        cell4.setOnClickListener { changeState(cell4, cells[cell4]!!) }
        cell5.setOnClickListener { changeState(cell5, cells[cell5]!!) }
        cell6.setOnClickListener { changeState(cell6, cells[cell6]!!) }
        cell7.setOnClickListener { changeState(cell7, cells[cell7]!!) }
        cell8.setOnClickListener { changeState(cell8, cells[cell8]!!) }
        cell9.setOnClickListener { changeState(cell9, cells[cell9]!!) }

        restartButton.setOnClickListener { restartGame() }

    }


    /**
     * The function adds all (cell-button/its coordinates) pairs to the map.
     */
    private fun mapCells(cells: Map<ImageButton, List<Int>>) {
        for ((key, value) in cells.entries) {
            cellsMap[key] = value
        }
    }

    /**
     * The function changes a state of the game.
     */
    private fun changeState(cell: ImageButton, cellCoordinates: List<Int>) {

        val cellRow = cellCoordinates[0]
        val cellCol = cellCoordinates[1]
        val clickedCell = board[cellRow][cellCol]

        if (clickedCell == " ") {

            addShape(cellRow, cellCol, currentTurn)

            if (currentTurn == "o") {
                cell.setImageResource(R.drawable.circle)
                checkForCombo(board, currentTurn, crossedCells)
                changeCurrentTurn("x")
                changeCurrentTurnText()
            } else {
                cell.setImageResource(R.drawable.cross)
                checkForCombo(board, currentTurn, crossedCells)
                changeCurrentTurn("o")
                changeCurrentTurnText()
            }
            paintCrossedCells()
            changeScoreText()
            checkForWin()
        }
    }

    /**
     * The function switches players (turns actually).
     */
    private fun changeCurrentTurn(nextTurn: String) {
        currentTurn = nextTurn
    }

    /**
     * The function displays the current turn.
     */
    private fun changeCurrentTurnText() {
        val currentTurnTextView: TextView = findViewById(R.id.current_turn)
        val text = "$currentTurn turn"
        currentTurnTextView.text = text
    }

    /**
     * The function adds a figure ("x" or "o") to the board
     * depending on the current turn.
     */
    private fun addShape(cellRow: Int, cellCol: Int, turn: String) {
        board[cellRow][cellCol] = turn
    }


    private fun showToast(something: String) {
        Toast.makeText(this, something, Toast.LENGTH_SHORT).show()
    }


    /**
     * The function displays the current score on the screen.
     */
    private fun changeScoreText() {
        val currentScoreTextView: TextView = findViewById(R.id.current_score)
        val text = "Score:\nx: ${score["x"]} o: ${score["o"]}"
        currentScoreTextView.text = text
    }

    /**
     * The function switches the game to the initial state.
     */
    private fun restartGame() {
        clearBoard()
        resetCellsImages()
        clearRecords()
        currentTurn = "o"
        changeCurrentTurnText()
        changeScoreText()
    }

    private fun clearBoard() {
        for (row: MutableList<String> in board) {
            for ((index, col) in row.withIndex()) {
                row[index] = " "
            }
        }
    }

    private fun resetCellsImages() {
        for (cell in cellsMap.keys) {
            // delete images in ImageButtons
            cell.setImageResource(android.R.color.transparent)
            // delete background color (paint it white)
            cell.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    private fun clearRecords() {
        clearScore()
        clearCrossedCells()
    }

    private fun clearScore() {
        score["x"] = 0
        score["o"] = 0
    }

    private fun clearCrossedCells() {
        crossedCells.clear()
    }

    /**
     * The function indicates if there is a winner. It's called after every turn.
     */
    private fun checkForWin() {
        // x wins
        if (score["x"] == 1) {
            showToast("x wins!")
        }
        // o wins
        else if (score["o"] == 1) {
            showToast("o wins!")
        }
        // draw
        else if (checkFullBoard()) {
            showToast("Draw.")
        }
    }

    /**
     * The function check if all cells of the board were used
     */
    private fun checkFullBoard(): Boolean {

        for (row: MutableList<String> in board) {
            if (" " in row) {
                return false
            }
        }

        return true

    }

    /**
     * The function changes crossed cells' background to red.
     */
    private fun paintCrossedCells() {
        for (coordinates: List<Int> in crossedCells) {
            for (cell: ImageButton in cellsMap.keys) {
                if (cellsMap[cell] == coordinates) {
                    cell.setBackgroundColor(Color.parseColor("#FF0000"))
                }
            }
        }
    }


    // Check for combo functions
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

    /**
     * The function checks each row of the board
     * and returns a list of cells' coordinates
     * that belong to a combo
     * if any combo is found.
     */
    private fun checkRows(
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>
    ): MutableList<List<Int>> {

        var combo: MutableList<List<Int>>

        for (i in 0 until boardSize) {

            combo = mutableListOf()
            for (j in 0 until boardSize) {
                if (foundCombo(i, j, board, shape, crossedCells, combo)) {
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

        for (j in 0 until boardSize) {
            combo = mutableListOf()
            for (i in 0 until boardSize) {
                if (foundCombo(i, j, board, shape, crossedCells, combo)) {
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

        // Check all diagonals below the main diagonal of the board including the main diagonal
        for (k in 0..boardSize - comboNumber) {
            combo = mutableListOf()
            for ((j, i) in (k until boardSize).withIndex()) {
                if (foundCombo(i, j, board, shape, crossedCells, combo)) {
                    return combo
                }
            }

        }

        // Check all diagonals above the main diagonal of the board
        for (k in 1..boardSize - comboNumber) {
            combo = mutableListOf()
            for ((i, j) in (k until boardSize).withIndex()) {
                if (foundCombo(i, j, board, shape, crossedCells, combo)) {
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

        // Above the main positive diagonal, including the one
        for (k in boardSize - 1 downTo boardSize - comboNumber) {

            combo = mutableListOf()
            for ((i, j) in (k downTo 0).withIndex()) {
                if (foundCombo(i, j, board, shape, crossedCells, combo)) {
                    return combo
                }
            }
        }

        // Below the main positive diagonal
        var offset = 1
        for (k in 1..boardSize - comboNumber) {
            combo = mutableListOf()
            for ((i, j) in (boardSize - 1 downTo k).withIndex()) {
                if (foundCombo(i + offset, j, board, shape, crossedCells, combo)) {
                    return combo
                }
            }
            offset++
        }

        return combo
    }

    /**
     * The function is called after each turn
     * and it checks for any combo in the board.
     * If any combo is found
     * the function will change the score
     * depending on the current turn.
     */
    private fun checkForCombo(
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>
    ): MutableList<List<Int>> {

        val comboNumber = 3

        val emptyList: MutableList<List<Int>> = mutableListOf()

        val allCombos: List<MutableList<List<Int>>> = listOf(
            checkRows(board, shape, crossedCells),
            checkCols(board, shape, crossedCells),
            checkNegDiag(board, shape, crossedCells),
            checkPosDiag(board, shape, crossedCells),
        )

        for (combo: MutableList<List<Int>> in allCombos) {
            if (combo.count() == comboNumber) {
                score[shape] = score[shape]!! + 1
                return combo
            }
        }

        return emptyList
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
    private fun foundCombo(
        i: Int,
        j: Int,
        board: List<MutableList<String>>,
        shape: String,
        crossedCells: MutableList<List<Int>>,
        combo: MutableList<List<Int>>
    ): Boolean {

        formComboCells(i, j, board, shape, crossedCells, combo)

        if (combo.count() == comboNumber) {
            // Add new crossed cells if a combo was found
            addCrossedCells(crossedCells, combo)
            return true
        }

        return false
    }
}

