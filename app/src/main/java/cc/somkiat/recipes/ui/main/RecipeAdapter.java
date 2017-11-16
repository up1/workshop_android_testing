package cc.somkiat.recipes.ui.main;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cc.somkiat.recipes.R;
import cc.somkiat.recipes.data.model.Recipe;
import cc.somkiat.recipes.ui.recipedetail.RecipeDetailActivity;

class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private final List<Recipe> allRecipes;

    public RecipeAdapter(List<Recipe> allRecipes) {
        this.allRecipes = allRecipes;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false
        );
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, final int position) {
        final Recipe recipe = allRecipes.get(position);
        holder.textView.setText(recipe.getTitle());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.textView.getContext();
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra("id", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allRecipes.size();
    }
}