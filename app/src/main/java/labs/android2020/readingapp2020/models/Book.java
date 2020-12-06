package labs.android2020.readingapp2020.models;

import java.util.List;

public class Book {
    private String id;
    private VolumeInfo volumeInfo;

    public List<String> getCategories() {
        return volumeInfo.getCategories();
    }

    public void setCategories(List<String> categories) {
        this.volumeInfo.setCategories(categories);
    }

    public IndustryIdentifiers[] getIndustryIdentifiers() {
        return volumeInfo.getIndustryIdentifiers();
    }

    public void setIndustryIdentifiers(IndustryIdentifiers[] industryIdentifiers) {
        this.volumeInfo.setIndustryIdentifiers(industryIdentifiers);
    }

    public String getISBN(){
        if(this.volumeInfo.getIndustryIdentifiers().length > 1 && this.volumeInfo.getIndustryIdentifiers()[1] != null) {
            return this.volumeInfo.getIndustryIdentifiers()[1].getIdentifier();
        }
        return  null;
    }

    public String getDescription() {
        return volumeInfo.getDescription();
    }

    public void setDescription(String description) {
        volumeInfo.setDescription(description);
    }

    public String getTitle() {
        return volumeInfo.getTitle();
    }


    public void setTitle(String title) {
        this.volumeInfo.setTitle(title);
    }

    public String getSubtitle() {
        return volumeInfo.getSubtitle();
    }

    public void setSubtitle(String subtitle) {
        this.volumeInfo.setSubtitle(subtitle);
    }

    public List<String> getAuthors() {
        return this.volumeInfo.getAuthors();
    }

    public void setAuthors(List<String> authors) {
        this.volumeInfo.setAuthors(authors);
    }

    public String getPublisher() {
        return this.volumeInfo.getPublisher();
    }

    public void setPublisher(String publisher) {
        this.volumeInfo.setPublisher(publisher);
    }

    public String getPublishedDate() {
        return this.volumeInfo.getPublishedDate();
    }

    public void setPublishedDate(String publishedDate) {
        this.volumeInfo.getPublishedDate();
    }

    public Long getPageCount() {
        return this.volumeInfo.getPageCount();
    }

    public void setPageCount(Long pageCount) {
        this.volumeInfo.setPageCount(pageCount);
    }

    public double getAverageRating() {
        return this.volumeInfo.getAverageRating();
    }

    public void setAverageRating(double averageRating) {
        this.volumeInfo.setAverageRating(averageRating);
    }

    public ImageLinks getImageLinks() {
        return this.volumeInfo.getImageLinks();
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.volumeInfo.setImageLinks(imageLinks);
    }

    public String getLanguage() {
        return this.volumeInfo.getLanguage();
    }

    public void setLanguage(String language) {
        this.volumeInfo.setLanguage(language);
    }

    public String getPreviewLink() {
        return this.volumeInfo.getPreviewLink();
    }

    public void setPreviewLink(String previewLink) {
        this.volumeInfo.setPreviewLink(previewLink);
    }

    public String getInfoLink() {
        return this.volumeInfo.getInfoLink();
    }

    public void setInfoLink(String infoLink) {
        this.volumeInfo.setInfoLink(infoLink);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public void setBook(labs.android2020.readingapp2020.models.Book book) {
        this.id = book.getId();
        this.volumeInfo = book.getVolumeInfo();
    }

    public String getSmallThumbnail() {
        return this.volumeInfo.getImageLinks().getSmallThumbnail();
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.volumeInfo.getImageLinks().setSmallThumbnail(smallThumbnail);
    }

    public String getThumbnail() {
        return this.volumeInfo.getImageLinks().getThumbnail();
    }

    public void setThumbnail(String thumbnail) {
        this.volumeInfo.getImageLinks().setThumbnail(thumbnail);
    }

    public String getSmall() {
        return this.volumeInfo.getImageLinks().getSmall();
    }

    public void setSmall(String small) {
        this.volumeInfo.getImageLinks().setSmall(small);
    }

    public String getMedium() {
        return this.volumeInfo.getImageLinks().getMedium();
    }

    public void setMedium(String medium) {
        this.volumeInfo.getImageLinks().setMedium(medium);
    }

    public String getLarge() {
        return this.volumeInfo.getImageLinks().getLarge();
    }

    public void setLarge(String large) {
        this.volumeInfo.getImageLinks().setLarge(large);
    }

    public String getExtraLarge() {
        return this.volumeInfo.getImageLinks().getExtraLarge();
    }

    public void setExtraLarge(String extraLarge) {
        this.volumeInfo.getImageLinks().setExtraLarge(extraLarge);
    }

}
