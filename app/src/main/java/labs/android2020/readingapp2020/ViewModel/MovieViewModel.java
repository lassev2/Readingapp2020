package labs.android2020.readingapp2020.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


import labs.android2020.readingapp2020.Database.Movie;
import labs.android2020.readingapp2020.repository.MovieRepository;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private LiveData<List<Movie>> allMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAllMovies();
    }

    public void insert(Movie movie){
        repository.insert(movie);
    }
    public void update(Movie movie){
        repository.update(movie);
    }
    public void delete(Movie movie){
        repository.delete(movie);
    }public void deleteAllMovies(){
        repository.deleteAllMovies();
    }
    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }



}
