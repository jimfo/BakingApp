package com.jimfo.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.model.Ingredient;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private ArrayList<Ingredient> mIngredients;
    private LayoutInflater mInflater;

    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredients) {

        this.mIngredients = ingredients;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = mInflater.inflate(R.layout.ingredient_card_view, parent, false);
        return new IngredientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int i) {
        holder.ingredientItem.append(mIngredients.get(i).getmQuantity() + " ");
        holder.ingredientItem.append(mIngredients.get(i).getmMeasure() + " ");
        holder.ingredientItem.append(mIngredients.get(i).getmIngredient());
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        private TextView ingredientItem;

        IngredientViewHolder(View itemView) {
            super(itemView);

            ingredientItem = itemView.findViewById(R.id.ingredients_tv);
        }
    }

}
