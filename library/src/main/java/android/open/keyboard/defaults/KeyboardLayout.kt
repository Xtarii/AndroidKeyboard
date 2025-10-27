package android.open.keyboard.defaults

import android.open.keyboard.Keyboard
import android.open.keyboard.defaults.layout.utils.KeyboardUtilsRow
import android.open.keyboard.defaults.layout.views.AlphabeticView
import android.open.keyboard.defaults.layout.views.NumberView
import android.open.keyboard.extensions.annotations.Extension
import android.open.keyboard.extensions.interfaces.IComposableExtension
import android.open.keyboard.extensions.interfaces.IComposeLayout
import android.open.keyboard.extensions.interfaces.getExtensions
import android.open.keyboard.extensions.objects.IObject
import android.view.inputmethod.EditorInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



/**
 * Keyboard Shift State
 */
enum class ShiftState {
    /**
     * OFF State
     */
    OFF,
    /**
     * ON State
     */
    ON,
    /**
     * Capslock on
     */
    CAPSLOCK
}



@Extension(ID = "android.open.keyboard.defaults.KeyboardLayout", description = "Simple Compose Keyboard Layout")
class KeyboardLayout : IComposeLayout {
    /**
     * Keyboard Layout content
     */
    private var content: (@Composable () -> Unit)? = null

    /**
     * Keyboard Extensions with layout
     */
    private lateinit var extensions: HashMap<String, IObject<Extension, IComposableExtension>>



    override fun onCreate(context: Keyboard) {
        extensions = IComposableExtension.getExtensions(context)
    }



    @Composable
    override fun Layout(context: Keyboard) {
        var alphabeticView by remember { mutableStateOf(true) }
        var shift by remember { mutableStateOf(ShiftState.ON) }
        var specialView by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(Color(0.2f, 0.2f, 0.2f, 0.95f)),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {



                /* Handle content view */



                Box(modifier = Modifier.fillMaxWidth().height(75.dp)) {

                    /* Extension View */

                }

                Box(modifier = Modifier) {
                    if(alphabeticView) AlphabeticView(shift) { shift = it }
                    else NumberView { specialView = it }
                }

                Box(modifier = Modifier) {
                    KeyboardUtilsRow(
                        alphabeticView,
                        { alphabeticView = it },
                        shift,
                        { shift = it },

                        specialView
                    )
                }
            }
        }
    }



    override fun loadContentIntoView(content: @Composable (() -> Unit)) {
        this.content = content
    }

    override fun unloadContentFromView() { content = null }



    override fun onResume(context: Keyboard, info: EditorInfo) {
    }

    override fun onPause(context: Keyboard) {
    }



    override fun onDestroy(context: Keyboard) {
    }
}
