package com.example.tictactoegamecompose.presentation.cell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoegamecompose.R
import com.example.tictactoegamecompose.presentation.svgColorSetter

@Composable
fun CellScreen(
    row: Int,
    col: Int,
    cellState: CellState,
    updateBoard: () -> Unit,
    changeGameState: (row: Int, col: Int) -> Unit,
) {


    // --------------------- States ---------------------

    // Background color of the cell
    var color: Color by remember {
        mutableStateOf(Color.White)
    }

    // --------------------- Changing States ---------------------

    val svgColor = svgColorSetter()

    // Change background of the cell when it's crossed
    color = if (cellState.crossed) {
        Color.Red
    } else {
        MaterialTheme.colorScheme.background
    }

    // --------------------- Rendering Section ---------------------
    IconButton(
        onClick = {
            changeGameState(row, col)
            updateBoard()
        },
        modifier = Modifier
            .border(1.dp, svgColor)
            .padding(0.dp)
            .background(color)
    ) {
        if (cellState.imageID != 0) {
            Image(
                painter = painterResource(id = cellState.imageID),
                contentDescription = "cell image",
                colorFilter = ColorFilter.tint(svgColor)
            )
        } else {
            // display nothing
        }

    }
}

@Preview
@Composable
fun CellEmptyPreview() {
    CellScreen(
        row = 0,
        col = 0,
        cellState = CellState(imageID = 0, crossed = false),
        updateBoard = {},
        changeGameState = { _, _ -> },
    )
}

@Preview
@Composable
fun CellCrossPreview() {
    CellScreen(
        row = 0,
        col = 0,
        cellState = CellState(imageID = R.drawable.cross, crossed = false),
        updateBoard = {},
        changeGameState = { _, _ -> },
    )
}

@Preview
@Composable
fun CellCirclePreview() {
    CellScreen(
        row = 0,
        col = 0,
        cellState = CellState(imageID = R.drawable.circle, crossed = false),
        updateBoard = {},
        changeGameState = { _, _ -> },
    )
}

@Preview
@Composable
fun CellTrianglePreview() {
    CellScreen(
        row = 0,
        col = 0,
        cellState = CellState(imageID = R.drawable.triangle, crossed = false),
        updateBoard = {},
        changeGameState = { _, _ -> },
    )
}

@Preview
@Composable
fun CellRectanglePreview() {
    CellScreen(
        row = 0,
        col = 0,
        cellState = CellState(imageID = R.drawable.rectangle, crossed = false),
        updateBoard = {},
        changeGameState = { _, _ -> },
    )
}

@Preview
@Composable
fun CellCrossedPreview() {
    CellScreen(
        row = 0,
        col = 0,
        cellState = CellState(imageID = R.drawable.cross, crossed = true),
        updateBoard = {},
        changeGameState = { _, _ -> },
    )
}
