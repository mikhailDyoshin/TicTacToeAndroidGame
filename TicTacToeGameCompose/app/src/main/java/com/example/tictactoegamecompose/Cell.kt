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
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tictactoegamecompose.R
import com.example.tictactoegamecompose.svgColorSetter

@Composable
fun Cell(
    checkForCombo: () -> Unit,
    addCoordinates: (List<Int>) -> Unit,
    changeTurn: () -> Unit,
    checkForWinner: () -> Unit,
    currentTurn: String,
    clearBoard: Boolean,
    buttonIndex: List<Int>,
    wasClickedByAI: Boolean,
    crossed: Boolean,
    images: Map<String, Int>,
    AIturn: String,
    gameMode: String,
) {

    // --------------------- States ---------------------

    // Tracking if the button was clicked by AI
    var wasClickedByAIState: Boolean by remember {
        mutableStateOf(false)
    }

    // The state stores the coordinates of the cell
    val index: List<Int> by remember {
        mutableStateOf(buttonIndex)
    }

    // Background color of the cell
    var color: Color by remember {
        mutableStateOf(Color.White)
    }

    // Check if the cells is crossed or not
    var crossedState: Boolean by remember {
        mutableStateOf(false)
    }

    // Image displayed inside the cell depending on the current turn
    var image: Int by remember {
        mutableStateOf(0)
    }

    var gameModeState: String by remember {
        mutableStateOf("")
    }

    var AIturnState: String by remember {
        mutableStateOf("")
    }

    // --------------------- Changing States ---------------------

    fun changeStateOnClick() {
        addCoordinates(index)
        checkForCombo()
        if (image == 0) {
            image = images[currentTurn]!!
            changeTurn()
        }
        checkForWinner()
    }

    gameModeState = gameMode

    AIturnState = AIturn

    wasClickedByAIState = wasClickedByAI

    crossedState = crossed

    val svgColor = svgColorSetter()

    // Change background of the cell when it's crossed
    color = if (crossedState) {
        Color.Red
    } else {
        MaterialTheme.colorScheme.background
    }

    // Hide the image of the cell when the board is cleared
    if (clearBoard) {
        image = 0
    }

    // This condition allows us to simulate button clicking by the AI
    if (wasClickedByAIState) {
        changeStateOnClick()
    }



    // --------------------- Rendering Section ---------------------
    IconButton(
        onClick = {
            // The conditions are used to prevent button clicking while it's AI's turn
            if (gameModeState == "VS computer") {
                if (currentTurn != AIturnState) {
                    changeStateOnClick()
                }
            } else {
                changeStateOnClick()
            }
        },
        modifier = Modifier
            .border(1.dp, svgColor)
            .padding(0.dp)
            .background(color)
    ) {
        if (image != 0) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "cell image",
                colorFilter = ColorFilter.tint(svgColor)
            )
        } else {
            // display nothing
        }

    }
}
