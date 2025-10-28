package android.open.keyboard.keyboard;

import android.inputmethodservice.InputMethodService;
import android.open.keyboard.config.ConfigReader;
import android.open.keyboard.config.defaults.KeyboardSettings;
import android.open.keyboard.extensions.ExtensionsManager;
import android.open.keyboard.extensions.interfaces.IExtension;
import android.open.keyboard.extensions.interfaces.IViewManager;
import android.os.Build;
import android.util.Log;

import java.util.Objects;

/**
 * Abstract Keyboard Service
 * <p/>
 * This works like a bridge or wrapper for the
 * <code>{@link android.open.keyboard.Keyboard}</code> and
 * the <code>{@link InputMethodService}</code>.
 * It holds data and attributes used by the <code>keyboard</code>
 * while also implementing the <code>service</code> needed to
 * run the keyboard.
 */
public abstract class AbstractKeyboardService extends InputMethodService {
    /**
     * Assets Folder Location for keyboard related
     * files and objects.
     */
    public static final String PATH = "keyboard/";

    /**
     * Console DEBUG Name
     */
    public static final String CONSOLE_NAME = "Keyboard";



    /**
     * Keyboard Configuration Reader
     */
    private ConfigReader reader = null;

    /**
     * Keyboard Input Stream Buffer
     */
    private StringBuffer buffer = null;

    /**
     * Keyboard Extension Manager
     */
    private ExtensionsManager extensionsManager = null;

    /**
     * Keyboard Settings
     */
    private KeyboardSettings settings;



    @Override
    public void onCreate() {
        super.onCreate();

        reader = new ConfigReader();
        settings = new KeyboardSettings(this, reader);

        try { extensionsManager = new ExtensionsManager(this); }
        catch(IllegalStateException e) {
            Log.e(CONSOLE_NAME, (e.getMessage() != null) ? e.getMessage()
                    : "Unmarked Error occurred"
            );
        }

        buffer = new StringBuffer();
    }



    /**
     * Puts a string into the input stream and
     * moves the cursor to the end of the string.
     *
     * @param str String to push to the input stream
     */
    public void putText(String str) {
        getCurrentInputConnection().commitText(str, 1);
        buffer.append(str);
    }



    /**
     * Erases text from input stream. If any text is selected it will
     * be removed otherwise the first character behind the cursor
     * will be removed.
     */
    public void erase() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            if(getCurrentInputConnection().getSelectedText(0) != null)
                getCurrentInputConnection().commitText("", 1);
            else getCurrentInputConnection().deleteSurroundingText(1, 0);
        }
        if(buffer.length() > 0) buffer.deleteCharAt(buffer.length() - 1);
        onBufferChange();
    }



    /**
     * Keyboard Buffer Change Event
     */
    protected abstract void onBufferChange();



    /**
     * Keyboard Settings Object
     */
    public KeyboardSettings getSettings() {
        return settings;
    }

    /**
     * Keyboard Extension Manager
     */
    public ExtensionsManager getExtensionsManager() {
        return extensionsManager;
    }


    /**
     * Keyboard Layout Extension
     */
    public IExtension getLayout() {
        return extensionsManager.getLayout().instance;
    }

    /**
     * Keyboard View Manager Extension
     */
    public IViewManager getViewManager() {
        return extensionsManager.getViewManager().instance;
    }

    /**
     * Keyboard Config Reader
     */
    public ConfigReader getReader() {
        return reader;
    }

    /**
     * Keyboard Input Stream Buffer
     */
    public StringBuffer getBuffer() { return buffer; }
}
