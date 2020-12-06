package labs.android2020.readingapp2020.models;

public class ImageLinks {
    private String smallThumbnail;
    private String thumbnail;
    private String small;
    private String medium;
    private  String large;
    private String extraLarge;

    public String getSmallThumbnail() {
        smallThumbnail = "https" + smallThumbnail.substring(4);
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        thumbnail = "https" + smallThumbnail.substring(4);
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSmall() {
        smallThumbnail= "https" + smallThumbnail.substring(4);
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        medium = "https" + smallThumbnail.substring(4);
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        large = "https" + smallThumbnail.substring(4);
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getExtraLarge() {
        extraLarge = "https" + smallThumbnail.substring(4);
        return extraLarge;
    }

    public void setExtraLarge(String extraLarge) {
        this.extraLarge = extraLarge;
    }
}
