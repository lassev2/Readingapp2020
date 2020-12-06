package labs.android2020.readingapp2020.Services;


//documentation: Google book services
//

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;


import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import labs.android2020.readingapp2020.R;
import labs.android2020.readingapp2020.models.Book;
import labs.android2020.readingapp2020.models.BookList;

public class GoogleBookService {

    public Book searchBookById(String id) {
        String urlString = "https://www.googleapis.com/books/v1/volumes/" + id;
        Book book = new Book();

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();
            book= gson.fromJson(reader, Book.class);
            reader.close();

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }

    public BookList searchBookByISBN(String isbn){
        String urlString = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;

        BookList bookList = new BookList();

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();

            bookList = gson.fromJson(reader,BookList.class);

            reader.close();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public BookList searchBookByTitle(String title){
        String urlString = "https://www.googleapis.com/books/v1/volumes?q=" + title;

        BookList bookList = new BookList();

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();

            bookList = gson.fromJson(reader,BookList.class);

            reader.close();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public  BookList getBooksBySubject(String subject){
        String urlString = "https://www.googleapis.com/books/v1/volumes?q=subject:" + subject + "&maxResults=39";

        BookList bookList = new BookList();

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Gson gson = new Gson();

            bookList = gson.fromJson(reader,BookList.class);

            reader.close();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public void setBookThumbnail(Book book, ImageView imageView){
        Handler uiHandler  = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                String thumbnail ="";
                if(book != null){
                    if(book.getVolumeInfo() != null){
                        if(book.getVolumeInfo().getImageLinks() != null){
                            if (book.getVolumeInfo().getImageLinks().getThumbnail() != null){
                                thumbnail = book.getVolumeInfo().getImageLinks().getThumbnail();
                                Picasso.get().load(thumbnail).into(imageView);
                            }
                        }
                    }
                }
            }
        });
    }

    public void setBookThumbnailAdapter(String book, ImageView imageView){
        Handler uiHandler  = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                if(book != null){
                    Picasso.get().load(book).placeholder(R.drawable.ic_library_books_black_24dp).into(imageView);
                }
            }
        });
    }
}