package com.jimfo.bakingapp.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.adapter.DetailPagerAdapter;
import com.jimfo.bakingapp.fragment.IngredientFragment;
import com.jimfo.bakingapp.fragment.StepFragment;
import com.jimfo.bakingapp.model.Ingredient;
import com.jimfo.bakingapp.model.Recipe;
import com.jimfo.bakingapp.model.Step;

import java.util.ArrayList;

public class RecipeDetail extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Ingredient> mIngredients;
    private ArrayList<Step> mSteps;
    private IngredientFragment iFragment = new IngredientFragment();
    private StepFragment sFragment = new StepFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ActionBar bar = getSupportActionBar();

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.activityBackground));

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.activityBackground));

            if (bar != null) {
                bar.setDisplayHomeAsUpEnabled(true);
                bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.activityBackground)));
            }
        }

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        if (extras != null && extras.containsKey(this.getResources().getString(R.string.selected))) {
            Recipe recipe = extras.getParcelable(this.getResources().getString(R.string.selected));

            if (recipe != null) {
                mIngredients = new ArrayList<>(recipe.getmIngredients());
                mSteps = new ArrayList<>(recipe.getmSteps());
                setTitle(recipe.getmName());
                setTitleColor(getResources().getColor(R.color.textColor));
            }
        }

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.sliding_tabs);
        this.addPages();
    }

    private void addPages() {

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        DetailPagerAdapter pagerAdapter =
                new DetailPagerAdapter(this.getSupportFragmentManager(), mIngredients, mSteps);
        pagerAdapter.addFragment(iFragment);
        pagerAdapter.addFragment(sFragment);
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    //    @Override
    //    public void onItemClickListener(int itemId) {
    //        Step step = mSteps.get(itemId);
    //
    //        Log.i("TAG 2 ", "INSIDE CLICK");
    //        Intent i = new Intent(this, StepActivity.class);
    //        i.putExtra(getResources().getString(R.string.stepKey), step);
    //        startActivity(i);
    //    }
}
