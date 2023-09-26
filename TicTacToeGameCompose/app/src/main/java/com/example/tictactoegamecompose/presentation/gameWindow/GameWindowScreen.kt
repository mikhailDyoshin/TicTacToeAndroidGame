package com.example.tictactoegamecompose.presentation.gameWindow

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoegamecompose.R
import com.example.tictactoegamecompose.presentation.board.BoardScreen
import com.example.tictactoegamecompose.presentation.svgColorSetter

@Composable
fun GameWindowScreen(
    openSettingWindow: () -> Unit,
    checkGameStatus: () -> Unit,
    viewModel: GameWindowViewModel = viewModel(),
) {

    var updateBoard by remember { mutableStateOf(false) }


    val svgColor = svgColorSetter()


    ConstraintLayout {

        val settingsButton = createRef()
        val restartButton = createRef()

        // Open settings button
        IconButton(onClick = {
            openSettingWindow()
        },
            modifier = Modifier.constrainAs(settingsButton) {
                top.linkTo(parent.top, margin = 5.dp)
                start.linkTo(parent.start, margin = 5.dp)
            }) {
            Image(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings",
                modifier = Modifier.size(32.dp),
                colorFilter = ColorFilter.tint(svgColor)
            )
        }

        // Restart button
        IconButton(onClick = {
            viewModel.restartGame()
//            cellViewModel.getCellState()
            updateBoard = true
        },
            modifier = Modifier.constrainAs(restartButton) {
                top.linkTo(parent.top, margin = 5.dp)
                end.linkTo(parent.end, margin = 5.dp)
            }) {
            Image(
                painter = painterResource(id = R.drawable.restart),
                contentDescription = "Restart",
                modifier = Modifier.size(32.dp),
                colorFilter = ColorFilter.tint(svgColor)
            )
        }

    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BoardScreen(
            updateBoard = updateBoard,
            resetUpdateBoard = { updateBoard = false },
            checkGameStatus = checkGameStatus,
        )
    }

}
