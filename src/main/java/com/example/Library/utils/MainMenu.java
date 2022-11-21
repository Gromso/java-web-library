package com.example.Library.utils;

import java.util.ArrayList;

public class MainMenu {

    public static ArrayList<ManiItem> get(){
        ArrayList<ManiItem> items = new ArrayList<>();
        items.add(new ManiItem("Home", "/"));
        items.add(new ManiItem("About","/about"));
        items.add(new ManiItem("Authors", "/authors"));
        items.add(new ManiItem("Genres", "/genres"));
        items.add(new ManiItem("Advanced Search", "/advanced-search"));
        items.add(new ManiItem("Contact", "/contact"));
        return items;
    }
}
