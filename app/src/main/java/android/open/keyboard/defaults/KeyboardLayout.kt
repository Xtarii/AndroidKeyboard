package android.open.keyboard.defaults

import android.open.keyboard.Keyboard
import android.open.keyboard.extensions.annotations.Extension
import android.open.keyboard.extensions.interfaces.IComposeLayout
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Extension(ID = "android.open.keyboard.defaults.KeyboardLayout", description = "Simple Compose Keyboard Layout")
class KeyboardLayout : IComposeLayout {
    override fun onCreate(context: Keyboard) {
    }



    @Composable
    override fun Layout(context: Keyboard, content: @Composable () -> Unit) {
        Column(modifier = Modifier.fillMaxWidth().height(500.dp)) {
            Button({
                context.currentInputConnection.sendKeyEvent(KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_A
                ))
            }) {
                Text("A")
            }
        }
    }



    override fun onResume(context: Keyboard, info: EditorInfo) {
    }

    override fun onPause(context: Keyboard) {
    }



    override fun onDestroy(context: Keyboard) {
    }
}
