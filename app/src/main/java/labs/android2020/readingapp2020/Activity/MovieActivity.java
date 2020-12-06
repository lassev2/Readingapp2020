package labs.android2020.readingapp2020.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import labs.android2020.readingapp2020.Adapter.MovieAdapter;
import labs.android2020.readingapp2020.R;
import labs.android2020.readingapp2020.ViewModel.MovieViewModel;
import labs.android2020.readingapp2020.Database.Movie;


public class MovieActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener {

    private MovieViewModel movieViewModel;
    public static final int ADD_MOVIE_REQUEST = 1;
    public static final int EDIT_MOVIE_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("List of all movies");

        FloatingActionButton buttonAddNote = findViewById(R.id.add_movie);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, AddMovieActivity.class);
                startActivityForResult(intent, ADD_MOVIE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MovieAdapter adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                adapter.submitList(movies);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                movieViewModel.delete(adapter.getMovie(viewHolder.getAdapterPosition()));
                Toast.makeText(MovieActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MOVIE_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddMovieActivity.EXTRA_NAME);
            String producer = data.getStringExtra(AddMovieActivity.EXTRA_PRODUCER);
            String year = data.getStringExtra(AddMovieActivity.EXTRA_YEAR);
            String country = data.getStringExtra(AddMovieActivity.EXTRA_COUNTRY);


            Movie movie = new Movie(name, producer, year, country);
            movieViewModel.insert(movie);

            Toast.makeText(this, "Movie added", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_MOVIE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddMovieActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Movie can´t be updated, invalid ID", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddMovieActivity.EXTRA_NAME);
            String producer = data.getStringExtra(AddMovieActivity.EXTRA_PRODUCER);
            String year = data.getStringExtra(AddMovieActivity.EXTRA_YEAR);
            String country = data.getStringExtra(AddMovieActivity.EXTRA_COUNTRY);


            Movie movie = new Movie(name, producer, year, country);
            movie.setId(id);
            movieViewModel.update(movie);

            Toast.makeText(this, "Movie updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save movie", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_movies:
                movieViewModel.deleteAllMovies();
                Toast.makeText(this, "All Movies deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onItemClick(labs.android2020.readingapp2020.Database.Movie movie) {
        Intent intent = new Intent(labs.android2020.readingapp2020.Activity.MovieActivity.this, AddMovieActivity.class);
        intent.putExtra(AddMovieActivity.EXTRA_NAME, movie.getName());
        intent.putExtra(AddMovieActivity.EXTRA_PRODUCER, movie.getProducer());
        intent.putExtra(AddMovieActivity.EXTRA_YEAR, movie.getYear());
        intent.putExtra(AddMovieActivity.EXTRA_COUNTRY, movie.getCountry());
        intent.putExtra(AddMovieActivity.EXTRA_ID, movie.getId());
        startActivityForResult(intent, EDIT_MOVIE_REQUEST);

    }





}
