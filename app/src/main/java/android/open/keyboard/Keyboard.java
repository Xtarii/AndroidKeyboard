package android.open.keyboard;

import android.open.keyboard.keyboard.AbstractKeyboardService;
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

        getViewManager().onCreate(this);
        getLayout().onCreate(this);

        getExtensionsManager().getExtensions().forEach((key, value) ->
                value.instance.onCreate(this)
        );
    }



    @Override
    public void onStartInputView(EditorInfo editorInfo, boolean restarting) {
        super.onStartInputView(editorInfo, restarting);

        getViewManager().onResume(this, editorInfo);
        getLayout().onResume(this, editorInfo);

        getExtensionsManager().getExtensions().forEach((key, value) ->
            value.instance.onResume(this, editorInfo)
        );
    }

    @Override
    public void onFinishInputView(boolean finishingInput) {
        super.onFinishInputView(finishingInput);

        getViewManager().onPause(this);
        getLayout().onPause(this);

        getExtensionsManager().getExtensions().forEach((key, value) ->
            value.instance.onPause(this)
        );
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        getViewManager().onDestroy(this);
        getLayout().onDestroy(this);

        getExtensionsManager().getExtensions().forEach((key, value) ->
            value.instance.onDestroy(this)
        );
    }
}
