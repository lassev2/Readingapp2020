package labs.android2020.readingapp2020.Database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoBook {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBook(dbbook book);

    //the book is null
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addlist(dbbook list);

    @Delete
    void deleteBook(dbbook book);

    @Delete
    void deleteList(dbbook list);

    @Query("SELECT book FROM MyBooks")
    List<String> getAllBook();

    @Query("SELECT * FROM MyBooks WHERE listBook " + "= :text")
    List<dbbook> getBooks(String text);

    @Query("SELECT * FROM MyBooks WHERE book IS NULL OR book = ';'")
    List<dbbook> getlist();

    @Query("SELECT * FROM MyBooks WHERE listBook " + "= :list AND book " + "= :book")
    dbbook getBooktolist(String list, String book);

    //return all dbook that book = string
    @Query("SELECT * FROM MyBooks WHERE book " + "= :book")
    List<dbbook> getdbook(String book);


    @Update
    void updateBook(dbbook book);

}
