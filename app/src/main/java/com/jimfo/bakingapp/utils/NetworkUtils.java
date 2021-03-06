package com.jimfo.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.jimfo.bakingapp.R;
import com.jimfo.bakingapp.model.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private NetworkUtils() {
    }

    public static ArrayList<Recipe> fetchRecipeData(Context context) {
        // Create URL object
        URL requestURL = createUrl(context, context.getString(R.string.json_url));

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(context, requestURL);
        }
        catch (IOException e) {
            Log.e(TAG, context.getString(R.string.inputstreamError), e);
        }

        return ParsingJsonUtils.extractRecipeData(context, jsonResponse);
    }


    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(Context context, String stringUrl) {

        URL url;
        try {
            url = new URL(stringUrl);
        }
        catch (MalformedURLException exception) {
            Log.e(TAG, context.getString(R.string.urlError) + exception);
            return null;
        }

        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(Context context, URL url) throws IOException {

        String jsonResponse = "";

        // Check if url is null
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(context.getString(R.string.get));
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(context, inputStream);
            } else {
                Log.v(TAG, context.getString(R.string.responseCodeError) + urlConnection.getResponseCode());
            }
        }
        catch (IOException e) {
            Log.e(TAG, context.getString(R.string.jsonError) + e);
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(Context context, InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName(context.getString(R.string.utf8)));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    /**
     * Purpose : Method to check for network connectivity
     *
     * @return : boolean
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;

        if (connectivityMgr != null) {
            networkInfo = connectivityMgr.getActiveNetworkInfo();

            // if no network is available networkInfo will be null
            return (networkInfo != null && networkInfo.isConnected());
        }
        return false;
    }
}

