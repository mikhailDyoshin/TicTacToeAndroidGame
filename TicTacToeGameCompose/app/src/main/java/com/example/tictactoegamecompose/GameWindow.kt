package com.example.tictactoegamecompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.tictactoegamecompose.bl.Game

@Composable
fun GameWindow(openSettingWindow: () -> Unit, game: Game) {

    val svgColor = svgColorSetter()

    LaunchedEffect(game) {
        game.restartGame()
    }

    ConstraintLayout {

        val settingsButton = createRef()
        val restartButton = createRef()

        // Open settings button
        IconButton(onClick = { openSettingWindow() },
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
        IconButton(onClick = { game.restartGame()},
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
        Board(game = game)
    }

}
