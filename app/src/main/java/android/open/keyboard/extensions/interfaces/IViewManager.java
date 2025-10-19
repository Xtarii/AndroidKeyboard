package android.open.keyboard.extensions.interfaces;

import android.open.keyboard.Keyboard;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.compose.runtime.Composable;

import kotlin.Unit;

/**
 * Keyboard view manager interface
 * <p/>
 * A extended <code>{@link IExtension}</code> interface for
 * the keyboard view manager.
 * Note, only one view manager should be present in the keyboard.
 */
public interface IViewManager extends IExtension {
    /**
     * On Create View Event Method
     *
     * @param context Keyboard Context
     * @return Keyboard view
     */
    View onCreateView(@NonNull Keyboard context);
}
