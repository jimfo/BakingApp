package com.jimfo.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jimfo.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecipeDetailViewHolder> {

    private final List<Step> mSteps;
    private ItemClickListener mListener;
    private LayoutInflater mInflater;

    RecipeDetailAdapter(Context context, ItemClickListener listener, ArrayList<Step> steps) {
        this.mSteps = steps;
        this.mListener = listener;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecipeDetailAdapter.RecipeDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = mInflater.inflate(R.layout.activity_recipe_card, parent, false);
        return new RecipeDetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailViewHolder holder, int i) {
        holder.stepTv.append(mSteps.get(i).getmId() + " ");
        holder.stepTv.append(mSteps.get(i).getmShortDescription());

        if (!mSteps.get(i).getmVideoUrl().equals("")) {
            //holder.stepTv.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.ic_play_arrow_black);
            holder.playIv.setImageResource(R.drawable.ic_play_arrow_black);
        }
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    class RecipeDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView stepTv;
        private ImageView playIv;

        public RecipeDetailViewHolder(View itemView) {
            super(itemView);

            stepTv = itemView.findViewById(R.id.step_tv);
            playIv = itemView.findViewById(R.id.play_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mListener.onItemClickListener(position);
        }
    }
}
