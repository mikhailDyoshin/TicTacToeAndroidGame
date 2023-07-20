package com.example.tictactoegamecompose.bl

class WinTracker {
    fun getWinner(score: MutableMap<String, Int>): String {

        return score.maxByOrNull { it.value }!!.key
    }

    fun checkForWinner(
        board: List<MutableList<String>>,
        score: MutableMap<String, Int>
    ): Boolean {
        if (board.count() == 3) {
            if (1 in score.values) {
                return true
            }

            if (checkFullBoard(board)) {
                return false
            }
        } else {
            if (checkFullBoard(board)) {
                return !checkForTie(score)
            }
        }

        return false
    }

    private fun checkForTie(score: MutableMap<String, Int>): Boolean {

        val scorePoints = score.values.toList()

        val firstPlayerPoints = scorePoints[0]

        for (points in scorePoints) {
            if (points != firstPlayerPoints) {
                return false
            }
        }

        return true
    }

    fun checkFullBoard(board: List<MutableList<String>>): Boolean {
        for (row: MutableList<String> in board) {
            for (col: String in row) {
                if (col == " ") {
                    return false
                }
            }
        }

        return true
    }
}