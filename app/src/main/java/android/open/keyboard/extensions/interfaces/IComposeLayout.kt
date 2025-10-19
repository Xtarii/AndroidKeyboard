package android.open.keyboard.extensions.interfaces

import android.open.keyboard.Keyboard
import androidx.compose.runtime.Composable

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
