package android.open.keyboard.config.defaults;

import android.content.Context;
import android.open.keyboard.config.ConfigReader;
import android.open.keyboard.keyboard.AbstractKeyboardService;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Keyboard Settings Object
 */
public class KeyboardSettings {
    /**
     * Default Layout Extension
     * <p/>
     * If there is no layout file in the config file linked
     * then this will be used for layout.
     */
    public static final String LAYOUT = "android.open.keyboard.defaults.KeyboardLayout";

    /**
     * Default View Manager Extension
     * <p/>
     * If there is no view manager file in the config file linked
     * then this will be used for view manager.
     */
    public static final String VIEW = "android.open.keyboard.defaults.ViewManager";

    /**
     * Config File Name
     */
    public static final String FILE = "keyboard.settings.json";

    /**
     * Raw Data
     */
    private JSONObject raw;



    /**
     * Creates Keyboard Settings Object
     * <p/>
     * Creates settings from the <code>keyboard.settings.json</code> file.
     *
     * @param context Keyboard context
     * @param reader Configuration Reader
     */
    public KeyboardSettings(Context context, ConfigReader reader) {
        String content = null;
        try { content = reader.ReadConfig(context, FILE); }
        catch (IOException e) { Log.e(AbstractKeyboardService.CONSOLE_NAME,
                (e.getMessage() != null) ? e.getMessage()
                        : "Unmarked error occurred");
        }

        try { raw = reader.parseJSON(content != null ? content : "{}"); }
        catch (JSONException e) { raw = new JSONObject(); }
    }



    /**
     * Gets extension list from Settings Config
     *
     * @return Extensions List
     * @throws JSONException This gets thrown if there was a problem
     * when reading the JSON objects.
     */
    public List<String> getExtensions() throws JSONException {
        List<String> list = new ArrayList<>();
        JSONArray array = raw.getJSONArray("extensions");
        for(int i = 0; i < array.length(); i++) list.add(array.getString(i));
        return list;
    }



    /**
     * Settings Layout Extension or default fallback extension if
     * there was no layout specified. See {@link #LAYOUT}
     */
    public String getLayout() {
        return getStringValue("layout", LAYOUT);
    }

    /**
     * Settings View Manager Extension or default fallback extension if
     * there was no view manager specified. See {@link #VIEW}
     */
    public String getView() {
        return getStringValue("view", VIEW);
    }



    /**
     * Gets String value from settings
     *
     * @param name Settings name
     * @param fallback Fallback value
     * @return Settings value or if empty the fallback
     */
    public String getStringValue(String name, String fallback) {
        String value;
        try{
            value = raw.getString(name);
            if(value.isEmpty()) value = fallback;
        } catch (JSONException e) {
            value = fallback;
        }
        return value;
    }
}
