package com.jimfo.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jimfo.bakingapp.model.Ingredient;
import com.jimfo.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private Recipe recipe;
    private TextView ingredientTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        ingredientTv = findViewById(R.id.ingredient_tv);

        Intent i = getIntent();
        Bundle mExtras = i.getExtras();

        if (mExtras != null && mExtras.containsKey(this.getResources().getString(R.string.selected))) {
            recipe = mExtras.getParcelable(this.getResources().getString(R.string.selected));
        }

        if(recipe != null) {
            setTitle(recipe.getmName());
            populateIngredientTv(recipe.getmIngredients());
        }
    }

    public void populateIngredientTv(List<Ingredient> ingredientList){

        for(int i = 0; i < ingredientList.size(); i++){
            ingredientTv.append(ingredientList.get(i).getmIngredient() + " : ");
            ingredientTv.append(ingredientList.get(i).getmQuantity() + " ");
            ingredientTv.append(ingredientList.get(i).getmMeasure() + "\n");

        }
    }

    public void onButtonClick(View v){
        int id = v.getId();

        switch(id){
            case R.id.ingredient_btn:

                if (ingredientTv.getVisibility() ==  View.GONE){
                    ingredientTv.setVisibility(View.VISIBLE);

                }
                else{
                    ingredientTv.setVisibility(View.GONE);
                }

                break;

             default:
                 break;
        }
    }
}
