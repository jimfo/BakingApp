package com.jimfo.bakingapp.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.RecipeTask;
import com.jimfo.bakingapp.adapter.RecipeListAdapter;
import com.jimfo.bakingapp.model.Recipe;
import com.jimfo.bakingapp.utils.NetworkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeTask.PostExecuteListener,
        RecipeListAdapter.ItemClickListener {


    // Recipe icon By Shane David Kenna, IE
    private ArrayList<Recipe> mRecipes;
    public RecipeListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.activityBackground));

        TextView emptyTv = findViewById(R.id.empty_view);
        mRecyclerView = findViewById(R.id.recipe_list);

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.activityBackground));

            if (bar != null) {
                bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.activityBackground)));
            }
        }

        if (NetworkUtils.isNetworkAvailable(this.getApplicationContext())) {
            new RecipeTask(this, this).execute();
            mRecyclerView.setVisibility(View.VISIBLE);
            emptyTv.setVisibility(View.GONE);
        } else {
            emptyTv.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClickListener(int itemId) {
        Recipe recipe = mRecipes.get(itemId);

        Intent i = new Intent(this, RecipeDetail.class);
        i.putExtra(getResources().getString(R.string.selected), recipe);
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
