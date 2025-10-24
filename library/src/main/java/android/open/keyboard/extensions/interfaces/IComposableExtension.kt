package android.open.keyboard.extensions.interfaces

import android.open.keyboard.Keyboard
import android.open.keyboard.extensions.annotations.Extension
import android.open.keyboard.extensions.objects.IObject
import androidx.compose.runtime.Composable

/**
 * Composable Extension
 *
 * Extensions that use a composable layout and view
 * should extend this interface.
 *
 * These components are available for the layout
 * to extend into the keyboard layout.
 */
interface IComposableExtension : IExtension {
    /**
     * IComposable Static Members
     */
    companion object Static



    /**
     * Extension Badge
     *
     * Note, this should not be a button for
     * loading this extension as that is done
     * by the keyboard layout.
     */
    @Composable
    fun Badge(): Unit

    /**
     * Extension Content
     *
     * When an extension ( such as this ) gets
     * loaded by the keyboard its composable
     * content gets put into the view managers
     * content handler.
     */
    @Composable
    fun Content(): Unit
}



/**
 * Gets all IComposable Extensions from the extension manager
 *
 * This should only be called once as it can take unwanted time.
 *
 * @param context Keyboard context
 */
fun IComposableExtension.Static.getExtensions(context: Keyboard) : HashMap<String, IObject<Extension, IComposableExtension>> {
    val map: HashMap<String, IObject<Extension, IComposableExtension>> = HashMap()

    context.extensionsManager.extensions.forEach {
        if(it.value.instance is IComposableExtension) map.put(
            it.key,
            IObject(
                it.value.meta,
                it.value.instance as IComposableExtension
            )
        )
    }

    return map
}
