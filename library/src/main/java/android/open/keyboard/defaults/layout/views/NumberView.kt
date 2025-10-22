package android.open.keyboard.defaults.layout.views

import android.open.keyboard.defaults.layout.buttons.KeyboardButton
import android.open.keyboard.extensions.interfaces.keyboardContext
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun NumberView() {
    val context = keyboardContext
    val numbers = arrayOf(
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in numbers) {
                Box(modifier = Modifier) {
                    KeyboardButton(i) {
                        context.currentInputConnection.commitText(i, 1)
                    }
                }
            }
        }
    }
}
