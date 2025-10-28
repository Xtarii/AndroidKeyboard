package android.open.keyboard.defaults.layout.utils

import android.open.keyboard.defaults.Lexicon
import android.open.keyboard.extensions.interfaces.keyboardContext
import android.open.keyboard.utils.shift.ShiftState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Lexicon(buffer: String, shift: ShiftState, words: List<String>, lexicon: Lexicon, onInsert: () -> Unit) {
    val context = keyboardContext

    Box(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                TextButton(
                    modifier = Modifier,
                    onClick = {
                        lexicon.add(buffer)
                        context.putText(" ")
                        onInsert()
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.textButtonColors().copy(
                        contentColor = Color(0.9f, 0.9f, 0.9f)
                    )
                ) {
                    Text("\"${buffer}\"", fontSize = 17.sp)
                }
            }

            items(words) {
                if(it == "") return@items
                TextButton(
                    modifier = Modifier,
                    onClick = {
                        context.currentInputConnection.deleteSurroundingText(
                            buffer.length,
                            0
                        )
                        context.putText((if(shift != ShiftState.OFF) it.uppercase() else it) + " ")
                        onInsert()
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.textButtonColors().copy(
                        contentColor = Color(0.9f, 0.9f, 0.9f)
                    )
                ) {
                    Text("\"${if(shift != ShiftState.OFF) it.uppercase() else it}\"", fontSize = 17.sp)
                }
            }
        }
    }
}
