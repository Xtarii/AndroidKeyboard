package android.open.keyboard.defaults.layout.views

import android.open.keyboard.R
import android.open.keyboard.defaults.layout.buttons.KeyboardButton
import android.open.keyboard.extensions.interfaces.keyboardContext
import android.view.KeyEvent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MainView() {
    val context = keyboardContext

    var shift by remember { mutableIntStateOf(0) }

    val qp = arrayOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "å")
    val al = arrayOf("a", "s", "d", "f", "g", "h", "j", "k", "l", "ö", "ä")
    val zm = arrayOf("z", "x", "c", "v", "b", "n", "m")

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for(i in qp) {
                Box(modifier = Modifier) {
                    KeyboardButton(if(shift != 0) i.uppercase() else i) {
                        context.currentInputConnection.commitText(
                            if(shift != 0) i.uppercase() else i,
                            1
                        )
                        if(shift == 1) shift = 0
                    }
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
            for(i in al) {
                Box(modifier = Modifier) {
                    KeyboardButton(if(shift != 0) i.uppercase() else i) {
                        context.currentInputConnection.commitText(
                            if(shift != 0) i.uppercase() else i,
                            1
                        )
                        if(shift == 1) shift = 0
                    }
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
                onClick = {
                    shift++
                    if(shift > 2) shift = 0
                },
                color = Color(0.2f, 0.7f, 0.8f, 0.9f),
                width = 45.dp
            ) {
                Icon(
                    painter = painterResource(
                        when (shift) {
                            2 -> R.drawable.arrow_up_filled_double
                            1 -> R.drawable.arrow_up_filled
                            else -> R.drawable.arrow_up_outline
                        }
                    ),
                    modifier = Modifier.fillMaxSize(0.5f),
                    contentDescription = "Shift"
                )
            }

            for(i in zm) {
                Box(modifier = Modifier) {
                    KeyboardButton(if(shift != 0) i.uppercase() else i) {
                        context.currentInputConnection.commitText(
                            if(shift != 0) i.uppercase() else i,
                            1
                        )
                        if(shift == 1) shift = 0
                    }
                }
            }

            KeyboardButton(
                onClick = {
                    context.currentInputConnection.deleteSurroundingText(1, 0)
                    if(shift == 1) shift = 0
                },

                color = Color(0.2f, 0.7f, 0.8f, 0.9f),
                width = 45.dp
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_left_outline),
                    modifier = Modifier.fillMaxSize(0.5f),
                    contentDescription = "Undo"
                )
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
                onClick = {
                    // Special Character View
                },

                color = Color(0.2f, 0.7f, 0.8f, 0.9f),
                width = 35.dp
            ) {
                Text("?123")
            }

            KeyboardButton(",") {
                context.currentInputConnection.commitText(",", 1)
            }

            KeyboardButton(
                onClick = {
                    context.currentInputConnection.commitText(" ", 1)
                },

                width = 175.dp
            ) { /* Space Button */ }

            KeyboardButton(".") {
                context.currentInputConnection.commitText(".", 1)
            }

            KeyboardButton(
                onClick = {
                    context.currentInputConnection.sendKeyEvent(
                        KeyEvent(KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_ENTER
                        )
                    )
                },

                color = Color(0.2f, 0.7f, 0.8f, 0.9f),
                width = 35.dp
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_enter_outline),
                    modifier = Modifier.fillMaxSize(0.5f),
                    contentDescription = "return"
                )
            }
        }
    }
}
