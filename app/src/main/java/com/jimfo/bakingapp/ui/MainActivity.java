package com.jimfo.bakingapp.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.RecipeTask;
import com.jimfo.bakingapp.SharedPreference;
import com.jimfo.bakingapp.adapter.DetailPagerAdapter;
import com.jimfo.bakingapp.adapter.RecipeListAdapter;
import com.jimfo.bakingapp.adapter.StepAdapter;
import com.jimfo.bakingapp.fragment.IngredientFragment;
import com.jimfo.bakingapp.fragment.StepFragment;
import com.jimfo.bakingapp.model.Recipe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeTask.PostExecuteListener,
        RecipeListAdapter.ItemClickListener, TabLayout.OnTabSelectedListener, StepAdapter.ItemClickListener {


    // Recipe icon By Shane David Kenna, IE
    private ArrayList<Recipe> mRecipes;
    public RecipeListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private DetailPagerAdapter mPagerAdapter;
    public Recipe mRecipe = null;
    private String mTitle;
    private ViewPager viewPager;
    private RecyclerView irecyclerView;
    private RecyclerView srecyclerView;
    private IngredientFragment iFragment = new IngredientFragment();
    private StepFragment sFragment = new StepFragment();
    private TabLayout tabLayout;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.activityBackground));

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.activityBackground));

            if (bar != null) {
                bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.activityBackground)));
            }
        }

        if (findViewById(R.id.baking_app_ll) != null) {
            mTwoPane = true;
            viewPager = findViewById(R.id.viewpager);
            tabLayout = findViewById(R.id.sliding_tabs);
            irecyclerView = findViewById(R.id.ingredient_rv);
            srecyclerView = findViewById(R.id.step_rv);
        } else {
            mTwoPane = false;
        }

        mRecyclerView = findViewById(R.id.recipe_list);
        new RecipeTask(this, this).execute();
    }

    private void addPages(Recipe recipe, String title) {
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        mPagerAdapter = new DetailPagerAdapter(this.getSupportFragmentManager(), recipe, title);
        mPagerAdapter.addFragment(new IngredientFragment());
        mPagerAdapter.addFragment(new StepFragment());
        viewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onItemClickListener(int itemId) {

        Recipe recipe = mRecipes.get(itemId);

        if (mTwoPane) {
            mRecipe = recipe;
            mTitle = mRecipe.getmName();
            setTitle(mTitle);
            this.addPages(mRecipe, mTitle);

            //            mPagerAdapter = new DetailPagerAdapter(this.getSupportFragmentManager(), recipe, mTitle);
            //            mPagerAdapter.addFragment(new IngredientFragment());
            //            mPagerAdapter.addFragment(new StepFragment());
            //            mPagerAdapter.notifyDataSetChanged();
            //            viewPager.setAdapter(mPagerAdapter);
            //
            //            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            //            mRecyclerView.setAdapter(mAdapter);
            //            this.addPages(mRecipe, mTitle);
            //            mPagerAdapter.notifyDataSetChanged();
            //            saveToSharedPreferences();

            //            irecyclerView.setLayoutManager(new LinearLayoutManager(this));
            //            irecyclerView.setAdapter(new IngredientAdapter(this, new ArrayList<>(mRecipe.getmIngredients())));
            //            srecyclerView.setLayoutManager(new LinearLayoutManager(this));
            //            srecyclerView.setAdapter(new StepAdapter(this, this, new ArrayList<>(mRecipe.getmSteps())));
            //            mPagerAdapter.notifyDataSetChanged();
            //            Bundle iArgs = new Bundle();
            //            Bundle sArgs = new Bundle();
            //
            //            IngredientFragment ingFrag = new IngredientFragment();
            //            StepFragment stepFrag = new StepFragment();
            //
            //            iArgs.putParcelableArrayList("ingredients",  new IngredientFragment().newInstance(new ArrayList<>(mRecipe.getmIngredients())))
            //            sArgs.putParcelableArrayList("steps", new ArrayList<>(mRecipe.getmSteps()));
            //
            //            ingFrag.setArguments(iArgs);
            //            stepFrag.setArguments(sArgs);
            //
            //            mPagerAdapter = new DetailPagerAdapter(this.getSupportFragmentManager(), mRecipe, mTitle);
            //            mPagerAdapter.addFragment( new IngredientFragment().newInstance(new ArrayList<>(mRecipe.getmIngredients())));
            //            mPagerAdapter.notifyDataSetChanged();
            //            mPagerAdapter.addFragment( new StepFragment().newInstance(new ArrayList<>(mRecipe.getmSteps()), mTitle));
            //            mPagerAdapter.notifyDataSetChanged();
            //            viewPager.setAdapter(mPagerAdapter);

            //            getSupportFragmentManager().beginTransaction()
            //                    .replace(R.id.ingredient_rv, new IngredientFragment().newInstance(new ArrayList<>(mRecipe.getmIngredients())))
            //                    .commit();
            //
            //            getSupportFragmentManager().beginTransaction()
            //                    .replace(R.id.step_rv, new StepFragment().newInstance(new ArrayList<>(mRecipe.getmSteps()), mTitle))
            //                    .commit();
        } else {
            Intent i = new Intent(this, RecipeDetail.class);
            i.putExtra(getResources().getString(R.string.selected), recipe);
            startActivity(i);
        }
    }

    @Override
    public void onPostExecute(ArrayList<Recipe> recipes) {
        mRecipes = recipes;
        mAdapter = new RecipeListAdapter(this, this, recipes);

        mRecipe = mRecipes.get(0);
        mTitle = mRecipe.getmName();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        this.addPages(mRecipe, mTitle);
        //        saveToSharedPreferences();
    }

    private void saveToSharedPreferences() {

        SharedPreference sharedPreference = new SharedPreference();
        sharedPreference.saveIngredients(this, new ArrayList<>(mRecipes.get(0).getmIngredients()));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
