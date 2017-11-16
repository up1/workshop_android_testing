package cc.somkiat.recipes.data.remote;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import cc.somkiat.recipes.data.model.Recipe;

public class SearchRecipeResponse {

    @SerializedName("results")
    private List<Recipe> results;

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }
}
