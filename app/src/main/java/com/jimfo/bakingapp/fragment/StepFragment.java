package com.jimfo.bakingapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.adapter.StepAdapter;
import com.jimfo.bakingapp.model.Step;
import com.jimfo.bakingapp.ui.StepActivity;

import java.util.ArrayList;

public class StepFragment extends Fragment implements StepAdapter.ItemClickListener {

    private ArrayList<Step> mSteps = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSteps = getArguments().getParcelableArrayList(getResources().getString(R.string.stepKey));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.step_fragment, container, false);
        Context context = view.getContext();
        RecyclerView rv = (RecyclerView) view;
        rv.setLayoutManager(new LinearLayoutManager(context));
        StepAdapter mStepAdapter = new StepAdapter(context, this, mSteps);
        rv.setAdapter(mStepAdapter);
        return view;
    }

    public StepFragment newInstance(ArrayList<Step> steps) {

        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("steps", steps);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onItemClickListener(int itemId) {
        Step step = mSteps.get(itemId);

        Log.i("TAG 2 ", "INSIDE CLICK");
        Intent i = new Intent(getActivity(), StepActivity.class);
        i.putExtra(getResources().getString(R.string.stepKey), step);
        startActivity(i);
    }
}
