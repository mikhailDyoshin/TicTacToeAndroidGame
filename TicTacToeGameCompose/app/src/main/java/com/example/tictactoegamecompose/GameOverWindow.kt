import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size

@Composable
fun GameOverWindow(
    winner: String,
    score: MutableMap<String, Int>,
    restartGame: () -> Unit,
    images: Map<String, Int>,
) {

    // Style values
    val h1FontSize = 44
    val h2FontSize = 30
    val textFontSize = 24
    val buttonFontSize = 40
    val spaceBetween = 60

    val sortedScore = score.toList().sortedByDescending { (_, value) -> value }.toMap()


    // -------------------- Rendering Section --------------------
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        // Winner
        if (winner == "") {
            Text(text = "Tie", fontSize = h1FontSize.sp)
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = images[winner]!!),
                    contentDescription = "Current turn",
                    modifier = Modifier.size(72.dp)
                )
                Text(text = "won!", fontSize = h1FontSize.sp)
            }

        }

        // Score
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = spaceBetween.dp,)
        ) {
            Text(text = "Score", fontSize = h2FontSize.sp)
            for ((index, player) in sortedScore.keys.withIndex()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${index+1}.",
                        fontSize = textFontSize.sp
                    )
                    Image(
                        painter = painterResource(id = images[player]!!),
                        contentDescription = "Current turn",
                    )
                    Text(
                        text = ": ${sortedScore[player]}",
                        fontSize = textFontSize.sp
                    )
                }
            }
        }

        // Replay button
        Button(onClick = { restartGame() },
                modifier = Modifier.padding(top = spaceBetween.dp)) {
            Text(
                text = "Replay", fontSize = buttonFontSize.sp,
            )
        }
    }
}