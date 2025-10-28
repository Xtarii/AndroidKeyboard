package android.open.keyboard.defaults

import android.open.keyboard.Keyboard
import android.open.keyboard.defaults.layout.utils.ExtensionLayout
import android.open.keyboard.defaults.layout.utils.KeyboardUtilsRow
import android.open.keyboard.defaults.layout.utils.Lexicon
import android.open.keyboard.defaults.layout.views.AlphabeticView
import android.open.keyboard.defaults.layout.views.NumberView
import android.open.keyboard.extensions.annotations.Extension
import android.open.keyboard.extensions.interfaces.IComposableExtension
import android.open.keyboard.extensions.interfaces.IComposeLayout
import android.open.keyboard.extensions.interfaces.getExtensions
import android.open.keyboard.extensions.objects.IObject
import android.open.keyboard.utils.shift.ShiftState
import android.view.inputmethod.EditorInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData

@Extension(ID = "android.open.keyboard.defaults.KeyboardLayout", description = "Simple Compose Keyboard Layout")
class KeyboardLayout : IComposeLayout {
    /**
     * Keyboard Layout extension content
     */
    private var content: IComposableExtension? = null

    /**
     * Keyboard layout view
     */
    private val alphabeticView: MutableState<Boolean> = mutableStateOf(true)

    /**
     * Keyboard shift state
     */
    private val shift: MutableState<ShiftState> = mutableStateOf(ShiftState.ON)

    /**
     * Keyboard special view ( special number view )
     */
    private val specialView: MutableState<Boolean> = mutableStateOf(false)

    /**
     * Keyboard Lexicon
     */
    private val lexicon: MutableState<List<String>> = mutableStateOf(ArrayList())

    /**
     * Keyboard Buffer
     */
    private val buffer: MutableState<String> = mutableStateOf("")



    /**
     * Keyboard Extensions with layout
     */
    private lateinit var extensions: HashMap<String, IObject<Extension, IComposableExtension>>

    /**
     * Keyboard Lexicon Manager
     */
    private lateinit var lexiconManager: Lexicon



    override fun onCreate(context: Keyboard) {
        extensions = IComposableExtension.getExtensions(context)
        lexiconManager = Lexicon(context)
    }



    @Composable
    override fun Layout(context: Keyboard) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(285.dp)
                .padding(0.dp)
                .background(Color(0.2f, 0.2f, 0.2f, 0.95f)),
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxWidth().height(45.dp)) {
                    if(lexicon.value.isEmpty()) ExtensionLayout(extensions)
                    else Row(modifier = Modifier.fillMaxSize()) {
                        Box(modifier = Modifier
                            .padding(start = 5.dp, end = 15.dp)
                            .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                modifier = Modifier.size(30.dp),
                                onClick = { lexicon.value = ArrayList() },
                                colors = ButtonDefaults.buttonColors().copy(
                                    containerColor = Color(0.2f, 0.7f, 0.8f, 0.9f)
                                )
                            ) {}
                        }
                        Box(modifier = Modifier) {
                            Lexicon(buffer.value, shift.value, lexicon.value, lexiconManager)
                        }
                    }
                }

                Box(modifier = Modifier) {
                    if(alphabeticView.value) AlphabeticView(
                        shift.value,
                        {shift.value = it},
                    )
                    else NumberView { specialView.value = it }
                }

                Box(modifier = Modifier) {
                    KeyboardUtilsRow(
                        alphabeticView.value,
                        { alphabeticView.value = it },
                        shift.value,
                        { shift.value = it },

                        specialView.value
                    )
                }
            }
        }
    }



    override fun loadExtensionIntoView(extension: IComposableExtension) {
        this.content = extension
    }

    override fun unloadExtensionFromView() {
        content = null
    }



    override fun onResume(context: Keyboard, info: EditorInfo) {

        /* Resets variables */

        alphabeticView.value = true
        specialView.value = false
        shift.value = ShiftState.ON
        lexicon.value = listOf()
    }

    override fun onPause(context: Keyboard) {
    }



    override fun onDestroy(context: Keyboard) {
    }



    override fun onBufferChange(context: Keyboard, buffer: StringBuffer) {
        if(buffer.isEmpty()) lexicon.value = listOf()
        else {
            this.buffer.value = buffer.toString()
            val res = lexiconManager.getMatches(buffer.toString())
            if(!res.isEmpty()) lexicon.value = res
            else lexicon.value = listOf("")
        }
    }
}
