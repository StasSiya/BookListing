package com.example.android.booklisting;

/**
 * Created by Anastasiya on 16.11.2017.
 */

public class Book {

    public String mTitle;
    public String mSubtitle;
    //public String [] mAuthors;
    public String mPublisher;

  /*  public Book(String title, String subtitle, String [] authors, String publisher) {
        mTitle = title;
        mSubtitle = subtitle;
        //mAuthors = authors;
        mPublisher = publisher;
    }*/
    public Book(String title) {
        mTitle = title;

    }

    public String getTitle() {
        return mTitle;
    }

    /*public String getSubtitle() {
        return mSubtitle;
    }

    //public String[] getAuthors() {
        //return mAuthors;
   // }

    public String getPublisher() {
        return mPublisher;
    }*/
}
