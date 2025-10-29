package android.open.keyboard;

import android.open.keyboard.keyboard.AbstractKeyboardService;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;

/**
 * Android Open Source Keyboard
 */
public class Keyboard extends AbstractKeyboardService {
    @Override
    public View onCreateInputView() {
        return getViewManager().onCreateView(this);
    }



    @Override
    public void onCreate() {
        super.onCreate();

        getExtensionsManager().getExtensions().forEach((key, value) -> {
            try {
                value.instance.onCreate(this);
            } catch (RuntimeException e) {
                Log.e(CONSOLE_NAME,
                        (e.getMessage() != null) ? e.getMessage()
                                : "Unspecified Runtime error occurred"
                );
            } catch (Exception e) {
                Log.e(CONSOLE_NAME, e.getMessage());
            }
        });
    }



    @Override
    public void onStartInputView(EditorInfo editorInfo, boolean restarting) {
        super.onStartInputView(editorInfo, restarting);

        getExtensionsManager().getExtensions().forEach((key, value) -> {
            try {
                value.instance.onResume(this, editorInfo);
            } catch (RuntimeException e) {
                Log.e(CONSOLE_NAME,
                        (e.getMessage() != null) ? e.getMessage()
                                : "Unspecified Runtime error occurred"
                );
            } catch (Exception e) {
                Log.e(CONSOLE_NAME, e.getMessage());
            }
        });
    }

    @Override
    public void onFinishInputView(boolean finishingInput) {
        super.onFinishInputView(finishingInput);

        getExtensionsManager().getExtensions().forEach((key, value) -> {
            try {
                value.instance.onPause(this);
            } catch (RuntimeException e) {
                Log.e(CONSOLE_NAME,
                        (e.getMessage() != null) ? e.getMessage()
                                : "Unspecified Runtime error occurred"
                );
            } catch (Exception e) {
                Log.e(CONSOLE_NAME, e.getMessage());
            }
        });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        getExtensionsManager().getExtensions().forEach((key, value) -> {
            try {
                value.instance.onDestroy(this);
            } catch (RuntimeException e) {
                Log.e(CONSOLE_NAME,
                        (e.getMessage() != null) ? e.getMessage()
                                : "Unspecified Runtime error occurred"
                );
            } catch (Exception e) {
                Log.e(CONSOLE_NAME, e.getMessage());
            }
        });
    }



    @Override
    public void onBufferChange() {
        getExtensionsManager().getExtensions().forEach((key, value) -> {
            try {
                value.instance.onBufferChange(this, getBuffer());
            } catch (RuntimeException e) {
                Log.e(CONSOLE_NAME,
                        (e.getMessage() != null) ? e.getMessage()
                                : "Unspecified Runtime error occurred"
                );
            } catch (Exception e) {
                Log.e(CONSOLE_NAME, e.getMessage());
            }
        });
    }
}
