package android.open.keyboard.abstracts.layout

import android.open.keyboard.Keyboard
import android.open.keyboard.extensions.annotations.Extension
import android.open.keyboard.extensions.interfaces.IComposableExtension
import android.open.keyboard.extensions.interfaces.IComposeLayout
import android.open.keyboard.extensions.interfaces.getExtensions
import android.open.keyboard.extensions.objects.IObject
import android.open.keyboard.utils.shift.ShiftState
import android.view.inputmethod.EditorInfo
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Abstract Keyboard Layout for Compose
 */
abstract class AbstractComposeLayout : IComposeLayout {
    /**
     * Keyboard Content
     */
    private val _content: MutableState<IComposableExtension?> = mutableStateOf(null)

    /**
     * Keyboard Input Buffer
     */
    private val _buffer: MutableState<String> = mutableStateOf("")

    /**
     * Keyboard Shift State
     */
    private val _shift: MutableState<ShiftState> = mutableStateOf(ShiftState.ON)

    /**
     * Keyboard Extensions
     */
    private lateinit var _extensions: HashMap<String, IObject<Extension, IComposableExtension>>



    override fun loadExtensionIntoView(extension: IComposableExtension) {
        unloadExtensionFromView()

        _content.value = extension
        extension.onLoad(this)
    }

    override fun unloadExtensionFromView() {
        _content.value?.onUnload(this)
        _content.value = null
    }



    override fun onCreate(context: Keyboard) {
        _extensions = IComposableExtension.getExtensions(context)
    }

    override fun onResume(context: Keyboard, info: EditorInfo?) {}
    override fun onPause(context: Keyboard) {}

    override fun onDestroy(context: Keyboard) {}



    override fun onBufferChange(context: Keyboard, buffer: StringBuffer) {
        _buffer.value = buffer.toString()
    }





    /**
     * Keyboard Extension content
     *
     * This will be null if no extension
     * is loaded into view. If there is
     * an extension loaded into the view
     * then this will return that extension
     */
    val content: IComposableExtension?
        get() = _content.value

    /**
     * Keyboard input buffer
     */
    val buffer: String
        get() = _buffer.value

    /**
     * Keyboard Shift State
     */
    var shift: ShiftState
        get() = _shift.value
        set(value) { _shift.value = value }

    /**
     * Keyboard extensions
     */
    val extensions: HashMap<String, IObject<Extension, IComposableExtension>>
        get() = _extensions
}
