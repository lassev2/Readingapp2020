package labs.android2020.readingapp2020.Database;
//https://codinginflow.com/tutorials/android/room-viewmodel-livedata-recyclerview-mvvm/part-2-entity
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name ="movie_name")
    private  String name;

    @ColumnInfo(name ="movie_producer")
    private  String producer;

    @ColumnInfo(name ="movie_year")
    private  String year;

    @ColumnInfo(name ="movie_country")
    private  String country;


    public Movie(String name, String producer, String year, String country) {
        this.name=name;
        this.producer = producer;
        this.year=year;
        this.country=country;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String fullName) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String address) {
        this.producer = producer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String email) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}