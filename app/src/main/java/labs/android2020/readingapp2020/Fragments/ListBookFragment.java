package labs.android2020.readingapp2020.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import labs.android2020.readingapp2020.Adapter.AdapterListBook;
import labs.android2020.readingapp2020.Database.BookDatabase;
import labs.android2020.readingapp2020.Database.dbbook;
import labs.android2020.readingapp2020.Interface.bookItem;
import labs.android2020.readingapp2020.R;
import labs.android2020.readingapp2020.models.Book;

public class ListBookFragment extends Fragment {

    private ArrayList<bookItem> listbook;
    private List<dbbook> list;
    private RecyclerView rvb;
    private RecyclerView.LayoutManager managerb ;
    private AdapterListBook adapterb;
    private String textlist;
    private BookDatabase database;

    public ListBookFragment(String text){
        this.textlist = text;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = BookDatabase.getInstance(getContext());
        listbook = generateBooks();
        adapterb = new AdapterListBook(getContext(), listbook, new AdapterListBook.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Gson gson = new Gson();
                Book book = gson.fromJson(list.get(++position).getBook(),Book.class);
                BookFragment fragment = new BookFragment(textlist, book);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.commit();
            }

            @Override
            public void onItemLongClickListener(int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage(R.string.deleteOneDialogTitle).setPositiveButton(R.string.okDeleteDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int index = position;
                                final dbbook data = list.get(++index);
                                database.BookDao().deleteBook(data);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapterb.removeItem(position);
                                        adapterb.notifyDataSetChanged();
                                    }
                                });
                            }
                        }).start();
                    }
                }).setNegativeButton(R.string.cancelDeleteDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.create().show();
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_listsbook, container,false);
        this.rvb = v.findViewById(R.id.recyclerBook);
        if(getContext() != null){
            managerb = new LinearLayoutManager(getContext());
            this.rvb.setLayoutManager(managerb);
            this.rvb.setAdapter(adapterb);
            adapterb.notifyDataSetChanged();
        }
        return v;
    }

    private ArrayList<bookItem> generateBooks() {
        ArrayList<bookItem> result = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = database.BookDao().getBooks(textlist);
                Gson gson = new Gson();
                if(list.size() > 0){
                    for(int i = 1; i < list.size(); i++){
                        bookItem bookitem = new bookItem();
                        Book book = gson.fromJson(list.get(i).getBook(),Book.class);
                        if((book.getVolumeInfo() != null)&& (book.getVolumeInfo().getImageLinks() != null)) {
                            bookitem.setUrl(book.getImageLinks().getThumbnail());
                        }

                        result.add(bookitem);
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterb.notifyDataSetChanged();
                    }
                });
            }
        }).start();
        return result;
    }
}
