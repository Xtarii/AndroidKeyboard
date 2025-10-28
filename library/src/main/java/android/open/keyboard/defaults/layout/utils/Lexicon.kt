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

                        val word = if(shift == ShiftState.CAPSLOCK)
                            it.uppercase()
                        else if(buffer[0].isUpperCase()) it.replaceFirstChar{ c ->
                            if(c.isLowerCase())
                                c.titlecase()
                            else c.toString() }
                        else it

                        context.putText("$word ")
                        onInsert()
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.textButtonColors().copy(
                        contentColor = Color(0.9f, 0.9f, 0.9f)
                    )
                ) {
                    val word = if(shift == ShiftState.CAPSLOCK)
                        it.uppercase()
                    else if(buffer[0].isUpperCase()) it.replaceFirstChar{ c ->
                        if(c.isLowerCase())
                            c.titlecase()
                        else c.toString() }
                    else it

                    Text("\"${word}\"", fontSize = 17.sp)
                }
            }
        }
    }
}
