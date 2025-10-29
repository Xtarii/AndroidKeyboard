package android.open.keyboard.defaults.layout.utils

import android.open.keyboard.R
import android.open.keyboard.defaults.layout.buttons.KeyboardButton
import android.open.keyboard.extensions.interfaces.keyboardContext
import android.open.keyboard.utils.shift.ShiftState
import android.view.KeyEvent
import androidx.compose.foundation.layout.Arrangement
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
fun KeyboardUtilsRow(
    alphabetView: Boolean,
    setAlphabetView: (Boolean) -> Unit,
    shift: ShiftState,
    setShift: (ShiftState) -> Unit,

    specialView: Boolean
) {
    val context = keyboardContext

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KeyboardButton(
                onClick = { setAlphabetView(!alphabetView) },

                color = Color(0.2f, 0.7f, 0.8f, 0.9f),
                width = 35.dp
            ) {
                Text(
                    if (alphabetView) "?123" else "ABC",
                    color = Color(0.9f, 0.9f, 0.9f)
                )
            }



            KeyboardButton(if(specialView) "<" else ",") {
                context.putText(if(specialView) "<" else ",")
                setShift(shift.off())
            }

            KeyboardButton(
                onClick = {
                    context.putText(" ")
                },

                width = 175.dp
            ) { /* Space Button */ }

            KeyboardButton(if(specialView) ">" else ".") {
                context.putText(if(specialView) ">" else ". ")
                if(!specialView && shift == ShiftState.OFF) setShift(ShiftState.ON)
            }

            KeyboardButton(
                onClick = {
                    context.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_ENTER
                        )
                    )
                    setShift(shift.off())
                    setAlphabetView(true) /* Every enter sends the user back to alphabet view */
                },

                color = Color(0.2f, 0.7f, 0.8f, 0.9f),
                width = 35.dp
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_enter_outline),
                    modifier = Modifier.fillMaxSize(0.5f),
                    contentDescription = "return",
                    tint = Color(0.9f, 0.9f, 0.9f)
                )
            }
        }
    }
}
