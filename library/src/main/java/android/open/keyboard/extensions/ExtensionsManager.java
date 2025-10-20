package android.open.keyboard.extensions;

import android.open.keyboard.config.defaults.KeyboardSettings;
import android.open.keyboard.extensions.interfaces.IViewManager;
import android.open.keyboard.keyboard.AbstractKeyboardService;
import android.open.keyboard.extensions.annotations.Extension;
import android.open.keyboard.extensions.interfaces.IExtension;
import android.open.keyboard.extensions.objects.IObject;
import android.util.Log;

import org.json.JSONException;

import java.lang.annotation.AnnotationFormatError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Keyboard Annotation Extensions Manager
 * <p/>
 * Manages extension at keyboard <code>load time</code>
 */
public class ExtensionsManager {
    /**
     * Keyboard Context
     */
    private final AbstractKeyboardService context;

    /**
     * Keyboard View Manager Extension
     */
    private final IObject<Extension, IViewManager> viewManager;

    /**
     * Keyboard Layout
     */
    private final IObject<Extension, IExtension> layout;

    /**
     * Keyboard Extensions Map
     */
    private HashMap<String, IObject<Extension, IExtension>> extensions;



    /**
     * Creates Extensions Manager Instance
     *
     * @param context Keyboard Context
     * @throws IllegalStateException This gets thrown if there was an error
     * loading the keyboard layout extension.
     */
    public ExtensionsManager(AbstractKeyboardService context) throws IllegalStateException {
        this.context = context;
        KeyboardSettings settings = context.getSettings();

        var vm = loadExtension(settings != null ? settings.getView() : null);
        if(!(vm.instance instanceof IViewManager)) throw new IllegalStateException("Can not create keyboard without view manager");
        viewManager = new IObject<>(vm.meta, (IViewManager) vm.instance);

        layout = loadExtension(settings != null ? settings.getLayout() : null);
        if(layout == null) throw new IllegalStateException("Can not create keyboard without layout");

        extensions = new HashMap<>(); // Empty value in case it fails to load extensions
        try {
            extensions = loadExtensions(settings != null ? settings.getExtensions() : new ArrayList<>());
        } catch (JSONException e) {
            Log.d(AbstractKeyboardService.CONSOLE_NAME, "No extensions loaded");
        }
    }



    /**
     * Loads Valid Keyboard Extensions from List of classes
     *
     * @param extensions List of extension classes
     * @return Map of valid extension instances
     */
    public HashMap<String, IObject<Extension, IExtension>> loadExtensions(List<String> extensions) {
        HashMap<String, IObject<Extension, IExtension>> map = new HashMap<>();
        for(String e : extensions) {
            try {
                Class<?> ec = getExtensionByName(e);
                Extension data = getExtensionAnnotation(ec);

                Object inst = ec.newInstance();
                if(inst instanceof IExtension) {
                    IObject<Extension, IExtension> obj = new IObject<>(data, (IExtension) inst);
                    map.put(data.ID(), obj);

                    Log.d(AbstractKeyboardService.CONSOLE_NAME, String.format(
                            "Loaded extension: { id: %s, description: %s }", data.ID(), data.description())
                    );
                }

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | AnnotationFormatError ex) {
                Log.e(AbstractKeyboardService.CONSOLE_NAME, Objects.requireNonNull(ex.getMessage()));
            }
        }
        return map;
    }



    /**
     * Loads Single Extension class as Object
     *
     * @param extension Extension class name
     * @return Extension Data and Object Instance
     */
    public IObject<Extension, IExtension> loadExtension(String extension) {
        if(extension == null) return null;

        try {
            Class<?> ec = getExtensionByName(extension);
            Extension data = getExtensionAnnotation(ec);

            Object inst = ec.newInstance();
            if(inst instanceof IExtension) return new IObject<>(data, (IExtension) inst);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | AnnotationFormatError e) {
            Log.e(AbstractKeyboardService.CONSOLE_NAME, Objects.requireNonNull(e.getMessage()));
        }

        return null;
    }



    /**
     * Gets Extension Annotation
     *
     * @param c Class Representation Object
     * @return Annotation Class, or null if not found
     * @throws AnnotationFormatError This gets thrown if the annotation was not found
     * on the class object
     */
    private Extension getExtensionAnnotation(Class<?> c) throws AnnotationFormatError {
        Extension annotation = c.getAnnotation(Extension.class);
        if(annotation == null) throw new AnnotationFormatError(
                String.format("Annotation not found on class [ %s ]", c.getName())
        );
        return annotation;
    }



    /**
     * Gets Extension by class name and path
     *
     * @param extension Extension class path
     * @return Extension class representation
     * @throws ClassNotFoundException If the class was not found
     */
    private Class<?> getExtensionByName(String extension) throws ClassNotFoundException {
        return context.getClassLoader().loadClass(extension);
    }



    /**
     * Get a map of loaded keyboard extensions
     */
    public HashMap<String, IObject<Extension, IExtension>> getExtensions() {
        return extensions;
    }

    /**
     * Gets Extension by ID
     *
     * @param ID Extension ID
     * @return Extension that matched the specified ID
     */
    public IObject<Extension, IExtension> getExtensionByID(String ID) {
        return extensions.get(ID);
    }

    /**
     * Gets keyboard layout extension
     */
    public IObject<Extension, IExtension> getLayout() {
        return layout;
    }

    /**
     * Gets keyboard view manager extension
     */
    public IObject<Extension, IViewManager> getViewManager() {
        return viewManager;
    }
}
