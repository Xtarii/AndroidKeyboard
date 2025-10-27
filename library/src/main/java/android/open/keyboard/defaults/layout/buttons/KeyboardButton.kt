package android.open.keyboard.defaults.layout.buttons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun KeyboardButton(key: String, onHold: () -> Unit = {}, onClick: () -> Unit = {}) {
    KeyboardButton(onClick = onClick, onHold = onHold) {
        Text(
            key,
            modifier = Modifier,
            fontSize = 20.sp,
            color = Color(0.9f, 0.9f, 0.9f)
        )
    }
}



@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun KeyboardButton(
    onHold: () -> Unit = {},
    onClick: () -> Unit = {},

    width: Dp = 25.dp,
    color: Color = Color(0.3f, 0.3f, 0.3f, 0.9f),

    onHoldInterval: Long = 100,
    holdDelay: Long  = onHoldInterval,

    content: @Composable () -> Unit = {}
) {

    val scope = rememberCoroutineScope()
    var isHold by remember { mutableStateOf(false) }

    val currentOnClick by rememberUpdatedState(onClick)
    val currentOnHold by rememberUpdatedState(onHold)
    val currentOnHoldInterval by rememberUpdatedState(onHoldInterval)
    val currentHoldDelay by rememberUpdatedState(holdDelay)

    Surface(
        modifier = Modifier
            .height(40.dp)
            .width(width)
            .pointerInput(Unit) {
                awaitEachGesture {
                    awaitFirstDown(requireUnconsumed = false)
                    val holdJob = scope.launch {
                        delay(currentHoldDelay)

                        isHold = true
                        while (isHold) {
                            currentOnHold()
                            delay(currentOnHoldInterval)
                        }
                    }

                    waitForUpOrCancellation()
                    if(!isHold) currentOnClick()

                    holdJob.cancel()
                    isHold = false
                }
            },
        shape = RoundedCornerShape(7.dp),
        color = color,
        tonalElevation = 2.dp,
        contentColor = contentColorFor(color)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            content()
        }
    }
}
