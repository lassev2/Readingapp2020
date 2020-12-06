package labs.android2020.readingapp2020.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import labs.android2020.readingapp2020.Adapter.AdapterList;
import labs.android2020.readingapp2020.Database.BookDatabase;
import labs.android2020.readingapp2020.Database.dbbook;
import labs.android2020.readingapp2020.Interface.Item;
import labs.android2020.readingapp2020.R;

public class ListsFragment extends Fragment {
    private ArrayList<Item> list;
    private RecyclerView rv;
    private List<dbbook> listdb;
    private RecyclerView.LayoutManager manager ;
    private AdapterList adapter;
    private FloatingActionButton add;
    private BookDatabase database;

    public ListsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = BookDatabase.getInstance(getContext());
        list = generateData();
        adapter = new AdapterList(getContext(), list, new AdapterList.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                ListBookFragment fragment = new ListBookFragment(list.get(position).getText());
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
                                final dbbook data = listdb.get(position);
                                //detele books of list
                                List<dbbook> books = database.BookDao().getBooks(data.getList());
                                for(int i = 0; i < books.size(); i++){
                                    database.BookDao().deleteBook(books.get(i));
                                }
                                //delete the list
                                database.BookDao().deleteList(data);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.removeItem(position);
                                        adapter.notifyDataSetChanged();
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
        View v = inflater.inflate(R.layout.fragment_lists, container,false);
        this.rv = v.findViewById(R.id.recyclerlist);
        this.add = v.findViewById(R.id.addList);
        if(getContext() != null){
            manager = new LinearLayoutManager(getContext());
            this.rv.setLayoutManager(manager);
        }
        this.rv.setAdapter(adapter);
        this.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.add_list, null);
                //cojo la referencia del text para guardarme su valor cuando el usuario cree la nueva lista
                EditText t = view.findViewById(R.id.et_CreateList);
                AddItemList(t, view);
            }
        });

        return v;
    }

    private void AddItemList(EditText text, View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext())
                .setPositiveButton(R.string.name_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!text.getText().toString().isEmpty()) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    list.add(new Item(text.getText().toString(), android.R.drawable.ic_menu_sort_by_size));
                                    database.BookDao().addlist(new dbbook(null, text.getText().toString()));
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }).start();
                        } else {
                            Toast.makeText(getContext(), R.string.create_entry_msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancelDeleteDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog.setView(view).create().show();
    }

    private ArrayList<Item> generateData() {
        ArrayList<Item> result = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                listdb = database.BookDao().getlist();
                if(listdb.size() > 0){
                    for(int i = 0; i < listdb.size(); i++){
                        result.add(new Item(listdb.get(i).getList(), android.R.drawable.ic_menu_sort_by_size));
                    }
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
        return result;
    }
}
