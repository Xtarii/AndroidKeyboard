package android.open.keyboard.extensions.interfaces;

import android.open.keyboard.Keyboard;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;

/**
 * Keyboard Extension Interface
 */
public interface IExtension {
    /**
     * On Create Event Method
     * <p/>
     * This is called when the keyboard is
     * created by the system.
     *
     * @param context Keyboard Context
     */
    void onCreate(@NonNull Keyboard context);

    /**
     * On Resume Event Method
     * <p/>
     * This is called when the keyboard activity
     * is resumed.
     *
     * @param context Keyboard Context
     * @param info Editor info
     */
    void onResume(@NonNull Keyboard context, EditorInfo info);

    /**
     * On Pause Event Method
     * <p/>
     * This is called when the keyboard activity
     * is paused.
     *
     * @param context Keyboard Context
     */
    void onPause(@NonNull Keyboard context);

    /**
     * On Destroy Event Method
     * <p/>
     * This is called when the keyboard is
     * being cleaned up and destroyed by
     * the system.
     *
     * @param context Keyboard Context
     */
    void onDestroy(@NonNull Keyboard context);



    /**
     * On Buffer Change Event Method
     * <p/>
     * This is called every time the buffer content changes.
     *
     * @param context Keyboard Context
     * @param buffer Keyboard Input Buffer
     */
    void onBufferChange(@NonNull Keyboard context, @NonNull StringBuffer buffer);
}
