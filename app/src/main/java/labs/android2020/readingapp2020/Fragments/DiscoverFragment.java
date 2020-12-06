package labs.android2020.readingapp2020.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import labs.android2020.readingapp2020.Adapter.AdapterDiscover;
import labs.android2020.readingapp2020.Interface.bookItem;
import labs.android2020.readingapp2020.R;
import labs.android2020.readingapp2020.Services.GoogleBookService;
import labs.android2020.readingapp2020.models.BookList;

public class DiscoverFragment extends Fragment {
    private ArrayList<bookItem> listbook;
    private RecyclerView rvb;
    private Spinner spinner;
    private BookList books;
    private ProgressBar progressBar;
    private RecyclerView.LayoutManager managerb;
    private AdapterDiscover adapterb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listbook = new ArrayList<bookItem>();
        adapterb = new AdapterDiscover(getContext(), listbook, new AdapterDiscover.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                BookFragment fragment = new BookFragment("", books.getItems().get(position));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        OnClickSubject("Fiction");
                        break;
                    case 1:
                        OnClickSubject("Thriller");
                        break;
                    case 2:
                        OnClickSubject("History");
                        break;
                    case 3:
                        OnClickSubject("Adventure");
                        break;
                    case 4:
                        OnClickSubject("Classic");
                        break;
                    case 5:
                        OnClickSubject("Fantasy");
                        break;
                    case 6:
                        OnClickSubject("Horror");
                        break;
                    case 7:
                        OnClickSubject("Biography");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                OnClickSubject("Fiction");
            }
        };

        this.spinner = getView().findViewById(R.id.spinner);
        this.spinner.setOnItemSelectedListener(listener);

        this.progressBar = getView().findViewById(R.id.progressBar);
        this.rvb = getView().findViewById(R.id.rvDiscover);

        if (getContext() != null) {
            managerb = new GridLayoutManager(getContext(), 3);
            this.rvb.setLayoutManager(managerb);
            this.rvb.setAdapter(adapterb);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    public void OnClickSubject(String subject) {
        listbook.clear();
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                GoogleBookService googleBookService = new GoogleBookService();
                books = googleBookService.getBooksBySubject(subject);
                for (int i = 0; i < books.getItems().size(); i++) {
                    bookItem book = new bookItem();
                    if ((books.getItems().get(i).getVolumeInfo() != null) && (books.getItems().get(i).getVolumeInfo().getImageLinks() != null)) {
                        book.setUrl(books.getItems().get(i).getImageLinks().getThumbnail());
                    }
                    book.setText(books.getItems().get(i).getTitle());
                    listbook.add(book);
                }
                /*de esta manera se actualiza la interfaz*/
                if(getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                            adapterb.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();

    }
}
