package android.open.keyboard.defaults

import android.annotation.SuppressLint
import android.open.keyboard.Keyboard
import android.open.keyboard.extensions.annotations.Extension
import android.open.keyboard.extensions.interfaces.IComposeLayout
import android.open.keyboard.extensions.interfaces.IExtension
import android.open.keyboard.extensions.interfaces.IViewManager
import android.open.keyboard.extensions.interfaces.KeyboardContextProvider
import android.open.keyboard.keyboard.AbstractKeyboardService
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

@Extension(ID = "android.open.keyboard.defaults.ViewManager", description = "Simple Compose View Manager")
class ViewManager : IViewManager, LifecycleOwner, SavedStateRegistryOwner {
    /**
     * View Content
     *
     * Content that should be visible in the layout
     */
    private var content: @Composable () -> Unit = {}

    private var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    private val savedStateRegistryController: SavedStateRegistryController =
        SavedStateRegistryController.create(this)
    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry





    override fun onCreateView(context: Keyboard): View? {
        context.window.window?.decorView?.let {
            it.setViewTreeLifecycleOwner(this)
            it.setViewTreeSavedStateRegistryOwner(this)
        }

        val layout: IExtension = context.extensionsManager.layout.instance
        if(layout !is IComposeLayout) {
            Log.e(AbstractKeyboardService.CONSOLE_NAME, "Layout is not a composable layout")
            return null
        }

        return LayoutWrapper(context, {
            KeyboardContextProvider(context) {
                layout.Layout(context, content)
            }
        })
    }

    override fun onCreate(context: Keyboard) {
        savedStateRegistryController.performRestore(null)
        handleLifeEvent(Lifecycle.Event.ON_CREATE)
    }



    override fun onResume(context: Keyboard, info: EditorInfo) {
        handleLifeEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause(context: Keyboard) {
        handleLifeEvent(Lifecycle.Event.ON_PAUSE)
    }



    override fun onDestroy(context: Keyboard) {
        handleLifeEvent(Lifecycle.Event.ON_DESTROY)
    }



    /**
     * Handles Life Cycle Event
     */
    private fun handleLifeEvent(event: Lifecycle.Event) = lifecycleRegistry.handleLifecycleEvent(event)



    /**
     * Wrapper for Composable Layout View
     */
    @SuppressLint("ViewConstructor")
    class LayoutWrapper(context: Keyboard, private val layout: @Composable () -> Unit) : AbstractComposeView(context) {
        @Composable
        override fun Content() { layout() }
    }
}
