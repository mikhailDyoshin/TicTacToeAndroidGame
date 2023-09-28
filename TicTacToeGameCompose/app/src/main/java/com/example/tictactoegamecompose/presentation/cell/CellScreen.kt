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
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoegamecompose.presentation.svgColorSetter

@Composable
fun CellScreen(
    row: Int,
    col: Int,
    cellState: CellState,
    checkGameStatus: () -> Unit,
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
            changeGameState(row,  col)
            updateBoard()
            checkGameStatus()
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
