package android.open.keyboard.defaults.layout.views.special

import android.open.keyboard.R
import android.open.keyboard.defaults.layout.buttons.KeyboardButton
import android.open.keyboard.extensions.interfaces.keyboardContext
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SecondNumberView(changeView: () -> Unit) {
    val context = keyboardContext

    val firstRow = arrayOf("~", "`", "|", "•", "√", "π", "÷", "×", "§", "∆")
    val secondRow = arrayOf("£", "¥", "$", "¢", "^", "°", "=", "{", "}", "\\")
    val thirdRow = arrayOf("%", "©", "®", "™", "✓", "[", "]")

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for(i in firstRow) {
                Box(modifier = Modifier) {
                    KeyboardButton(i) { context.putText(i) }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for(i in secondRow) {
                Box(modifier = Modifier) {
                    KeyboardButton(i) { context.putText(i) }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KeyboardButton(
                onClick = { changeView() },

                color = Color(0.2f, 0.7f, 0.8f, 0.9f),
                width = 45.dp
            ) {
                Text("=\\<", color = Color(0.9f, 0.9f, 0.9f))
            }

            for (i in thirdRow) {
                Box(modifier = Modifier) {
                    KeyboardButton(i) { context.putText(i) }
                }
            }

            KeyboardButton(
                onClick = { context.erase() },
                onHold = { context.erase() },

                color = Color(0.2f, 0.7f, 0.8f, 0.9f),
                width = 45.dp
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_left_outline),
                    modifier = Modifier.fillMaxSize(0.5f),
                    contentDescription = "Undo",
                    tint = Color(0.9f, 0.9f, 0.9f)
                )
            }
        }
    }
}
