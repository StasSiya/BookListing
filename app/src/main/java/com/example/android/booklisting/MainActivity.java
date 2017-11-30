package com.example.android.booklisting;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();
    EditText mUserInput;
    Button mSearchButton;
    private RecyclerView mRecyclerView;
    BookAdapter adapter;
    //RecyclerView mBooksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserInput=(EditText)findViewById(R.id.user_search);
        mSearchButton=(Button) findViewById(R.id.search_button);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);


        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeGoogleBooksSearchQuery();

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager
                (MainActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        adapter = new BookAdapter();
        mRecyclerView.setAdapter(adapter);
    }
    //This method pulls googleBooksQuery from Edittext and builds googleBooksSearchURL, and fires off
    //Asynctask to perform the GET request
    void makeGoogleBooksSearchQuery () {
        String googleBooksQuery = mUserInput.getText().toString();
        URL googleBooksSearchUrl = BookUtils.buildURL(googleBooksQuery);


      //Asynctask performs the GET request
        new BooksTask().execute(googleBooksSearchUrl);
        Log.v(LOG_TAG, "execute the search and show results");
    }



    public class BooksTask extends AsyncTask <URL, Void, String[]>{

        @Override
        protected String[] doInBackground(URL... urls) {

            URL searchURL = urls[0];
            String googleBooksSearchResults = null;
            try{
                googleBooksSearchResults = BookUtils.getResponseFromHttpURL(searchURL);
                String [] bookData = BookUtils.extractBooks(MainActivity.this,googleBooksSearchResults);

                return bookData;

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }

        private void showWeatherDataView() {
        /* First, make sure the error is invisible */
            // COMPLETED (44) Show mRecyclerView, not mWeatherTextView
        /* Then, make sure the weather data is visible */
            mRecyclerView.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onPostExecute(String[] bookInfo) {
            if (bookInfo !=null){
                showWeatherDataView();
                adapter.setBookData(bookInfo);


            }
        }
    }

}
