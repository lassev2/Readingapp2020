package labs.android2020.readingapp2020.models;

import java.util.List;

public class BookList {
    private List<Book> items;

    private int totalItems;

    public List<Book> getItems() {
        return items;
    }

    public void setItems(List<Book> items) {
        this.items = items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
