package android.open.keyboard.defaults.layout.buttons

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyboardButton(key: String, onClick: () -> Unit = {}) {
    KeyboardButton(onClick = onClick) {
        Text(
            key,
            modifier = Modifier,
            fontSize = 20.sp
        )
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KeyboardButton(onClick: () -> Unit = {}, width: Dp = 25.dp, color: Color = Color(0.3f, 0.3f, 0.3f, 0.9f), content: @Composable () -> Unit = {}) {
    Button(
        modifier = Modifier.height(35.dp).width(width),
        contentPadding = PaddingValues(0.dp),

        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = color
        ),

        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            content()
        }
    }
}
