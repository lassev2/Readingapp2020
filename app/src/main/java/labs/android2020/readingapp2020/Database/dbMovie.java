package labs.android2020.readingapp2020.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MyMovies")
public class dbMovie {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    private int id;

    @ColumnInfo(name = "Movie")
    private String movie;

    @NonNull
    @ColumnInfo(name = "listMovie")
    private String list;

    @ColumnInfo(name = "Date")
    private String date;


    @ColumnInfo(name = "isCompleted")
    private int isCompleted;

    public dbMovie(){}

    public dbMovie(String b, String s){
        this.movie = b;
        this.list = s;
        this.date = "";
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }
}
