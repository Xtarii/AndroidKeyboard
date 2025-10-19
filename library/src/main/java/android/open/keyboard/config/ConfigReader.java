package android.open.keyboard.config;

import android.content.Context;
import android.open.keyboard.keyboard.AbstractKeyboardService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Keyboard Configuration Reader
 * <p/>
 * Reader for reading <code>JSON</code> files
 * with settings and config for the keyboard
 * system.
 */
public class ConfigReader {
    /**
     * Reads Config file as to string
     *
     * @param context Android Context
     * @param file File Name
     * @return File content
     * @throws IOException This gets thrown if there was an error loading
     * and reading the file content.
     */
    public String ReadConfig(Context context, String file) throws IOException {
        String data;
        try(InputStream in = context.getAssets().open(AbstractKeyboardService.PATH + file)) {
            int size = in.available();
            byte[] buffer = new byte[size];
            int r = in.read(buffer);
            data = new String(buffer, StandardCharsets.UTF_8);
        }
        return data;
    }



    /**
     * Parses JSON String into JSON Object
     * @param json JSON Content in string format
     * @return JSON Object
     * @throws JSONException This gets thrown if there was an error
     * reading the json string content.
     */
    public JSONObject parseJSON(String json) throws JSONException {
        return new JSONObject(json);
    }
}
