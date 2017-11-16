package cc.somkiat.recipes.ui.recipedetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cc.somkiat.recipes.R;
import cc.somkiat.recipes.data.local.CachedRecipe;
import cc.somkiat.recipes.data.local.SharedPreferencesFavorites;
import cc.somkiat.recipes.data.model.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String KEY_ID = "id";

    private TextView titleTextView;
    private TextView descriptionTextView;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);

        id = getIntent().getIntExtra(KEY_ID, 0);
        final Recipe recipe = CachedRecipe.getByPosition(id);

        if (recipe == null) {
            titleTextView.setVisibility(View.GONE);
            descriptionTextView.setText(R.string.recipe_not_found);
            return;
        }



        final SharedPreferencesFavorites favorites = new SharedPreferencesFavorites(this);
        boolean favorite = favorites.get(id);

        titleTextView.setText(recipe.getTitle());
        titleTextView.setSelected(favorite);
        descriptionTextView.setText(recipe.getTitle());

        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = favorites.toggle(id);
                titleTextView.setSelected(result);
            }
        });
    }
}
