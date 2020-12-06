package labs.android2020.readingapp2020.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import labs.android2020.readingapp2020.Database.DaoMovie;
import labs.android2020.readingapp2020.Database.Movie;
import labs.android2020.readingapp2020.Database.MovieDatabase;


public class MovieRepository {
    private DaoMovie daoMovie;
    private LiveData<List<Movie>> allMovies;

    public MovieRepository(Application application){
        MovieDatabase database= MovieDatabase.getInstance(application);
        daoMovie =database.daoMovie();
        allMovies=daoMovie.getAllMovies();

    }
    // those methods are API that repository exposes to the outside
    public void insert(Movie movie){
    new InsertMovieAsyncTask(daoMovie).execute(movie);
    }
    public void update(Movie movie){
    new UpdateMovieAsyncTask(daoMovie).execute(movie);
    }
    public void delete(Movie movie){
    new DeleteMovieAsyncTask(daoMovie).execute(movie);
    }
    public void deleteAllMovies(){
    new DeleteAllMoviesAsyncTask(daoMovie).execute();
    }
    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }
    private static class InsertMovieAsyncTask extends AsyncTask<Movie,Void,Void> {
    private DaoMovie daoMovie;

    private InsertMovieAsyncTask(DaoMovie daoMovie){
        this.daoMovie=daoMovie;
    }
        @Override
        protected Void doInBackground(Movie... movies) {
            daoMovie.insert(movies[0]);
            return null;
        }
    }

    private static class UpdateMovieAsyncTask extends AsyncTask<Movie,Void,Void> {
        private DaoMovie daoMovie;

        private UpdateMovieAsyncTask(DaoMovie daoMovie){
            this.daoMovie=daoMovie;
        }
        @Override
        protected Void doInBackground(Movie... movies) {
            daoMovie.update(movies[0]);
            return null;
        }
    }
    private static class DeleteMovieAsyncTask extends AsyncTask<Movie,Void,Void> {
        private DaoMovie daoMovie;

        private DeleteMovieAsyncTask(DaoMovie daoMovie){
            this.daoMovie=daoMovie;
        }
        @Override
        protected Void doInBackground(Movie... movies) {
            daoMovie.delete(movies[0]);
            return null;
        }
    }
    private static class DeleteAllMoviesAsyncTask extends AsyncTask<Void,Void,Void> {
        private DaoMovie daoMovie;

        private DeleteAllMoviesAsyncTask(DaoMovie daoMovie){
            this.daoMovie=daoMovie;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            daoMovie.deleteALLMovies();
            return null;
        }
    }
}
