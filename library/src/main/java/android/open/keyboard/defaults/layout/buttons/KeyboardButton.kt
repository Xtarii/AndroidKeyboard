package android.open.keyboard.defaults.layout.buttons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyboardButton(key: String, onLongClick: () -> Unit = {}, onClick: () -> Unit = {}) {
    KeyboardButton(onClick = onClick, onLongClick = onLongClick) {
        Text(
            key,
            modifier = Modifier,
            fontSize = 20.sp
        )
    }
}



@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun KeyboardButton(onLongClick: () -> Unit = {}, onClick: () -> Unit = {}, width: Dp = 25.dp, color: Color = Color(0.3f, 0.3f, 0.3f, 0.9f), content: @Composable () -> Unit = {}) {
    Box(
        modifier = Modifier.height(35.dp).width(width)
            .background(color = color, shape = RoundedCornerShape(7.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            content()
        }
    }
}
