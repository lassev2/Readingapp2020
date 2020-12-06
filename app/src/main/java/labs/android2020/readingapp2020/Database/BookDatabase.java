package labs.android2020.readingapp2020.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {dbbook.class}, version = 1,exportSchema = false)
public abstract class BookDatabase extends  RoomDatabase{
    private static labs.android2020.readingapp2020.Database.BookDatabase BookDatabase;

    public synchronized static labs.android2020.readingapp2020.Database.BookDatabase getInstance(Context context){
        if(BookDatabase == null) {
            BookDatabase = Room
                    .databaseBuilder(context, labs.android2020.readingapp2020.Database.BookDatabase.class, "MyBooks").build();
        }
        return BookDatabase;
    }

    public abstract DaoBook BookDao();
}
