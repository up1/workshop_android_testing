package cc.somkiat.recipes.ui.main;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.List;

import cc.somkiat.recipes.R;
import cc.somkiat.recipes.data.local.CachedRecipe;
import cc.somkiat.recipes.data.model.Recipe;
import cc.somkiat.recipes.data.remote.RecipeApi;
import cc.somkiat.recipes.data.remote.SearchRecipeResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recipes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchData();
    }

    private void searchData() {

        final AlertDialog loadingDialog = createLoadingDialog();
        loadingDialog.show();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://www.recipepuppy.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipeApi api = retrofit.create(RecipeApi.class);
        api.search("rice").enqueue(new Callback<SearchRecipeResponse>() {
            @Override
            public void onResponse(Call<SearchRecipeResponse> call, Response<SearchRecipeResponse> response) {
                loadingDialog.dismiss();
                List<Recipe> allRecipes = response.body().getResults();
                CachedRecipe.setAllRecipes(allRecipes);
                recyclerView.setAdapter(new RecipeAdapter(allRecipes));
            }

            @Override
            public void onFailure(Call<SearchRecipeResponse> call, Throwable t) {
                loadingDialog.dismiss();
                displayError("Error!", "There is some thing wrong!");
            }
        });
    }

    private AlertDialog createLoadingDialog() {
        ProgressBar bar = new ProgressBar(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(bar)
                .create();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return dialog;
    }

    private void displayError(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
}
