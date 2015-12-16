package com.lcsmobileapps.brazilianblogs2.model;

/**
 * Created by ckilee on 29/11/15.
 */
public class Post {
    String id;
    String title;
    String description;
    String imagePath;
    String blogName;
    String postUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String toString(){
        return "Title:"+getTitle()+" Get Description:"+getDescription()+" Get Image Path:"+getImagePath()+" get Blog Name:"+getBlogName()+" get Post URL:"+getPostUrl();
    }

}
