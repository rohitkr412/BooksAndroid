package com.example.rohit.booksandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.ImageView;

public class BookDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Intent i = getIntent();
        String BookID = i.getStringExtra("BookID");

        new AsyncTask<String,Void,Book>(){
            @Override
            protected Book doInBackground(String... params){
                return Book.getBook(params[0]);
            }

            @Override
            protected void onPostExecute(Book book)
            {
                show(book);
            }
        }.execute(BookID);


    }

    void show(Book book) {
        int []ids = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4,R.id.editText5,R.id.editText6,R.id.editText7};
        String []keys = {"Author","BookID","CatID", "ISBN","Price","Stock","Title"};
        for (int i=0; i<keys.length; i++) {
            EditText e = (EditText) findViewById(ids[i]);
            e.setText(book.get(keys[i]));
        }

        new AsyncTask<String, Void, Bitmap>(){
            @Override
            protected Bitmap doInBackground(String... params)
            {
                return Book.getPhoto(false, params[0]);
            }

            @Override
            protected void onPostExecute(Bitmap result){
                ImageView image = (ImageView) findViewById(R.id.imageView);
                image.setImageBitmap(result);
            }
        }.execute(book.get("ISBN"));

        //ImageView image = (ImageView) findViewById(R.id.imageView);
        //        image.setImageBitmap(Employee.getPhoto(false, emp.get("Id")));
    }
}
