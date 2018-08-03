package com.jimfo.bakingapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.SharedPreference;
import com.jimfo.bakingapp.adapter.IngredientAdapter;
import com.jimfo.bakingapp.model.Ingredient;

import java.util.ArrayList;

public class IngredientFragment extends Fragment {

    private ArrayList<Ingredient> mIngredients = new ArrayList<>();
    private SharedPreference sharedPreference;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreference = new SharedPreference();
        sharedPreference.removeIngredients(getActivity());

        if (getArguments() != null) {
            mIngredients = getArguments().getParcelableArrayList(getResources().getString(R.string.ingredientKey));
            sharedPreference.saveIngredients(getActivity(), mIngredients);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        sharedPreference.removeIngredients(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();

        sharedPreference.saveIngredients(getActivity(), mIngredients);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ingredient_fragment, container, false);
        Context context = view.getContext();
        RecyclerView rv = (RecyclerView) view;
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new IngredientAdapter(context, mIngredients));
        return view;
    }

    public IngredientFragment newInstance(ArrayList<Ingredient> ingredients) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("ingredients", ingredients);
        fragment.setArguments(args);
        return fragment;
    }
}
