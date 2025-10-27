package android.open.keyboard.defaults.layout.views

import android.open.keyboard.R
import android.open.keyboard.defaults.ShiftState
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
fun AlphabeticView(shift: ShiftState, setShift: (ShiftState) -> Unit) {
    val context = keyboardContext

    val qp = arrayOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "å")
    val al = arrayOf("a", "s", "d", "f", "g", "h", "j", "k", "l", "ö", "ä")
    val zm = arrayOf("z", "x", "c", "v", "b", "n", "m")

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for(i in qp) {
                Box(modifier = Modifier) {
                    KeyboardButton(if(shift != ShiftState.OFF) i.uppercase() else i) {
                        context.putText(if(shift != ShiftState.OFF) i.uppercase() else i)
                        if(shift == ShiftState.ON) setShift(ShiftState.OFF)
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
                    KeyboardButton(if(shift != ShiftState.OFF) i.uppercase() else i) {
                        context.putText(if(shift != ShiftState.OFF) i.uppercase() else i)
                        if(shift == ShiftState.ON) setShift(ShiftState.OFF)
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
                    val current = shift.ordinal
                    val max = ShiftState.entries.size - 1

                    var next = current + 1
                    if(next > max) next = 0

                    setShift(ShiftState.entries[next])
                },
                color = Color(0.2f, 0.7f, 0.8f, 0.9f),
                width = 45.dp
            ) {
                Icon(
                    painter = painterResource(
                        when (shift) {
                            ShiftState.CAPSLOCK -> R.drawable.arrow_up_filled_double
                            ShiftState.ON -> R.drawable.arrow_up_filled
                            else -> R.drawable.arrow_up_outline
                        }
                    ),
                    modifier = Modifier.fillMaxSize(0.5f),
                    contentDescription = "Shift",
                    tint = Color(0.9f, 0.9f, 0.9f)
                )
            }

            for(i in zm) {
                Box(modifier = Modifier) {
                    KeyboardButton(if(shift != ShiftState.OFF) i.uppercase() else i) {
                        context.putText(if(shift != ShiftState.OFF) i.uppercase() else i)
                        if(shift == ShiftState.ON) setShift(ShiftState.OFF)
                    }
                }
            }

            KeyboardButton(
                onClick = {
                    context.erase()
                    if(shift == ShiftState.ON) setShift(ShiftState.OFF)
                },
                onHold = {
                    context.erase()
                    if(shift == ShiftState.ON) setShift(ShiftState.OFF)
                },

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
