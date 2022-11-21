package com.example.Library.hibernate.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "publishers")
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publishers_id;
    private String name;
    private String website;
    private String address;

    @OneToMany(cascade = CascadeType.DETACH, orphanRemoval = true, fetch = FetchType.EAGER )
    @JoinColumn(name = "publisher_id")
    private List<Book> books;



    public Publisher() {
    }

    public Publisher(String name, String website, String address) {
        this.name = name;
        this.website = website;
        this.address = address;

    }

    public int getPublishers_id() {
        return publishers_id;
    }

    public void setPublishers_id(int publishers_id) {
        this.publishers_id = publishers_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
