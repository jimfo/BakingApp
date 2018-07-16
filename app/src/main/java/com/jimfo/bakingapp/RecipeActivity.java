package com.jimfo.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jimfo.bakingapp.model.Recipe;

public class RecipeActivity extends AppCompatActivity {

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent i = getIntent();
        Bundle mExtras = i.getExtras();

        if (mExtras != null && mExtras.containsKey(this.getResources().getString(R.string.selected))) {
            recipe = mExtras.getParcelable(this.getResources().getString(R.string.selected));
        }
    }
}
