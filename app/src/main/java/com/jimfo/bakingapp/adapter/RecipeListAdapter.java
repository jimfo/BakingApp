package com.jimfo.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private final List<Recipe> mRecipes;
    private ItemClickListener mListener;
    private LayoutInflater mInflater;

    public RecipeListAdapter(Context context, ItemClickListener listener, ArrayList<Recipe> recipes) {
        this.mRecipes = recipes;
        this.mListener = listener;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = mInflater.inflate(R.layout.activity_main_card, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        recipeViewHolder.recipeTitle.setText(mRecipes.get(i).getmName());
        recipeViewHolder.servingSize.append(mRecipes.get(i).getmServings());
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView recipeTitle;
        private TextView servingSize;

        RecipeViewHolder(View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipe_title_tv);
            servingSize = itemView.findViewById(R.id.serving_size_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mListener.onItemClickListener(position);
        }
    }
}
