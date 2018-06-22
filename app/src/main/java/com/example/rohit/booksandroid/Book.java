package com.example.rohit.booksandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Book extends HashMap<String,String> {

    final static String baseURL ="http://172.17.251.101/BookShop/Service1.svc/Books";
    final static String baseURL1 ="http://172.17.251.101/BookShop/Service1.svc/Book/";

    public Book(String Author,String BookID,String CatID,String ISBN,String Price,String Stock,String Title) {
        put("Author", Author);
        put("BookID",BookID);
        put("CatID",CatID);
        put("ISBN",ISBN);
        put("Price",Price);
        put("Stock",Stock);
        put("Title",Title);
    }

    public static List<String> list() {
        List<String> list = new ArrayList<String>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL);
        try {
            for (int i =0; i<a.length(); i++)
                list.add(String.valueOf(a.getInt(i)));
        } catch (Exception e) {
            Log.e("Book.list()", "JSONArray error");
        }
        return(list);
    }

    public static Book getBook(String BookID) {
        JSONObject b = JSONParser.getJSONFromUrl(baseURL1 + BookID);
        try {
            return new Book(b.getString("Author"), b.getString("BookID"),
                    b.getString("CatID"), b.getString("ISBN"), b.getString("Price"),
                    b.getString("Stock"),b.getString("Title")
                    );
        } catch (Exception e) {
            Log.e("Book.getBook()", "JSONArray error");
        }
        return(null);
    }


    final static String imageURL = "http://172.17.251.101/BookShop/images";
    public static Bitmap getPhoto(boolean thumbnail, String id) {
        try {
            URL url = new URL(String.format("/%s.jpg",imageURL, id));
            URLConnection conn = url.openConnection();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Book.getPhoto()", "Bitmap error");
        }
        return(null);
    }
}
