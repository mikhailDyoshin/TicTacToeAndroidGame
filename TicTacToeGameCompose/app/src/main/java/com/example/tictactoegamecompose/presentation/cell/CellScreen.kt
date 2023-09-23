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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoegamecompose.presentation.svgColorSetter

@Composable
fun CellScreen(
    buttonCoordinates: List<Int>,
    checkGameStatus: () -> Unit,
    updateBoard: () -> Unit,
    viewModel: CellViewModel = viewModel()
) {

    // --------------------- States ---------------------

    // Background color of the cell
    var color: Color by remember {
        mutableStateOf(Color.White)
    }

    // Cell coordinates
    val row = buttonCoordinates[0]
    val col = buttonCoordinates[1]

    val cell = viewModel.state.value[row][col]

    // --------------------- Changing States ---------------------

    val svgColor = svgColorSetter()

    // Change background of the cell when it's crossed
    color = if (cell.crossed) {
        Color.Red
    } else {
        MaterialTheme.colorScheme.background
    }

    // --------------------- Rendering Section ---------------------
    IconButton(
        onClick = {
            viewModel.changeGameState(buttonCoordinates)
            updateBoard()
            checkGameStatus()
        },
        modifier = Modifier
            .border(1.dp, svgColor)
            .padding(0.dp)
            .background(color)
    ) {
        if (cell.imageID != 0) {
            Image(
                painter = painterResource(id = cell.imageID),
                contentDescription = "cell image",
                colorFilter = ColorFilter.tint(svgColor)
            )
        } else {
            // display nothing
        }

    }
}
