package com.jimfo.bakingapp;

import android.os.AsyncTask;

import com.jimfo.bakingapp.model.Recipe;
import com.jimfo.bakingapp.ui.MainActivity;
import com.jimfo.bakingapp.utils.NetworkUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class RecipeTask extends AsyncTask<String, Void, ArrayList<Recipe>> {

    private final WeakReference<MainActivity> myRef;

    private PostExecuteListener mPostExecuteListener;

    public interface PostExecuteListener {
        void onPostExecute(ArrayList<Recipe> movies);
    }

    public RecipeTask(MainActivity activity, PostExecuteListener pel) {

        myRef = new WeakReference<>(activity);
        this.mPostExecuteListener = pel;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Recipe> doInBackground(String... args) {

        ArrayList<Recipe> recipes;
        recipes = NetworkUtils.fetchRecipeData(myRef.get());

        return recipes;
    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> result) {
        super.onPostExecute(result);

        mPostExecuteListener.onPostExecute(result);
    }
}
