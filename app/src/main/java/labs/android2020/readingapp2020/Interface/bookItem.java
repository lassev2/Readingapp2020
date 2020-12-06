package labs.android2020.readingapp2020.Interface;

import android.widget.ImageView;

public class bookItem {
    private String text;
    private ImageView image;
    private String url;

    public bookItem(){}

    public bookItem(String text, ImageView image) {
        this.text = text;
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String text) {
        this.url = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}