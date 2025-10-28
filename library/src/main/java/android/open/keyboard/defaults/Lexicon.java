package android.open.keyboard.defaults;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.open.keyboard.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Keyboard Local Lexicon
 */
public class Lexicon {
    /**
     * Max size of the word match list
     */
    public static final int MAX_SIZE = 3;

    /**
     * Lexicon Path
     */
    public static final String PATH = "lexicon";



    /**
     * Lexicon Data
     */
    private final List<String> data;

    /**
     * Lexicon Shared Preferences
     */
    private final SharedPreferences preferences;



    /**
     * Creates Keyboard Lexicon
     *
     * @param context Keyboard context
     */
    public Lexicon(Keyboard context) {
        data = new ArrayList<>();

        preferences = context.getSharedPreferences(PATH, MODE_PRIVATE);
        Map<String, ?> map = preferences.getAll();
        map.forEach((key, value) -> data.add(key));
    }



    /**
     * Gets matching strings from lexicon
     *
     * @param str String to search for
     * @param max Max amount of words to find
     * @return Strings that matched with <code>str</code>
     */
    public List<String> getMatches(String str, int max) {
        List<String> matches = new ArrayList<>();

        int i = 0;
        while(i < max && i < data.size()) {
            String c = data.get(i);
            if(c.startsWith(str)) matches.add(c);
        }

        return matches;
    }



    /**
     * Gets matching strings from lexicon
     *
     * @param str String to search for
     * @return Strings that matched with <code>str</code>
     */
    public List<String> getMatches(String str) {
        return getMatches(str, MAX_SIZE);
    }



    /**
     * Adds string to lexicon
     *
     * @param str String to add
     */
    public void add(String str) {
        data.add(str);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(str.toLowerCase(), "word");
        editor.apply();
    }
}
