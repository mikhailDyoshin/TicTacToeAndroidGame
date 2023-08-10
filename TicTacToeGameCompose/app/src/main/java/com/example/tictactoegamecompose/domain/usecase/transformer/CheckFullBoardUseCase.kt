package com.example.tictactoegamecompose.domain.usecase.transformer

class CheckFullBoardUseCase {

    fun execute(board: List<MutableList<String>>): Boolean {
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