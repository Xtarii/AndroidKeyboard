package android.open.keyboard;

import android.open.keyboard.keyboard.AbstractKeyboardService;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import java.util.Objects;

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

        try {
            getViewManager().onCreate(this);
            getLayout().onCreate(this);

            getExtensionsManager().getExtensions().forEach((key, value) ->
                    value.instance.onCreate(this)
            );
        }catch (RuntimeException e) {
            Log.e(CONSOLE_NAME,
                (e.getMessage() != null) ? e.getMessage()
                        : "Unspecified Runtime error occurred"
            );
        } catch (Exception e) {
            Log.e(CONSOLE_NAME, e.getMessage());
        }
    }



    @Override
    public void onStartInputView(EditorInfo editorInfo, boolean restarting) {
        super.onStartInputView(editorInfo, restarting);

        try {
            getViewManager().onResume(this, editorInfo);
            getLayout().onResume(this, editorInfo);

            getExtensionsManager().getExtensions().forEach((key, value) ->
                    value.instance.onResume(this, editorInfo)
            );
        }catch (RuntimeException e) {
            Log.e(CONSOLE_NAME,
                    (e.getMessage() != null) ? e.getMessage()
                            : "Unspecified Runtime error occurred"
            );
        } catch (Exception e) {
            Log.e(CONSOLE_NAME, e.getMessage());
        }
    }

    @Override
    public void onFinishInputView(boolean finishingInput) {
        super.onFinishInputView(finishingInput);

        try {
            getViewManager().onPause(this);
            getLayout().onPause(this);

            getExtensionsManager().getExtensions().forEach((key, value) ->
                    value.instance.onPause(this)
            );
        }catch (RuntimeException e) {
            Log.e(CONSOLE_NAME,
                    (e.getMessage() != null) ? e.getMessage()
                            : "Unspecified Runtime error occurred"
            );
        } catch (Exception e) {
            Log.e(CONSOLE_NAME, e.getMessage());
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        try {
            getViewManager().onDestroy(this);
            getLayout().onDestroy(this);

            getExtensionsManager().getExtensions().forEach((key, value) ->
                    value.instance.onDestroy(this)
            );
        }catch (RuntimeException e) {
            Log.e(CONSOLE_NAME,
                    (e.getMessage() != null) ? e.getMessage()
                            : "Unspecified Runtime error occurred"
            );
        } catch (Exception e) {
            Log.e(CONSOLE_NAME, e.getMessage());
        }
    }



    @Override
    public void onBufferChange() {
        try {
            getViewManager().onBufferChange(this, getBuffer());
            getLayout().onBufferChange(this, getBuffer());

            getExtensionsManager().getExtensions().forEach((key, value) ->
                    value.instance.onBufferChange(this, getBuffer())
            );
        }catch (RuntimeException e) {
            Log.e(CONSOLE_NAME,
                    (e.getMessage() != null) ? e.getMessage()
                            : "Unspecified Runtime error occurred"
            );
        } catch (Exception e) {
            Log.e(CONSOLE_NAME, e.getMessage());
        }
    }
}
