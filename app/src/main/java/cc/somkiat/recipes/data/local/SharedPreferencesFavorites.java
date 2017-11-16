package cc.somkiat.recipes.data.local;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesFavorites {

    private final SharedPreferences pref;

    public SharedPreferencesFavorites(Context context) {
        pref = context.getSharedPreferences("favorites.xml", Context.MODE_PRIVATE);
    }

    public boolean get(int id) {
        return pref.getBoolean(String.valueOf(id), false);
    }

    public boolean toggle(int id) {
        boolean favorite = get(id);
        put(String.valueOf(id), !favorite);
        return !favorite;
    }

    private void put(String id, boolean favorite) {
        SharedPreferences.Editor editor = pref.edit();
        if (favorite) {
            editor.putBoolean(id, true);
        } else {
            editor.remove(id);
        }
        editor.apply();
    }

}
