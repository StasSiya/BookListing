package com.example.android.booklisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Anastasiya on 16.11.2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookAdapterViewHolder> {
   public static final String LOG_TAG = MainActivity.class.getName();
    private String[] mBookInfo;

    public BookAdapter() {

    }

    public class BookAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView mBooksTextView;

        public BookAdapterViewHolder(View view) {
            super(view);
            mBooksTextView = (TextView) view.findViewById(R.id.books_data);
        }
    }

    @Override
    public BookAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.books_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachToParentImmerdiatly = false;

        View view = inflater.inflate(layoutIdForListItem, parent, attachToParentImmerdiatly);
        Log.v(LOG_TAG, "Viewholder inflated");
        return new BookAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(BookAdapterViewHolder holder, int position) {

        String currentBook = mBookInfo[position];

        Log.v(LOG_TAG, "Set the current book on the corresponding textview");
        holder.mBooksTextView.setText(currentBook);



    }

    @Override
    public int getItemCount() {
        if(null==mBookInfo) {
            return 0;
        }
        return mBookInfo.length;
    }
    public void setBookData(String[] bookData) {
        mBookInfo = bookData;
        notifyDataSetChanged();
    }
}
