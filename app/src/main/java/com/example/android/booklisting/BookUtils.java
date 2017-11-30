package com.example.android.booklisting;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Anastasiya on 16.11.2017.
 */

public class BookUtils {
    public static final String LOG_TAG = MainActivity.class.getName();

    final static String BASIC_GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes";

    final static String PARAM_QUERY = "q";
    final static String googleSearchTerm = "terms";
    final static String PARAM_MAX_RESULS = "maxResults";
    final static String mResult = "40";


    /*Build URL to query Google Books API
    @param terms - the keyword that will be queried for.
    @return The URL to use to query the GoogleBooksServer*/

    public static URL buildURL(String googleSearchTerm) {
        Uri builtUri = Uri.parse(BASIC_GOOGLE_BOOKS_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, googleSearchTerm)
                .appendQueryParameter(PARAM_MAX_RESULS,mResult)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
/*  This method returns the entire result from the HTTP response*/
    public static String getResponseFromHttpURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }

    }
    public static String[] extractBooks (Context context, String booksJSON)throws JSONException{
        Log.v(LOG_TAG, "Extract data");

        if(TextUtils.isEmpty(booksJSON)){
            return null;
        }
            String[] parsedBooksData;
            JSONObject root = new JSONObject(booksJSON);

            JSONArray booksArray = root.getJSONArray("items");

            parsedBooksData = new String [booksArray.length()];

            for (int i=0; i<booksArray.length();i++){

                JSONObject f = booksArray.getJSONObject(i);

                JSONObject volumeInfo = f.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                //JSONArray authors = volumeInfo.getJSONArray("authors");
                /*String author = "";
                for (int j =0;j<authors.length();j++)
                {
                    author+=authors.getString(j)+", ";
                }
*/
                parsedBooksData [i]= title;
            }
            Log.v(LOG_TAG, "Show parsedBooksData" + parsedBooksData);
            return parsedBooksData;



    }
}


