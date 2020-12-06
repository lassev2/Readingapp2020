package labs.android2020.readingapp2020.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import labs.android2020.readingapp2020.Database.BookDatabase;
import labs.android2020.readingapp2020.Database.dbbook;
import labs.android2020.readingapp2020.R;
import labs.android2020.readingapp2020.Services.GoogleBookService;
import labs.android2020.readingapp2020.models.Book;

public class BookFragment extends Fragment {
    private boolean newBook = false;
    private Book book;
    private TextView author;
    private TextView edit;
    private TextView year;
    private TextView title;
    private TextView topic;
    private TextView summary;
    private TextView isbnCode;
    private ImageView bookCover;
    private Button button;
    private BookDatabase database;
    private String textList;
    private List<dbbook> listdb;
    private ArrayList<String> list;
    private String date;
    private FloatingActionButton fab;

    public BookFragment(String s, Book b){
        textList = s;
        book = b;
        date = "";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = BookDatabase.getInstance(getContext());
        list = getLists();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book, container, false);






        bookCover = v.findViewById(R.id.bookCover);
        author = v.findViewById(R.id.tvauthor);
        title = v.findViewById(R.id.tvnamebook);
        edit = v.findViewById(R.id.tvedition);
        year = v.findViewById(R.id.tvyear);
        isbnCode = v.findViewById(R.id.tvcodeBook);
        topic = v.findViewById(R.id.tvtopic);
        summary = v.findViewById(R.id.tvsumnary);
        InfBook();



        return v;
    }

    private void InfBook(){
        List<String> authors = book.getAuthors();
        if(authors != null) {
            if (authors.size() > 1) {
                String s = "";
                for (int i = 0; i < book.getAuthors().size(); i++) {
                    s += book.getAuthors().get(i) + ", ";
                }
                author.setText(s);
            } else {
                author.setText(book.getAuthors().get(0));
            }
        }
        List<String> categories = book.getCategories();
        if(categories != null) {
            if (categories.size() > 1) {
                String s = "";
                for (int i = 0; i < book.getCategories().size(); i++) {
                    s += ", " + book.getCategories().get(i);
                }
                topic.setText(s);
            } else {
                topic.setText(book.getCategories().get(0));
            }
        }
        GoogleBookService googleBookService = new GoogleBookService();
        googleBookService.setBookThumbnail(book, bookCover);

        title.setText(book.getTitle());
        year.setText(book.getPublishedDate());
        edit.setText(book.getPublisher());
        summary.setText(book.getDescription());
        isbnCode.setText(book.getISBN());
    }

    private void OnClickButton(){
        CharSequence[] listChar = new CharSequence[list.size()];
        for(int i = 0; i < list.size(); i++){
            listChar[i] = list.get(i);
        }
        if(list.size() > 0){
            AlertDialog.Builder builder = new AlertDialog.Builder((getContext()));
            builder.setTitle("Add book to list")
                    .setItems(listChar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Gson gson = new Gson();
                                    String b = gson.toJson(book);
                                    dbbook db = database.BookDao().getBooktolist(listChar[which].toString(),b);
                                    if(db == null){
                                        database.BookDao().addBook(new dbbook(b,listChar[which].toString()));
                                    }
                                }
                            }).start();
                        }
                    });
            builder.create().show();
            button.setVisibility(View.INVISIBLE);
        }else{
            Toast.makeText(getContext(), R.string.noList, Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<String> getLists() {
        ArrayList<String> result = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                listdb = database.BookDao().getlist();
                if(listdb.size() > 0){
                    for(int i = 0; i < listdb.size(); i++){
                        result.add(listdb.get(i).getList());
                    }
                }
            }
        }).start();
        return result;
    }






                }

