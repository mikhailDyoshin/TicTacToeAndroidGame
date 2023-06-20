fun createBoardMatrix(boardSize: Int): List<MutableList<String>> {

    return List(boardSize) { MutableList(boardSize) { " " } }
}

fun createScore(keys: List<String>): MutableMap<String, Int> {
    return keys.associateWith { 0 }.toMutableMap()
}

fun clearBoardMatrix(boardMatrix: List<MutableList<String>>) {
    for (row: MutableList<String> in boardMatrix) {
        for ((index, _) in row.withIndex()) {
            row[index] = " "
        }
    }
}

fun clearCrossedCells(crossedCells: MutableList<List<Int>>) {
    crossedCells.clear()
}

fun clearScore(score: MutableMap<String, Int>) {
    for (key: String in score.keys) {
        score[key] = 0
    }
}

fun createMapToTrackClickedCellsOwner(boardSize: Int): MutableMap<List<Int>, Boolean> {
    val m = mutableMapOf<List<Int>, Boolean>()
    for (row in 0 until boardSize) {
        for (col in 0 until boardSize) {
            m[listOf(row, col)] = false
        }
    }

    return m
}

fun flipTrueValues(map: MutableMap<List<Int>, Boolean>): MutableMap<List<Int>, Boolean> {
    val flippedMap = mutableMapOf<List<Int>, Boolean>()

    for ((key, value) in map) {
        val flippedValue = if (value) false else value
        flippedMap[key] = flippedValue
    }

    return flippedMap
}