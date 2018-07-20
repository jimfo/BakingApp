package com.jimfo.bakingapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jimfo.bakingapp.fragment.IngredientFragment;
import com.jimfo.bakingapp.fragment.StepFragment;
import com.jimfo.bakingapp.model.Ingredient;
import com.jimfo.bakingapp.model.Step;

import java.util.ArrayList;

public class DetailPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<Ingredient> mIngredients;
    private ArrayList<Step> mSteps;
    private Fragment fragment;

    public DetailPagerAdapter(FragmentManager fm, ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        super(fm);

        this.mIngredients = ingredients;
        this.mSteps = steps;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                fragment = new IngredientFragment().newInstance(mIngredients);
                break;
            case 1:
                fragment = new StepFragment().newInstance(mSteps);
                break;
            default:
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment frag) {
        fragments.add(frag);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String title = null;

        if (position == 0) {
            title = "Ingredients";
        } else if (position == 1) {
            title = "Steps";
        }

        return title;
    }
}
