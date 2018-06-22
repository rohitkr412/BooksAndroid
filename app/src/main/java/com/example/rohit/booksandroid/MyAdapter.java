package com.example.rohit.booksandroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {

    private List<String> items;
    int resource;

    public MyAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.resource = resource;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, null);
        String BookID = items.get(position);
        if (BookID != null) {
            TextView b = (TextView) v.findViewById(R.id.textView);
            b.setText(BookID);
            ImageView image = (ImageView) v.findViewById(R.id.imageView2);
            image.setImageBitmap(Book.getPhoto(true,BookID));

        }
        return v;

        // LayoutInflater inflater = (LayoutInflater) getContext()
        //                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //        View v = inflater.inflate(resource, null);
        //        String eid = items.get(position);
        //        if (eid != null) {
        //            TextView e = (TextView) v.findViewById(R.id.textView);
        //            e.setText(eid);
        //            ImageView image = (ImageView) v.findViewById(R.id.imageView2);
        //            image.setImageBitmap(Employee.getPhoto(true, eid));
        //        }
        //        return v;
    }
}
