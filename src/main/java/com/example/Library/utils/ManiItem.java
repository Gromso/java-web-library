package com.example.Library.utils;

public class ManiItem {
    private String title;
    private String path;
    private boolean isExternal;


    public ManiItem(String title, String path) {
        this.title = title;
        this.path = path;
        this.isExternal = false;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public boolean isExternal() {
        return isExternal;
    }
}
