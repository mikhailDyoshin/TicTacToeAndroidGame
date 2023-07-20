package com.example.tictactoegamecompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RadioButtonGroup(
    options: List<String>,
    annotation: String,
    setConfiguration: (String) -> Unit,
    disabledButtons: MutableList<String>,
    images: List<Int> = listOf(),
    showImages: Boolean = false,
) {
    var selectedOption by remember { mutableStateOf(options[0]) }

    var showImagesState by remember {
        mutableStateOf(false)
    }

    val svgColor = svgColorSetter()

    showImagesState = showImages

    Column(
        modifier = Modifier.padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = annotation, fontSize = 30.sp)

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            options.forEach { text ->
                Column(verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.selectable(selected = (text == selectedOption),
                            onClick = { selectedOption = text })) {

                    RadioButton(
                        selected = (text == selectedOption), onClick = {
                            selectedOption = text
                            setConfiguration(selectedOption)
                        }, enabled = text !in disabledButtons
                    )

                    if (!showImagesState) {
                        Text(
                            text = text
                        )
                    } else {
                        Image(
                            painter = painterResource(id = images[options.indexOf(text)]),
                            contentDescription = "RadioButtonImage",
                            modifier = Modifier.size(28.dp),
                            colorFilter = ColorFilter.tint(svgColor)
                        )

                    }

                }
            }
        }
    }


}