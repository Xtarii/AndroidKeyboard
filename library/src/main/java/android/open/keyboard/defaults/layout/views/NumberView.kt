package android.open.keyboard.defaults.layout.views

import android.open.keyboard.defaults.layout.views.special.MainNumberView
import android.open.keyboard.defaults.layout.views.special.SecondNumberView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun NumberView(changedView: (special: Boolean) -> Unit) {
    var firstView by remember { mutableStateOf(true) }


    Box(modifier = Modifier.fillMaxWidth()) {
        if(firstView) MainNumberView {
            firstView = false
            changedView(true)
        }
        else SecondNumberView {
            firstView = true
            changedView(false)
        }
    }
}
