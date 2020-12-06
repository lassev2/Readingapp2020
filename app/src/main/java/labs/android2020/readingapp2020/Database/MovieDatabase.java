package labs.android2020.readingapp2020.Database;

import android.content.Context;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import labs.android2020.readingapp2020.Database.Movie;



@Database(entities = {Movie.class}, version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    private static labs.android2020.readingapp2020.Database.MovieDatabase instance;
    public abstract DaoMovie daoMovie();

    public static synchronized labs.android2020.readingapp2020.Database.MovieDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    labs.android2020.readingapp2020.Database.MovieDatabase.class, "movies").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private DaoMovie daoMovie;

        private PopulateDbAsyncTask(labs.android2020.readingapp2020.Database.MovieDatabase db){
            daoMovie=db.daoMovie();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            daoMovie.insert(new Movie("Shawshank Redemption", "Niki Marvin", "1994", "USA"));
            daoMovie.insert(new Movie("Godfather", "Francis Ford Coppola", "1972", "USA"));
            daoMovie.insert(new Movie("The Dark Knight", "Christopher Nolan", "2008", "USA"));
            return null;
        }
    }
}

