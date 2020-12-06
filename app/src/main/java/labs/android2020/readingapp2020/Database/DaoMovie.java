package labs.android2020.readingapp2020.Database;




import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoMovie {
    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movies")
    void deleteALLMovies();

    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAllMovies();

}