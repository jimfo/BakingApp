package com.jimfo.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.model.Step;

import java.util.ArrayList;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private ArrayList<Step> mSteps;
    private LayoutInflater mInflater;
    private ItemClickListener mListener;

    public StepAdapter(Context context, ItemClickListener listener, ArrayList<Step> steps) {
        this.mSteps = steps;
        this.mListener = listener;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StepAdapter.StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = mInflater.inflate(R.layout.step_card_view, parent, false);

        return new StepViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final StepViewHolder holder, int i) {
        holder.stepItem.append(mSteps.get(i).getmId() + " ");
        holder.stepItem.append(mSteps.get(i).getmShortDescription());

        if (!mSteps.get(i).getmVideoUrl().equals("")) {
            holder.stepItem.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_play_arrow_black, 0);
        }

        holder.stepItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClickListener(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    class StepViewHolder extends RecyclerView.ViewHolder {
        TextView stepItem;

        StepViewHolder(View itemView) {
            super(itemView);

            stepItem = itemView.findViewById(R.id.step_tv);
        }
    }
}
