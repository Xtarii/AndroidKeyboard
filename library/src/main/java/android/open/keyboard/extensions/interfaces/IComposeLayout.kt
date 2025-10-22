package android.open.keyboard.extensions.interfaces

import android.open.keyboard.Keyboard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * Local Keyboard Context
 */
private val LocalContext = compositionLocalOf<Keyboard> { error("No keyboard context available in this context") }



/**
 * Keyboard Context Provider for Composable Layouts
 *
 * @param context Keyboard context
 * @param content Layout that should use this context
 */
@Composable
fun KeyboardContextProvider(context: Keyboard, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalContext provides context) { content() }
}



/**
 * Keyboard Context
 */
val keyboardContext: Keyboard
    @Composable
    get() = LocalContext.current





/**
 * Keyboard Composable Layout Extension
 *
 * Interface for making keyboard layouts with
 * `Jetbrains Compose`.
 */
interface IComposeLayout : IExtension {
    /**
     * Compose Layout
     *
     * This should be the main layout of the keyboard.
     * If there are any child content loaded in the
     * keyboard then this should implement that content.
     *
     * @param context Keyboard context
     * @param content Layout child Content
     */
    @Composable
    fun Layout(context: Keyboard, content: @Composable () -> Unit)
}
