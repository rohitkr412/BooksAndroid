package com.example.rohit.booksandroid;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new AsyncTask<String,Void,List<String>>(){
            @Override
            protected List<String> doInBackground(String... params){
                return Book.list();
            }

            @Override
            protected void onPostExecute(List<String> result){
                MyAdapter adapter = new MyAdapter(MainActivity.this,R.layout.row,result);
                setListAdapter(adapter);
            }
        }.execute();



    }

    @Override
    protected void onListItemClick(ListView l, View v,
                                   int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("BookID", item);
        startActivity(intent);
    }
}
