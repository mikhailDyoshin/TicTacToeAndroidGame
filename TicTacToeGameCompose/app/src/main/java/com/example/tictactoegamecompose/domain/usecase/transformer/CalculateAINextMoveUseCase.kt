package com.example.tictactoegamecompose.domain.usecase.transformer

import com.example.tictactoegamecompose.domain.models.BoardStateModel
import java.lang.Integer.max
import java.lang.Math.min

data class Move(var coordinates: List<Int> = listOf(), var score: Int = 0)
class CalculateAINextMoveUseCase(boardState: BoardStateModel) {


    private val boardStateCopy = BoardStateModel(
        board = boardState.board.toList(),
        crossedCells = boardState.crossedCells.toMutableList(),
        currentTurn = boardState.currentTurn,
        aiPlayerShape = boardState.aiPlayerShape,
        comboNumber = boardState.comboNumber,
    )

    private val huPlayer = boardStateCopy.currentTurn

    private val aiPlayer = boardStateCopy.aiPlayerShape

    private var depth = 0

    private val maxDepth = 2

    private val crossedCells = boardStateCopy.crossedCells

    private val comboTracker = CheckForComboUseCase()

    private val board = boardStateCopy.board

    private val comboNumber = boardStateCopy.comboNumber

    fun execute(): List<Int> {
        return minimax(board, aiPlayer, Int.MIN_VALUE, Int.MAX_VALUE).coordinates
    }

    private fun getEmptyCellsCoordinates(
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
        player: String,
        alpha: Int,
        beta: Int,
    ): Move {

        val availableCells = getEmptyCellsCoordinates(board)

        val moves: MutableList<Move> = mutableListOf()

        when {
            // Combo for human player is found
            comboTracker.execute(
                boardState = BoardStateModel(
                    board = board,
                    currentTurn = huPlayer,
                    aiPlayerShape = aiPlayer,
                    crossedCells = crossedCells,
                    comboNumber = comboNumber
                )
            ) -> {
                depth--
                return Move(score = -10, coordinates = listOf())
            }
            // Combo for AI player is found
            comboTracker.execute(
                boardState = BoardStateModel(
                    board = board,
                    currentTurn = aiPlayer,
                    aiPlayerShape = huPlayer,
                    crossedCells = crossedCells,
                    comboNumber = comboNumber
                )
            ) -> {
                depth--
                return Move(score = 10, coordinates = listOf())
            }
            // The board is full (no empty cells)
            availableCells.isEmpty() -> {
                depth--
                return Move(score = 0, coordinates = listOf())
            }
            // The depth of the tree is more than max depth
            depth > maxDepth -> {
                depth--
                return Move(score = 0, coordinates = listOf())
            }

            else -> {
                // loop through available cells
                for (emptyCell in availableCells) {
                    // create an object for each and store the index of that spot that was stored as a number in the object's index key
                    val move = Move()

                    depth++

                    // set the empty spot to the current player
                    board[emptyCell[0]][emptyCell[1]] = player

                    //if collect the score resulted from calling minimax on the opponent of the current player
                    if (player == aiPlayer) {
                        val moveScore = minimax(board, aiPlayer, alpha, beta).score
                        val newAlpha = max(alpha, moveScore)

                        if (newAlpha >= beta) {
                            break // Beta cut-off
                        } else {
                            move.score = moveScore
                        }

                    } else {
                        val moveScore = minimax(board, aiPlayer, alpha, beta).score
                        val newBeta = min(beta, moveScore)

                        if (newBeta <= alpha) {
                            break // Alpha cut-off
                        } else {
                            move.score = moveScore
                        }

                    }

                    move.coordinates = emptyCell

                    //reset the spot to empty
                    board[emptyCell[0]][emptyCell[1]] = " "

                    // push the object to the array
                    moves.add(move)
                }

                var bestMove = 0
                // If it's AI player in the terminate node
                // Then loop over the moves and choose the move with the highest score
                if (player == aiPlayer) {
                    var worstScore = -10000
                    for ((i, _) in moves.withIndex()) {
                        if (moves[i].score > worstScore) {
                            worstScore = moves[i].score
                            bestMove = i
                        }
                    }
                    // else loop over the moves and choose the move with the lowest score
                } else {
                    var worstScore = 10000
                    for ((i, _) in moves.withIndex()) {
                        if (moves[i].score < worstScore) {
                            worstScore = moves[i].score
                            bestMove = i
                        }
                    }
                }

                depth--
                return moves[bestMove]
            }

        }

    }
}
