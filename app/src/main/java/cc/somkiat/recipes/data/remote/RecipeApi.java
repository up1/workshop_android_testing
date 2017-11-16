package cc.somkiat.recipes.data.remote;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {

    @GET("/api")
    Call<SearchRecipeResponse> search(@Query("q") String query);

}
