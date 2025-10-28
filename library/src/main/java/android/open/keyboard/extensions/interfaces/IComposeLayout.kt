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
 * Local Keyboard Layout Context
 */
private val LocalLayout = compositionLocalOf<IComposeLayout> { error("No compose layout available in this context") }



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
 * Keyboard Layout Context Provider for Composable Layouts
 *
 * @param layout Keyboard Layout
 * @param content Content that should use this context
 */
@Composable
fun LayoutContextProvider(layout: IComposeLayout, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLayout provides layout) { content() }
}



/**
 * Keyboard Context
 */
val keyboardContext: Keyboard
    @Composable
    get() = LocalContext.current

/**
 * Keyboard Layout
 */
val layoutContext: IComposeLayout
    @Composable
    get() = LocalLayout.current





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
     */
    @Composable
    fun Layout(context: Keyboard)



    /**
     * Loads Content into Keyboard Layout
     *
     * This will load composable content into the
     * keyboard layout view.
     *
     * @param extension Content to load into the layout
     */
    fun loadExtensionIntoView(extension: IComposableExtension)

    /**
     * Unloads content from layout view
     */
    fun unloadExtensionFromView()
}
