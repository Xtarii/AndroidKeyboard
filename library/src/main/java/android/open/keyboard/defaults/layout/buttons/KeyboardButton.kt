package android.open.keyboard.defaults.layout.buttons

import android.view.MotionEvent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun KeyboardButton(key: String, maxDelay: Long = 250, onClick: (hold: Boolean) -> Unit = {}) {
    KeyboardButton(onClick = onClick, maxDelay = maxDelay) {
        Text(
            key,
            modifier = Modifier,
            fontSize = 20.sp
        )
    }
}



@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun KeyboardButton(onClick: (hold: Boolean) -> Unit = {}, width: Dp = 25.dp, color: Color = Color(0.3f, 0.3f, 0.3f, 0.9f), minDelay: Long = 5, maxDelay: Long = 250, delayDecayFactor: Float = .15f, content: @Composable () -> Unit = {}) {
    val currentClick by rememberUpdatedState(onClick)
    var hold by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier
            .height(35.dp)
            .width(width).pointerInteropFilter {
                hold = when(it.action) {
                    MotionEvent.ACTION_DOWN -> true
                    else -> false
                }
                true
            },
        contentPadding = PaddingValues(0.dp),

        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = color
        ),

        onClick = { }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            content()
        }
    }


    LaunchedEffect(hold) {
        var currentDelay = maxDelay

        currentClick(false) // On Click without hold

        while(hold) {
            currentClick(true) // Click with hold

            delay(currentDelay)
            currentDelay = (currentDelay - (currentDelay * delayDecayFactor))
                .toLong()
                .coerceAtLeast(minDelay)
        }
    }
}
