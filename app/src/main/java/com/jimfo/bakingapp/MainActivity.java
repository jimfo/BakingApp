package com.jimfo.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jimfo.bakingapp.model.Recipe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeTask.PostExecuteListener,
        RecipeListAdapter.ItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Recipe> mRecipes;
    public RecipeListAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recipe_list);
        new RecipeTask(this, this).execute();
    }

    @Override
    public void onItemClickListener(int itemId) {
        Recipe recipe = mRecipes.get(itemId);

        String selected = getResources().getString(R.string.selected);

        Intent i = new Intent(this, RecipeActivity.class);
        i.putExtra(selected, recipe);
        startActivity(i);

    }

    @Override
    public void onPostExecute(ArrayList<Recipe> recipes) {

        mRecipes = recipes;
        mAdapter = new RecipeListAdapter(this, this, recipes);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
