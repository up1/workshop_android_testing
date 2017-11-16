package cc.somkiat.recipes.data.local;


import java.util.ArrayList;
import java.util.List;

import cc.somkiat.recipes.data.model.Recipe;

public class CachedRecipe {

    private static List<Recipe> allRecipes = new ArrayList<>();

    public static void setAllRecipes(List<Recipe> allRecipes) {
        CachedRecipe.allRecipes = allRecipes;
    }

    public static Recipe getByPosition(int position) {
        return allRecipes.get(position);
    }
}
