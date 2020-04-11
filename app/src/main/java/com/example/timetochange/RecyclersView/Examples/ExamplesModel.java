package com.example.timetochange.RecyclersView.Examples;

public class ExamplesModel {
    int id;
    String title,image,link;

    public ExamplesModel(int id, String title, String image, String link) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
